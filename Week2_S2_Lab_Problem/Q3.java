import java.util.Scanner;
public class Q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter number of iterations (e.g., 1000, 10000, 100000):");
            int iterations = scanner.nextInt();
            PerformanceResult concatResult = testStringConcatenation(iterations);
            PerformanceResult builderResult = testStringBuilder(iterations);
            PerformanceResult bufferResult = testStringBuffer(iterations);
            displayPerformanceTable(concatResult, builderResult, bufferResult);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static PerformanceResult testStringConcatenation(int iterations) {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result = result + "a";
        }

        long end = System.currentTimeMillis();
        return new PerformanceResult("String Concatenation (+)", end - start, result.length());
    }
    public static PerformanceResult testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            builder.append("a");
        }
        long end = System.currentTimeMillis();
        return new PerformanceResult("StringBuilder.append()", end - start, builder.length());
    }
    public static PerformanceResult testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            buffer.append("a");
        }
        long end = System.currentTimeMillis();
        return new PerformanceResult("StringBuffer.append()", end - start, buffer.length());
    }
    public static void displayPerformanceTable(PerformanceResult... results) {
        System.out.printf("%-25s %-15s %-15s\n", "Method Used", "Time (ms)", "Final Length");
        System.out.println("--------------------------------------------------------");
        for (PerformanceResult res : results) {
            System.out.printf("%-25s %-15d %-15d\n", res.methodName, res.timeTaken, res.length);
        }
    }
}
class PerformanceResult {
    String methodName;
    long timeTaken;
    int length;
    public PerformanceResult(String methodName, long timeTaken, int length) {
        this.methodName = methodName;
        this.timeTaken = timeTaken;
        this.length = length;
    }
}