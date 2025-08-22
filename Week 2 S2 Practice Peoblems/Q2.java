import java.util.Scanner;
import java.util.HashMap;
public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence with mixed formatting:");
        String input = scanner.nextLine();
        String trimmed = input.trim();
        String replacedSpaces = trimmed.replace(' ', '_');
        String removedDigits = replacedSpaces.replaceAll("\\d", "");
        String[] words = removedDigits.split("_");

        String joined = String.join(" | ", words);
        System.out.println("\nTrimmed: " + trimmed);
        System.out.println("Spaces replaced with underscores: " + replacedSpaces);
        System.out.println("Digits removed: " + removedDigits);
        System.out.println("Words array:");
        for (String w : words) System.out.println(w);
        System.out.println("Joined with \" | \": " + joined);
        String noPunct = removePunctuation(trimmed);
        System.out.println("\nNo punctuation: " + noPunct);
        String capitalized = capitalizeWords(noPunct);
        System.out.println("Capitalized words: " + capitalized);
        String reversed = reverseWordOrder(noPunct);
        System.out.println("Reversed word order: " + reversed);
        System.out.println("Word frequency:");
        countWordFrequency(noPunct);
        scanner.close();
    }
    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) sb.append(w.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        HashMap<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        for (String key : freq.keySet()) {
            System.out.println(key + ": " + freq.get(key));
        }
    }
}