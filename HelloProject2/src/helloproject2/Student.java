
public class Student {
	

    private String firstName;

    private String lastName;
    
    private double age;
    
    private int grade;
    
    private boolean isCurrent;


    public Student(String setFirstName, String setLastName,
            double setAge, int setGrade, boolean setIsCurrent) {
        this.firstName = setFirstName;
        this.lastName = setLastName;
        this.age = setAge;
        this.grade = setGrade;
        this.isCurrent = setIsCurrent;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public double getAge() {
        return age;
    }

    /**
     * Captura o semestre que o estudante está
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Verifica se o estudante está ativo no sistema
     */
    public boolean checkIsCurrent() {
        return isCurrent;
    }

}
