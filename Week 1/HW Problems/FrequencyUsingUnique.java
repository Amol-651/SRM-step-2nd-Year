import java.util.Scanner;
public class FrequencyUsingUnique {
    // Reuse uniqueCharacters from Question 2
    public static char[] uniqueCharacters(String text) {
        int n = text.length();
        char[] result = new char[n];

        int k = 0;
        for (int i = 0; i < n; i++) {
            char current = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == current) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                result[k++] = current;
            }
        }
        char[] finalResult = new char[k];
        for (int i = 0; i < k; i++) finalResult[i] = result[i];
        return finalResult;
    }
    public static String[][] frequencyUsingUnique(String text) {
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }
        char[] unique = uniqueCharacters(text);
        String[][] result = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            result[i][0] = Character.toString(unique[i]);
            result[i][1] = Integer.toString(freq[unique[i]]);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        String[][] table = frequencyUsingUnique(text);
        System.out.println("Character frequencies:");
        for (String[] row : table) {

            System.out.println(row[0] + " -> " + row[1]);
        }
    }
}