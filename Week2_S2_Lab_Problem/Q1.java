import java.util.ArrayList;
import java.util.Scanner;
public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the main text:");
            String mainText = scanner.nextLine();
            System.out.println("Enter the substring to find:");
            String substringToFind = scanner.nextLine();
            System.out.println("Enter the replacement substring:");
            String replacementSubstring = scanner.nextLine();
            ArrayList<Integer> occurrences = findAllOccurrences(mainText, substringToFind);
            String replacedText = manualReplace(mainText, substringToFind, replacementSubstring,
                    occurrences);
            String builtinReplacedText = mainText.replace(substringToFind, replacementSubstring);
            boolean isEqual = compareResults(replacedText, builtinReplacedText);
            System.out.println("\nOriginal Text:\n" + mainText);
            System.out.println("\nManually Replaced Text:\n" + replacedText);
            System.out.println("\nBuilt-in replace() Result:\n" + builtinReplacedText);
            System.out.println("\nDo both methods produce the same result? " + (isEqual ? "Yes" :
                    "No"));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    /**
     * Finds all start indices of the substring occurrences in the main text.
     * @param text The main text
     * @param substring The substring to find
     * @return List of starting indices where substring occurs
     */
    public static ArrayList<Integer> findAllOccurrences(String text, String substring) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            occurrences.add(index);
            index += substring.length();
        }
        return occurrences;
    }
    /**
     * Manually replaces all occurrences of substring in the main text using the occurrences list.
     * @param text The original main text
     * @param substring The substring to replace
     * @param replacement The replacement substring
     * @param occurrences List of start indices where substring occurs
     * @return The resulting string after replacement
     */
    public static String manualReplace(String text, String substring, String replacement,
                                       ArrayList<Integer> occurrences) {
        StringBuilder resultBuilder = new StringBuilder();
        int currentPos = 0;
        int substringLength = substring.length();
        for (int startIndex : occurrences) {
// Append characters from currentPos up to startIndex (not inclusive)
            while (currentPos < startIndex) {
                resultBuilder.append(text.charAt(currentPos));
                currentPos++;
            }
// Skip the substring characters and append the replacement
            resultBuilder.append(replacement);
            currentPos += substringLength;
        }
// Append remaining characters after the last occurrence
        while (currentPos < text.length()) {
            resultBuilder.append(text.charAt(currentPos));
            currentPos++;
        }
        return resultBuilder.toString();
    }
    /**
     * Compares two strings and returns true if they are equal.
     * @param manualResult Result from manual replacement
     * @param builtinResult Result from built-in replace method
     * @return boolean indicating if results match
     */
    public static boolean compareResults(String manualResult, String builtinResult) {
        return manualResult.equals(builtinResult);
    }
}