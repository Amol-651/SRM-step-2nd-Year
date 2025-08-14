import java.util.Scanner;
public class CharFrequencyArray {
    public static String[][] frequencyTable(String text) {
        int[] freq = new int[256]; // ASCII
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }
// Count unique chars
        int uniqueCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] > 0) {
                uniqueCount++;
                freq[text.charAt(i)] = -freq[text.charAt(i)]; // mark visited
            }
        }
        String[][] result = new String[uniqueCount][2];
        int index = 0;
        int[] tempFreq = new int[256];
// reset freq properly
        for (int i = 0; i < text.length(); i++) {
            tempFreq[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (tempFreq[c] != 0) {
                result[index][0] = Character.toString(c);
                result[index][1] = Integer.toString(tempFreq[c]);
                tempFreq[c] = 0; // avoid duplicates
                index++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String[][] table = frequencyTable(text);
        System.out.println("Character frequencies:");
        for (String[] row : table) {
            System.out.println(row[0] + " -> " + row[1]);
        }
    }
}