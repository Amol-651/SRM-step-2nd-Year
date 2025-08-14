import java.util.Scanner;
public class StringLength {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.next();
        int length = getLength(input);
        System.out.println("Calculated Length: " + length);
        System.out.println("Built-in Length: " + input.length());
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
}