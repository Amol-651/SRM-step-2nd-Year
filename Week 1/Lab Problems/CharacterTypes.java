import java.util.Scanner;
public class CharacterTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        String[][] result = getCharTypes(input);
        displayTable(result);
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
    static String checkCharType(char ch) {
        if (ch >= 'A' && ch <= 'Z') ch = (char) (ch + 32);
        if (ch >= 'a' && ch <= 'z') {
            if ("aeiou".indexOf(ch) >= 0) return "Vowel";
            return "Consonant";
        }
        return "Not a Letter";
    }
    static String[][] getCharTypes(String s) {
        int len = getLength(s);
        String[][] result = new String[len][2];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            result[i][0] = String.valueOf(c);
            result[i][1] = checkCharType(c);
        }

        return result;
    }
    static void displayTable(String[][] data) {
        System.out.println("Char\tType");
        for (String[] row : data) {
            System.out.println(row[0] + "\t" + row[1]);
        }
    }
}