import java.util.*;

public class Q2_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter number of passwords to analyze:");
            int n = sc.nextInt();
            sc.nextLine();
            String[] passwords = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter password " + (i + 1) + ": ");
                passwords[i] = sc.nextLine();
            }

            System.out.println("\nPassword Analysis Report:");
            System.out.println("Password\tLen\tUpper\tLower\tDigits\tSpecial\tScore\tStrength");
            for (String pwd : passwords) {
                int[] counts = analyzePassword(pwd);
                int score = calculateScore(pwd, counts);
                String strength = getStrength(score);
                System.out.printf("%-10s %-3d %-5d %-5d %-6d %-7d %-5d %-8s%n",
                        pwd, pwd.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
            }

            System.out.print("\nEnter desired length to generate strong password: ");
            int len = sc.nextInt();
            String generated = generatePassword(len);
            System.out.println("Generated Strong Password: " + generated);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static int[] analyzePassword(String pwd) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < pwd.length(); i++) {
            char ch = pwd.charAt(i);
            if (ch >= 65 && ch <= 90) upper++;
            else if (ch >= 97 && ch <= 122) lower++;
            else if (ch >= 48 && ch <= 57) digit++;
            else if (ch >= 33 && ch <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static int calculateScore(String pwd, int[] counts) {
        int score = 0;
        if (pwd.length() > 8) score += (pwd.length() - 8) * 2;
        for (int c : counts) if (c > 0) score += 10;
        if (pwd.contains("123") || pwd.contains("abc") || pwd.toLowerCase().contains("qwerty")) score -= 10;
        return score;
    }

    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        if (score <= 50) return "Medium";
        return "Strong";
    }

    public static String generatePassword(int len) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}";
        String all = upper + lower + digits + special;

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        for (int i = 4; i < len; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        return shuffle(sb.toString());
    }

    public static String shuffle(String input) {
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }
}
