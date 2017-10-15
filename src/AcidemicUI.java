import java.util.Scanner;

/** 
 * @author Bronwyn Herndon	
 * This class should be looped in the main until it's time to exit the program. 
 * The user will be able to add, save and search through academic grades
 */
public class AcidemicUI {

	Scanner scan = new Scanner( System.in );
	
	public AcidemicUI() {
		
		System.out.println( "AcidemicScanner" );
	}
	
	public void mainMenu() {
		
		System.out.println( "Main Menu \n a) Add \n b) Save \n c) Search \n e) Exit" );
		String inp = scan.nextLine();
	}
	
	/**
	 *  Add data (‘a’ or ‘A’):
	 *		i. Take file name from the user (course-semester-year.csv).
	 *		ii. Read the provided file (if it exists), extract the data and add it to the repository along with the course number, semester and year data.
	 *		iii. Print the number of students whose data it just read, and how many students already existed in the repository.
	 */
	public void addData() {
		
		
	}
	
	/**
	 * Save data for a student (‘s’ or ‘S’):
	 *		i. Take student ID (e.g. “ashesh”). Note that the student ID may have numbers.
	 *		ii. Take the name of the file where data must be exported/saved.
	 *		iii. Find all data from the repository pertaining to this student, and export it in csv format with the following columns:
	 *			1. Column headings: “Student Id”, “Course”, “Semester”, “Year”, “Assignment name”, “Points”
	 */
	public void saveData() {
		
	}
	
	/**
	 * Return number of students who got a specific grade in a specific course in a specific semester (‘g’ or ‘G’):
	 *		i. Take the course number (e.g. 437). The user must have the option of skipping this input by typing “none”.
	 *		ii. Take the semester and year from the user (e.g. “Fall and “2005”). The user must have the option of skipping this input by typing “none”.
	 *		iii. If both inputs are skipped, the program should return to the menu without doing anything.
	 *		iv. Return an array of integers. The array must be of length 
	 *			5. The first position should store the number of A’s, the next one the number of ‘B’s and so on.
	 *		
	 *		1. If the course number and semester/year were specified, it should contain data only for that course during that semester/year.
	 *		2. If the course number is missing, it should contain data for all the courses during that semester/year.
	 *		3. If the semester/year is missing, it should contain data for the given course across all semesters.
	 */
	public void studentsByGrade() {
		
	}
	
	/**
	 *Exit the program (‘e’ or ‘E’).
	 */
	public void exitProgram() {
		
		System.out.println( "Exiting Program" );
		
	}

}
