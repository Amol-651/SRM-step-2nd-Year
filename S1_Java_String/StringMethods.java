import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();

        System.out.print("Describe your programming experience in one sentence: ");
        String experience = scanner.nextLine();

        // Processing
        String[] names = fullName.split(" ");
        String firstName = names[0];
        String lastName = names[names.length - 1];

        int charCount = experience.replace(" ", "").length();

        String upperLang = language.toUpperCase();

        // Output
        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Experience Sentence Character Count (excluding spaces): " + charCount);
        System.out.println("Favorite Language (Uppercase): " + upperLang);

        scanner.close();
    }
}
