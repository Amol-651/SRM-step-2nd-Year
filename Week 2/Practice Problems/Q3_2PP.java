import java.util.Scanner;
public class Q3_2PP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        System.out.println("Character | ASCII | Type | UpperCase | LowerCase | Diff");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;
            String type = classifyCharacter(ch);
            char upper = Character.toUpperCase(ch);
            char lower = Character.toLowerCase(ch);
            int diff = (int) upper - (int) lower;
            System.out.printf("%9c | %5d | %-16s | %9c | %9c | %4d\n", ch, ascii, type, upper, lower,
                    diff);
        }
        System.out.println("\nASCII Table from 32 to 126:");
        displayASCIITable(32, 126);
        System.out.println("\nEnter a string to encrypt with Caesar Cipher:");
        String text = scanner.nextLine();
        System.out.println("Enter shift value (integer):");
        int shift = scanner.nextInt();
        String encrypted = caesarCipher(text, shift);
        String decrypted = caesarCipher(encrypted, -shift);
        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Decryption successful: " + decrypted.equals(text));
        scanner.close();
    }
    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z')
            return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z')
            return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9')
            return "Digit";
        else
            return "Special Character";
    }
    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {

            return (char)(ch + 32);
        } else if (ch >= 'a' && ch <= 'z') {
            return (char)(ch - 32);
        }
        return ch;
    }
    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                int base = 'A';
                int offset = (ch - base + shift) % 26;
                if (offset < 0) offset += 26;
                sb.append((char)(base + offset));
            } else if (ch >= 'a' && ch <= 'z') {
                int base = 'a';
                int offset = (ch - base + shift) % 26;
                if (offset < 0) offset += 26;
                sb.append((char)(base + offset));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.printf("%3d : %c\t", i, (char)i);
            if ((i - start + 1) % 10 == 0) System.out.println();
        }
        System.out.println();
    }
}