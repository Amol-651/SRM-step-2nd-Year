public class Q1_2PP {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";
// 1. Original length including spaces
        int originalLength = sampleText.length();
// 2. Remove leading and trailing spaces and new length
        String trimmedText = sampleText.trim();
        int trimmedLength = trimmedText.length();
// 3. Character at index 5
        char charAt5 = sampleText.charAt(5);
// 4. Extract substring "Programming"
        String substringProg = sampleText.substring(sampleText.indexOf("Programming"),
                sampleText.indexOf("Programming") + "Programming".length());
// 5. Index of the word "Fun"
        int indexFun = sampleText.indexOf("Fun");
// 6. Check if contains "Java" (case-sensitive)
        boolean containsJava = sampleText.contains("Java");
// 7. Check if starts with "Java" after trimming
        boolean startsWithJava = trimmedText.startsWith("Java");
// 8. Check if ends with exclamation mark
        boolean endsWithExclamation = sampleText.trim().endsWith("!");
// 9. Convert entire string to uppercase
        String upperCaseText = sampleText.toUpperCase();
// 10. Convert entire string to lowercase
        String lowerCaseText = sampleText.toLowerCase();
// Count vowels
        int vowelCount = countVowels(sampleText);
// Find all occurrences of character 'a'
        System.out.println("Positions of 'a' in sampleText:");
        findAllOccurrences(sampleText, 'a');

// Display all results
        System.out.println("=== String Built-In Methods Demo ===");
        System.out.println("Original String: \"" + sampleText + "\"");
        System.out.println("1. Original length including spaces: " + originalLength);
        System.out.println("2. Trimmed length: " + trimmedLength);
        System.out.println("3. Character at index 5: '" + charAt5 + "'");
        System.out.println("4. Substring \"Programming\": \"" + substringProg + "\"");
        System.out.println("5. Index of \"Fun\": " + indexFun);
        System.out.println("6. Contains \"Java\": " + containsJava);
        System.out.println("7. Starts with \"Java\" after trimming: " + startsWithJava);
        System.out.println("8. Ends with '!': " + endsWithExclamation);
        System.out.println("9. Uppercase: \"" + upperCaseText + "\"");
        System.out.println("10. Lowercase: \"" + lowerCaseText + "\"");
        System.out.println("Vowel count: " + vowelCount);
    }
    // Method to count vowels using charAt()
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
    // Method to find all occurrences of a character and print their positions
    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}