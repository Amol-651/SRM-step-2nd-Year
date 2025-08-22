import java.util.*;
public class Q6_2LP {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the text to format:");
            String text = scanner.nextLine();
            System.out.println("Enter desired line width (number):");
            int width = scanner.nextInt();
            scanner.nextLine(); // consume newline
            String[] words = splitIntoWords(text);
            List<String> justifiedLines = justifyText(words, width);
            List<String> centeredLines = centerAlignText(justifiedLines, width);
            System.out.println("\nOriginal Text:\n" + text);
            System.out.println("\nLeft-Justified Text:");
            displayFormattedText(justifiedLines);
            System.out.println("\nCenter-Aligned Text:");
            displayFormattedText(centeredLines);
            long timeConcatStart = System.nanoTime();
            String concatResult = formatTextWithConcatenation(words, width);
            long timeConcatEnd = System.nanoTime();
            long timeBuilderStart = System.nanoTime();
            StringBuilder builderResult = new StringBuilder();
            for (String line : justifiedLines) {
                builderResult.append(line).append("\n");
            }
            long timeBuilderEnd = System.nanoTime();
            System.out.println("\nPerformance Comparison (Justify Text):");
            System.out.println("String Concatenation time (ns): " + (timeConcatEnd -
                    timeConcatStart));
            System.out.println("StringBuilder time (ns): " + (timeBuilderEnd - timeBuilderStart));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Method to split text into words without using split()
    public static String[] splitIntoWords(String text) {
        List<String> wordsList = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (start != i) {
                    wordsList.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        return wordsList.toArray(new String[0]);
    }
    // Method to justify text to given width
    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (count + 1 + words[last].length() > width) break;
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder line = new StringBuilder();
            int diff = last - index - 1;
// if last line or only one word in the line, left justify
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i != last - 1) line.append(" ");
                }
// fill the rest spaces
                int remaining = width - line.length();
                for (int i = 0; i < remaining; i++) {
                    line.append(" ");
                }
            } else {

// calculate spaces evenly distributed
                int spaces = (width - count) / diff;
                int extraSpaces = (width - count) % diff;
                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i < last - 1) {
                        for (int s = 0; s <= spaces; s++) {
                            line.append(" ");
                        }
                        if (extraSpaces > 0) {
                            line.append(" ");
                            extraSpaces--;
                        }
                    }
                }
            }
            lines.add(line.toString());
            index = last;
        }
        return lines;
    }
    // Method to center-align the justified text lines
    public static List<String> centerAlignText(List<String> lines, int width) {
        List<String> centeredLines = new ArrayList<>();
        for (String line : lines) {
            int padding = (width - line.trim().length()) / 2;
            StringBuilder centered = new StringBuilder();
            for (int i = 0; i < padding; i++) {
                centered.append(" ");
            }
            centered.append(line.trim());
            centeredLines.add(centered.toString());
        }
        return centeredLines;
    }
    // Method to format text using String concatenation (for performance comparison)
    public static String formatTextWithConcatenation(String[] words, int width) {
        String result = "";
        int index = 0;
        while (index < words.length) {

            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (count + 1 + words[last].length() > width) break;
                count += 1 + words[last].length();
                last++;
            }
            String line = "";
            int diff = last - index - 1;
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    line += words[i];
                    if (i != last - 1) line += " ";
                }
                int remaining = width - line.length();
                for (int i = 0; i < remaining; i++) {
                    line += " ";
                }
            } else {
                int spaces = (width - count) / diff;
                int extraSpaces = (width - count) % diff;
                for (int i = index; i < last; i++) {
                    line += words[i];
                    if (i < last - 1) {
                        for (int s = 0; s <= spaces; s++) {
                            line += " ";
                        }
                        if (extraSpaces > 0) {
                            line += " ";
                            extraSpaces--;
                        }
                    }
                }
            }
            result += line + "\n";
            index = last;
        }
        return result;
    }

    // Method to display formatted text with line numbers and char count
    public static void displayFormattedText(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.printf("%2d: %s | Length: %d\n", i + 1, line, line.length());
        }
    }
}