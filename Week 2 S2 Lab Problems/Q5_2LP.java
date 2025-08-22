import java.util.*;
public class Q5_2LP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            emails.add(input);
        }
        List<EmailInfo> processedEmails = new ArrayList<>();

        int validCount = 0;
        int invalidCount = 0;
        Map<String, Integer> domainFrequency = new HashMap<>();
        int totalUsernameLength = 0;
        for (String email : emails) {
            boolean isValid = validateEmail(email);
            if (isValid) {
                validCount++;
                EmailInfo info = extractEmailComponents(email);
                processedEmails.add(info);
                domainFrequency.put(info.domain, domainFrequency.getOrDefault(info.domain, 0) +
                        1);
                totalUsernameLength += info.username.length();
            } else {
                invalidCount++;
                processedEmails.add(new EmailInfo(email, "", "", "", "", false));
            }
        }
        String mostCommonDomain = domainFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount
                : 0.0;
        displayEmailTable(processedEmails);
        System.out.println("\nEmail Statistics:");
        System.out.println("Total Valid Emails: " + validCount);
        System.out.println("Total Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + mostCommonDomain);
        System.out.printf("Average Username Length: %.2f\n", avgUsernameLength);
        scanner.close();
    }
    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        if (atIndex == -1 || atIndex != lastAtIndex) return false; // must have exactly one '@'

        int dotAfterAt = email.indexOf('.', atIndex);
        if (dotAfterAt == -1) return false; // must have '.' after '@'
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }
    public static EmailInfo extractEmailComponents(String email) {
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        int dotIndex = domain.indexOf('.');
        String domainName = "";
        String extension = "";
        if (dotIndex != -1) {
            domainName = domain.substring(0, dotIndex);
            extension = domain.substring(dotIndex + 1);
        } else {
            domainName = domain;
            extension = "";
        }
        return new EmailInfo(email, username, domain, domainName, extension, true);
    }
    public static void displayEmailTable(List<EmailInfo> emails) {
        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", "Email", "Username",
                "Domain", "Domain Name", "Extension", "Valid");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (EmailInfo e : emails) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n",

                    e.email, e.username, e.domain, e.domainName, e.extension, e.isValid ?

                            "Valid" : "Invalid");
        }
    }
}

class EmailInfo {
    String email;
    String username;
    String domain;
    String domainName;
    String extension;
    boolean isValid;
    public EmailInfo(String email, String username, String domain, String domainName, String
            extension, boolean isValid) {
        this.email = email;
        this.username = username;
        this.domain = domain;
        this.domainName = domainName;
        this.extension = extension;
        this.isValid = isValid;
    }
}