public class Student {

    private String firstName;
    private String lastName;
    private String userID;
    private String studentID;
    private String assignmentName;
    private String grade;

    public Student(String firstName, String lastName, String userID, String studentID,
                         String assignmentName, String grade){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.studentID = studentID;
        this.assignmentName = assignmentName;
        this.grade = grade;
    }

    //Getters and setters for first name, last name, userID/studentID, assignment name, and grade
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

    public String getUserID(){
        return userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getStudentID(){
        return studentID;
    }

    public void setStudentID(String studentID){
        this.studentID = studentID;
    }

    public String getAssignmentName(){
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName){
        this.assignmentName = assignmentName;
    }

    public String getGrade(){
        return grade;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }
}
