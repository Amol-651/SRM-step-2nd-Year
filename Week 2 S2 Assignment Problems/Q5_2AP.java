import java.util.*;

public class Q5_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter CSV data (end with 'END'):");
            StringBuilder input = new StringBuilder();
            while (true) {
                String line = sc.nextLine();
                if (line.equals("END")) break;
                input.append(line).append("\n");
            }
            String[][] data = parseCSV(input.toString());
            displayData(data);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static String[][] parseCSV(String text) {
        List<String[]> rows = new ArrayList<>();
        int start = 0;
        List<String> fields = new ArrayList<>();
        for (int i = 0; i < text.length();) {
            if (text.charAt(i) == '\n') {
                fields.add(text.substring(start, i));
                rows.add(fields.toArray(new String[0]));
                fields.clear();
                i++;
                start = i;
            } else if (text.charAt(i) == ',') {
                fields.add(text.substring(start, i));
                i++;
                start = i;
            } else i++;
        }
        return rows.toArray(new String[0][]);
    }

    public static void displayData(String[][] data) {
        System.out.println("\nFormatted CSV Data:");
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col.trim() + "\t");
            }
            System.out.println();
        }
        System.out.println("\nTotal Records: " + data.length);
    }
}
