import java.util.*;

public class Q5_5LP {
    public static void main(String[] args) {
        System.out.println("=== Q5: Student Record / Grades Demo ===");
        Student s = new Student("Aman", "CS101");
        s.addScore("Assignment1", 85);
        s.addScore("Midterm", 72);
        s.addScore("Final", 90);
        System.out.println(s);
        System.out.println("Current GPA-like score: " + s.getWeightedTotal());
        System.out.println("Letter grade: " + s.getLetterGrade());
    }

    static class Student {
        private final String name;
        private final String roll;
        private final Map<String, Double> scores;
        // weights (immutable default)
        private static final Map<String, Double> DEFAULT_WEIGHTS;
        static {
            Map<String, Double> m = new HashMap<>();
            m.put("Assignment1", 0.15);
            m.put("Midterm", 0.35);
            m.put("Final", 0.5);
            DEFAULT_WEIGHTS = Collections.unmodifiableMap(m);
        }

        public Student(String name, String roll) {
            if (name == null || name.isEmpty()) throw new IllegalArgumentException("Bad name");
            this.name = name;
            this.roll = roll;
            this.scores = new HashMap<>();
        }

        public String getName() { return name; }
        public String getRoll() { return roll; }

        public void addScore(String key, double value) {
            if (value < 0 || value > 100) throw new IllegalArgumentException("Bad score");
            scores.put(key, value);
        }

        public double getWeightedTotal() {
            double total = 0.0;
            for (Map.Entry<String, Double> e : DEFAULT_WEIGHTS.entrySet()) {
                Double sc = scores.getOrDefault(e.getKey(), 0.0);
                total += sc * e.getValue();
            }
            return Math.round(total * 100.0) / 100.0;
        }

        public String getLetterGrade() {
            double t = getWeightedTotal();
            if (t >= 90) return "A";
            if (t >= 80) return "B";
            if (t >= 70) return "C";
            if (t >= 60) return "D";
            return "F";
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", roll='" + roll + '\'' +
                    ", scores=" + scores +
                    '}';
        }
    }
}
