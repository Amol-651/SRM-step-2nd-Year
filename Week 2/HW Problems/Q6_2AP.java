import java.util.*;

public class Q6_2AP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter number of files:");
            int n = sc.nextInt(); sc.nextLine();
            String[] files = new String[n];
            for (int i = 0; i < n; i++) {
                files[i] = sc.nextLine();
            }
            organizeFiles(files);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static void organizeFiles(String[] files) {
        System.out.println("File\t\tCategory\tNew Name");
        for (String f : files) {
            String ext = getExtension(f);
            String cat = getCategory(ext);
            String newName = cat + "_" + System.currentTimeMillis();
            System.out.printf("%-10s %-10s %-10s%n", f, cat, newName);
        }
    }

    public static String getExtension(String file) {
        int idx = file.lastIndexOf('.');
        if (idx == -1) return "";
        return file.substring(idx + 1);
    }

    public static String getCategory(String ext) {
        if (ext.equalsIgnoreCase("txt") || ext.equalsIgnoreCase("doc")) return "Document";
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")) return "Image";
        if (ext.equalsIgnoreCase("mp4")) return "Video";
        return "Other";
    }
}
