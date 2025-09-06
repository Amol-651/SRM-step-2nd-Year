import java.util.Scanner;
public class UniqueCharacters {
    // a) Method to find length without using length()
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
// reached end of string
        }
        return count;
    }
    // b) Method to find unique characters and return them as char[]
    public static char[] uniqueCharacters(String text) {
        int n = getLength(text);
        char[] result = new char[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            char current = text.charAt(i);
            boolean isUnique = true;
// Check previous characters
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
// Create new array with only unique chars
        char[] finalResult = new char[k];
        for (int i = 0; i < k; i++) {
            finalResult[i] = result[i];

        }
        return finalResult;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        char[] unique = uniqueCharacters(text);
        System.out.print("Unique characters: ");
        for (char c : unique) {
            System.out.print(c + " ");
        }
    }
}