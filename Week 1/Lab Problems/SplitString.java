import java.util.Scanner;
public class SplitString {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();
        String[] manualSplit = splitText(input);
        String[] builtInSplit = input.split(" ");
        System.out.println("Manual Split:");
        for (String word : manualSplit) System.out.println(word);
        System.out.println("\nSplit match: " + compareArrays(manualSplit, builtInSplit));
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
        int wordCount = 1;
        for (int i = 0; i < len; i++) if (s.charAt(i) == ' ') wordCount++;
        int[] spaceIndexes = new int[wordCount + 1];
        int index = 0;
        spaceIndexes[index++] = -1;
        for (int i = 0; i < len; i++) if (s.charAt(i) == ' ') spaceIndexes[index++] = i;
        spaceIndexes[index] = len;
        String[] words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            String word = "";
            for (int j = spaceIndexes[i] + 1; j < spaceIndexes[i + 1]; j++) {
                word += s.charAt(j);
            }
            words[i] = word;
        }

        return words;
    }
    static boolean compareArrays(String[] a, String[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (!a[i].equals(b[i])) return false;
        return true;
    }
}