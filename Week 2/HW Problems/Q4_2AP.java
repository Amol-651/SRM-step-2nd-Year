import java.util.*;

public class Q4_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter expression:");
            String expr = sc.nextLine().replaceAll(" ", "");

            if (!validate(expr)) {
                System.out.println("Invalid Expression");
                return;
            }
            System.out.println("Original Expression: " + expr);
            System.out.println("Result: " + evaluate(expr));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static boolean validate(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (!(Character.isDigit(ch) || "+-*/()".indexOf(ch) >= 0)) return false;
        }
        return true;
    }

    public static int evaluate(String expr) {
        while (expr.contains("(")) {
            int open = expr.lastIndexOf("(");
            int close = expr.indexOf(")", open);
            int val = evaluate(expr.substring(open + 1, close));
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
        }
        return calcSimple(expr);
    }

    public static int calcSimple(String expr) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char ch = expr.charAt(i);
            if (Character.isDigit(ch)) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                nums.add(Integer.parseInt(expr.substring(i, j)));
                i = j;
            } else {
                ops.add(ch);
                i++;
            }
        }
        for (int k = 0; k < ops.size();) {
            if (ops.get(k) == '*' || ops.get(k) == '/') {
                int a = nums.get(k);
                int b = nums.get(k + 1);
                int res = (ops.get(k) == '*') ? a * b : a / b;
                nums.set(k, res);
                nums.remove(k + 1);
                ops.remove(k);
            } else k++;
        }
        int res = nums.get(0);
        for (int k = 0; k < ops.size(); k++) {
            res = (ops.get(k) == '+') ? res + nums.get(k + 1) : res - nums.get(k + 1);
        }
        return res;
    }
}
