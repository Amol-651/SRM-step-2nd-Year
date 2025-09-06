import java.util.Scanner;
public class PalindromeCheck {
    // Logic 1: Compare start and end directly
    public static boolean isPalindrome1(String text) {
        int start = 0, end = text.length() - 1;
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) return false;
            start++; end--;
        }
        return true;
    }
    // Logic 2: Recursive
    public static boolean isPalindrome2(String text, int start, int end) {
        if (start >= end) return true;
        if (text.charAt(start) != text.charAt(end)) return false;
        return isPalindrome2(text, start + 1, end - 1);
    }
    // Logic 3: Using char array + reverse
    public static char[] reverseString(String text) {
        int n = text.length();

        char[] rev = new char[n];
        for (int i = 0; i < n; i++) {
            rev[i] = text.charAt(n - 1 - i);
        }
        return rev;
    }
    public static boolean isPalindrome3(String text) {
        char[] original = text.toCharArray();
        char[] reversed = reverseString(text);
        for (int i = 0; i < text.length(); i++) {
            if (original[i] != reversed[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.println("Palindrome check using 3 methods:");
        System.out.println("Method 1: " + (isPalindrome1(text) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Method 2: " + (isPalindrome2(text, 0, text.length() - 1) ? "Palindrome" :
                "Not Palindrome"));
        System.out.println("Method 3: " + (isPalindrome3(text) ? "Palindrome" : "Not Palindrome"));
    }
}