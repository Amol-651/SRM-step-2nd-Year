import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int charCount = text.replace(" ", "").length();
        int sentenceCount = text.split("[.!?]").length;

        String longestWord = "";
        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : words) {
            word = word.replaceAll("\\W", "");
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        String mostCommon = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                mostCommon = entry.getKey();
                maxFreq = entry.getValue();
            }
        }

        System.out.println("\n--- Text Analysis ---");
        System.out.println("Word Count: " + wordCount);
        System.out.println("Character Count: " + charCount);
        System.out.println("Sentence Count: " + sentenceCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Word: " + mostCommon);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String input = scanner.nextLine();

        String clean = cleanInput(input);
        analyzeText(clean);

        String[] sortedWords = getWordsSorted(clean);
        System.out.println("\nAlphabetically Sorted Words:");
        for (String word : sortedWords) {
            System.out.println(word);
        }

        System.out.print("\nEnter word to search: ");
        String search = scanner.nextLine();
        boolean found = Arrays.asList(sortedWords).contains(search.toLowerCase());
        System.out.println("Word \"" + search + "\" found: " + found);

        scanner.close();
    }
}
