import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StudentAnalytics {
    
    private final static String[] firstNames = {"Willian", "Francisco", "Veronildo", "Emanuel", "Orrana", "Hériclys"};
    private final static String[] lastNames = {"de Oliveira", "das Chagas", "Batista", "Ibiapino", "Lhaynher", "Sousa"};
    
     public static int getNCores() {

         return Runtime.getRuntime().availableProcessors();

    }

     
    public static Student[] generateStudentData() {
            int N_STUDENTS = 7000;
            int N_CURRENT_STUDENTS = 2000;

            Student[] students = new Student[N_STUDENTS];
            Random r = new Random();

            for (int s = 0; s < N_STUDENTS; s++) {
                String firstName = firstNames[r.nextInt(firstNames.length)];
                String lastName = lastNames[r.nextInt(lastNames.length)];
                double age = r.nextDouble() * 100.0;
                int grade = 1 + r.nextInt(100);
                boolean current = (s < N_CURRENT_STUDENTS);

                students[s] = new Student(firstName, lastName, age, grade, current);
            }

            return students;
        }
    
    protected static void PrintResults(long timeInNanos, double sum) {
        System.out.println(" terminou em " + timeInNanos + " milisegundos e média de idade dos alunos = " + sum);
    }
    
    public static double averageAgeOfEnrolledStudentsSeq(Student[] studentArray) {
        long startTime = System.nanoTime();
        List<Student> activeStudents = new ArrayList<Student>();

        for (Student s : studentArray) {
            if (s.checkIsCurrent()) {
                activeStudents.add(s);
            }
        }
        String nome = " ", nomeAUX, nomeCompara;
        int quantidade = 0, aux = 0;
        double ageSum = 0.0;
        for (Student s : activeStudents) {
            ageSum += s.getAge();
            nomeAUX = s.getFirstName();
            for(int i=0;i < (int) activeStudents.size();i++) {
                nomeCompara = activeStudents.get(i).getFirstName();
                if(nomeAUX == nomeCompara) {
                    aux= aux + 1;
                }
            }
            if(quantidade < aux) {
                quantidade = aux;
                nome = nomeAUX;
            }
            aux = 0;
        }
        
        System.out.println("Nome " + nome + " repetiu " + quantidade + " vezes. ");
        
        long timeInNanos = System.nanoTime() - startTime;
        PrintResults(timeInNanos, ageSum / (double) activeStudents.size());
        return ageSum / (double) activeStudents.size();
    }

    public static double averageAgeOfEnrolledStudentsParallelStream(
            Student[] studentArray) {
    
        long startTime = System.nanoTime();
        
        double ageSum = Stream.of(studentArray)
                .parallel()
                .filter(s -> s.checkIsCurrent())
                .mapToDouble(a -> a.getAge())
                .average()
                .getAsDouble();
        
        long timeInNanos = System.nanoTime() - startTime;
        PrintResults(timeInNanos, ageSum);

        return ageSum;
    }

    
    public static void main(String[] args) {
        
        int ncores = getNCores();
        Student[] students = generateStudentData();
        
        System.out.println("Número de Cores: " + ncores);
        averageAgeOfEnrolledStudentsSeq(students);
        averageAgeOfEnrolledStudentsParallelStream(students);
        

    }

}