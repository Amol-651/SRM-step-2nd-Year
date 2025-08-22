import java.util.*;

public class Q1_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter a sentence:");
            String sentence = sc.nextLine();

            String[] dictionary = {"apple", "banana", "grape", "orange", "mango", "pineapple"};

            String[] words = splitSentence(sentence);
            System.out.println("\nSpell Check Report:");
            System.out.println("Word\t\tSuggestion\tDistance\tStatus");

            for (String word : words) {
                if (word.isEmpty()) continue;
                String suggestion = findClosestWord(word, dictionary);
                int distance = calculateDistance(word, suggestion);
                String status = (distance == 0) ? "Correct" : "Misspelled";
                System.out.printf("%-10s %-10s %-10d %-10s%n", word, suggestion, distance, status);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // Method to split sentence without using split()
    public static String[] splitSentence(String sentence) {
        List<String> wordsList = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (ch == ' ' || ch == ',' || ch == '.' || ch == '!' || ch == '?') {
                if (i > start) {
                    wordsList.add(sentence.substring(start, i).toLowerCase());
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            wordsList.add(sentence.substring(start).toLowerCase());
        }
        return wordsList.toArray(new String[0]);
    }

    // Calculate distance between two words
    public static int calculateDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int distance = Math.abs(len1 - len2);
        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    // Find closest matching word from dictionary
    public static String findClosestWord(String word, String[] dictionary) {
        String closestWord = word;
        int minDistance = Integer.MAX_VALUE;
        for (String dictWord : dictionary) {
            int dist = calculateDistance(word, dictWord);
            if (dist < minDistance) {
                minDistance = dist;
                closestWord = dictWord;
            }
        }
        return closestWord;
    }
}
