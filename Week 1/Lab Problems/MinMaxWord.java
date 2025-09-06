import java.util.Scanner;
public class MinMaxWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = splitText(input);
        String[][] wordLengths = getWordsWithLength(words);
        int[] minMax = findMinMax(wordLengths);
        System.out.println("Shortest word: " + wordLengths[minMax[0]][0]);
        System.out.println("Longest word: " + wordLengths[minMax[1]][0]);
    }
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
    static String[] splitText(String s) {
        int len = getLength(s);
        int wordCount = 0;
        boolean inWord = false;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ' && !inWord) {
                wordCount++;
                inWord = true;
            } else if (s.charAt(i) == ' ') {
                inWord = false;
            }
        }
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
    static String[][] getWordsWithLength(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getLength(words[i]));
        }
        return result;
    }
    static int[] findMinMax(String[][] wordLengths) {
        int minIdx = 0, maxIdx = 0;
        int min = Integer.parseInt(wordLengths[0][1]);
        int max = min;
        for (int i = 1; i < wordLengths.length; i++) {
            int len = Integer.parseInt(wordLengths[i][1]);
            if (len < min) {
                min = len;
                minIdx = i;
            }
            if (len > max) {
                max = len;
                maxIdx = i;
            }
        }
        return new int[]{minIdx, maxIdx};
    }
}