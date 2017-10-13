public class Student {
    private String firstName;
    private String lastName;
    private long userID;
    private int grade;
    private char letterGrade;


    public Student(String firstName, String lastName, long userID, int grade, char letterGrade){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.grade = grade;
        this.letterGrade = letterGrade;
    }


    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public long getUserID(){
        return userID;
    }

    public void setUserID(long userID){
        this.userID = userID;
    }

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public char getLetterGrade(){
        return letterGrade;
    }

    public void setLetterGrade(char letterGrade){
        this.letterGrade = letterGrade;
    }
    
}
