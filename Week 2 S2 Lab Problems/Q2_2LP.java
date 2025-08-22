import java.util.Scanner;
public class Q2_2LP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the text:");
            String inputText = scanner.nextLine();

            String upperCaseText = convertToUpperCase(inputText);
            String lowerCaseText = convertToLowerCase(inputText);
            String titleCaseText = convertToTitleCase(inputText);
            boolean upperCheck = compareWithBuiltIn(upperCaseText, inputText.toUpperCase());
            boolean lowerCheck = compareWithBuiltIn(lowerCaseText, inputText.toLowerCase());
            System.out.println("\nOriginal Text: " + inputText);
            System.out.println("Uppercase (Custom): " + upperCaseText);
            System.out.println("Uppercase (Built-in): " + inputText.toUpperCase());
            System.out.println("Uppercase matches built-in? " + (upperCheck ? "Yes" : "No"));
            System.out.println("\nLowercase (Custom): " + lowerCaseText);
            System.out.println("Lowercase (Built-in): " + inputText.toLowerCase());
            System.out.println("Lowercase matches built-in? " + (lowerCheck ? "Yes" : "No"));
            System.out.println("\nTitle Case (Custom): " + titleCaseText);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static String convertToUpperCase(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                result.append((char)(ch - 32));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static String convertToLowerCase(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char)(ch + 32));

            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static String convertToTitleCase(String text) {
        StringBuilder result = new StringBuilder();
        boolean startOfWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                result.append(ch);
                startOfWord = true;
            } else {
                if (startOfWord) {
// convert to uppercase if lowercase
                    if (ch >= 'a' && ch <= 'z') {
                        result.append((char)(ch - 32));
                    } else {
                        result.append(ch);
                    }
                    startOfWord = false;
                } else {
// convert to lowercase if uppercase
                    if (ch >= 'A' && ch <= 'Z') {
                        result.append((char)(ch + 32));
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
    public static boolean compareWithBuiltIn(String custom, String builtin) {
        return custom.equals(builtin);
    }
}