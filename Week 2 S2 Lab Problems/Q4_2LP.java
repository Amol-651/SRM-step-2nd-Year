import java.util.Scanner;
public class Q4_2LP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the text to encrypt:");
            String originalText = scanner.nextLine();
            System.out.println("Enter the shift value:");
            int shift = scanner.nextInt();
            scanner.nextLine(); // consume newline
            String encryptedText = encryptText(originalText, shift);
            String decryptedText = decryptText(encryptedText, shift);
            displayAsciiValues("Original Text", originalText);
            displayAsciiValues("Encrypted Text", encryptedText);
            displayAsciiValues("Decrypted Text", decryptedText);

            boolean isValid = validateDecryption(originalText, decryptedText);
            System.out.println("\nDecryption validation: " + (isValid ? "Success" : "Failed"));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static String encryptText(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
// uppercase letter wrap-around
                char shifted = (char) ((ch - 'A' + shift) % 26);
                if (shifted < 0) shifted += 26;
                encrypted.append((char) (shifted + 'A'));
            } else if (ch >= 'a' && ch <= 'z') {
// lowercase letter wrap-around
                char shifted = (char) ((ch - 'a' + shift) % 26);
                if (shifted < 0) shifted += 26;
                encrypted.append((char) (shifted + 'a'));
            } else {
// non-alphabetic unchanged
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }
    public static String decryptText(String text, int shift) {
        return encryptText(text, -shift); // Reverse shift to decrypt
    }
    public static void displayAsciiValues(String label, String text) {
        System.out.println("\n" + label + " with ASCII values:");
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i) + "(" + (int) text.charAt(i) + ") ");
        }

        System.out.println();
    }
    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }
}