public class Q4_2PP {
    public static void main(String[] args) {
        int iterations = 10000;

        System.out.println("=== PERFORMANCE COMPARISON ===");
        long startTime = System.nanoTime();
        String resultString = concatenateWithString(iterations);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        String resultBuilder = concatenateWithStringBuilder(iterations);
        endTime = System.nanoTime();
        System.out.println("StringBuilder append time: " + (endTime - startTime) + " ns");
        startTime = System.nanoTime();
        String resultBuffer = concatenateWithStringBuffer(iterations);
        endTime = System.nanoTime();
        System.out.println("StringBuffer append time: " + (endTime - startTime) + " ns");
        System.out.println("\nDemonstrate StringBuilder methods:");
        demonstrateStringBuilderMethods();
        System.out.println("\nCompare String comparison methods:");
        compareStringComparisonMethods();
// Memory efficiency and thread safety demonstration omitted for brevity
    }
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }
    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        System.out.println("Original: " + sb);
        sb.append("!");
        System.out.println("After append: " + sb);
        sb.insert(6, "Java ");
        System.out.println("After insert: " + sb);
        sb.delete(6, 11);
        System.out.println("After delete: " + sb);
        sb.deleteCharAt(5);
        System.out.println("After deleteCharAt: " + sb);
        sb.reverse();
        System.out.println("After reverse: " + sb);
        sb.replace(0, 5, "Hey");
        System.out.println("After replace: " + sb);
        sb.setCharAt(0, 'h');
        System.out.println("After setCharAt: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(50);
        System.out.println("Capacity after ensureCapacity(50): " + sb.capacity());
        sb.trimToSize();
        System.out.println("Capacity after trimToSize(): " + sb.capacity());
    }
    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("str1 == str2 : " + (str1 == str2)); // true, same literal pool
        System.out.println("str1 == str3 : " + (str1 == str3)); // false, different objects
        System.out.println("str1.equals(str3) : " + str1.equals(str3)); // true, content equals
        System.out.println("str1.equalsIgnoreCase(\"hello\") : " + str1.equalsIgnoreCase("hello"));
        System.out.println("str1.compareTo(str3) : " + str1.compareTo(str3));
        System.out.println("str1.compareToIgnoreCase(\"HELLO\") : " +
                str1.compareToIgnoreCase("HELLO"));
        System.out.println("\nExplanation:");
        System.out.println("- '==' compares references, not contents.");
        System.out.println("- equals() compares contents.");
        System.out.println("- equalsIgnoreCase() compares contents ignoring case.");
        System.out.println("- compareTo() gives lex order (0 if equal).");
        System.out.println("- compareToIgnoreCase() compares ignoring case.");
    }
}