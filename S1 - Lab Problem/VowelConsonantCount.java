import java.util.Scanner;
public class Program5_VowelConsonantCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        int[] result = countVowelsConsonants(input);
        System.out.println("Vowels: " + result[0]);
        System.out.println("Consonants: " + result[1]);
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
    static int[] countVowelsConsonants(String s) {
        int vowels = 0, consonants = 0;
        int len = getLength(s);
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            String type = checkCharType(ch);
            if (type.equals("Vowel")) vowels++;
            else if (type.equals("Consonant")) consonants++;
        }
        return new int[]{vowels, consonants};
    }
    static String checkCharType(char ch) {
        if (ch >= 'A' && ch <= 'Z') ch = (char) (ch + 32); // Convert to lowercase
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return "Vowel";
            return "Consonant";
        }
        return "Not a Letter";
    }
}