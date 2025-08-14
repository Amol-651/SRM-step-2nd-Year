import java.util.Scanner;
public class WordLengths {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();
        String[] words = splitText(input);
        String[][] wordWithLengths = getWordsWithLength(words);
        System.out.println("\nWord\tLength");
        for (String[] row : wordWithLengths) {
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
        }

    }
    // Get the length of a string without using length()
    static int getLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (Exception e) {
            return count;
        }
    }
    // Split string into words without using split()
    static String[] splitText(String s) {
        int len = getLength(s);
        int wordCount = 0;
// Count words
        boolean inWord = false;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ' && !inWord) {
                wordCount++;
                inWord = true;
            } else if (s.charAt(i) == ' ') {
                inWord = false;
            }
        }
// Extract words
        String[] words = new String[wordCount];
        int wordIndex = 0;
        String word = "";
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                word += c;
            }
            if ((c == ' ' || i == len - 1) && word.length() > 0) {
                words[wordIndex++] = word;
                word = "";

            }
        }
        return words;
    }
    // Generate a 2D array with word and its length
    static String[][] getWordsWithLength(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getLength(words[i]));
        }
        return result;
    }
}