import java.util.*;

public class Q3_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter text to compress:");
            String text = sc.nextLine();

            char[] uniqueChars = new char[text.length()];
            int[] freq = new int[text.length()];
            int size = countFrequency(text, uniqueChars, freq);

            String[][] map = createMapping(uniqueChars, freq, size);
            String compressed = compressText(text, map, size);
            String decompressed = decompressText(compressed, map, size);

            System.out.println("\nCharacter Frequency:");
            for (int i = 0; i < size; i++) {
                System.out.println(uniqueChars[i] + " -> " + freq[i]);
            }

            System.out.println("\nMapping:");
            for (int i = 0; i < size; i++) {
                System.out.println(map[i][0] + " -> " + map[i][1]);
            }

            System.out.println("\nOriginal: " + text);
            System.out.println("Compressed: " + compressed);
            System.out.println("Decompressed: " + decompressed);
            System.out.println("Compression Efficiency: " +
                    ((100.0 * compressed.length() / text.length())) + "%");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static int countFrequency(String text, char[] chars, int[] freq) {
        int size = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = -1;
            for (int j = 0; j < size; j++) {
                if (chars[j] == c) { idx = j; break; }
            }
            if (idx == -1) {
                chars[size] = c;
                freq[size] = 1;
                size++;
            } else freq[idx]++;
        }
        return size;
    }

    public static String[][] createMapping(char[] chars, int[] freq, int size) {
        String[][] map = new String[size][2];
        for (int i = 0; i < size; i++) {
            map[i][0] = String.valueOf(chars[i]);
            map[i][1] = i + "";
        }
        return map;
    }

    public static String compressText(String text, String[][] map, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String ch = String.valueOf(text.charAt(i));
            for (int j = 0; j < size; j++) {
                if (map[j][0].equals(ch)) sb.append(map[j][1]);
            }
        }
        return sb.toString();
    }

    public static String decompressText(String comp, String[][] map, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < comp.length(); i++) {
            String code = String.valueOf(comp.charAt(i));
            for (int j = 0; j < size; j++) {
                if (map[j][1].equals(code)) sb.append(map[j][0]);
            }
        }
        return sb.toString();
    }
}
