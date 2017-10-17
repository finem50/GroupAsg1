import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * @author Bronwyn Herndon	
 * This class should be looped in the main until it's time to exit the program. 
 * The user will be able to add, save and search through academic grades
 */
public class AcidemicUI {

	FileOrganizer organizer = new FileOrganizer();
	Scanner scan = new Scanner( System.in );
	
	public AcidemicUI() {
		
		System.out.println( "AcidemicScanner" );
		
		organizer.readFile("226-fall-1997.csv");
		organizer.readFile("326-fall-1996.csv");
		organizer.readFile("326-fall-1997.csv");
		
		
//		organizer.printArray();
		
	}
	
	/**
	 * Prints out a main menu and takes keyboard input for instructions
	 */
	public void mainMenu() {
		
		//print menu
		System.out.println( "Main Menu \n a) Add Data \n s) Save data \n g) Student By Grade \n e) Exit" );
		//takes input
		String inp = scan.nextLine();
		inp = inp.toLowerCase();
		char ch = inp.charAt(0);
		
		
		if ( ch=='a' || ch=='s' ||  ch=='g' ||  ch=='e' ) {		
			String file;
			
			switch ( ch ) {			
			
			case 'a':
				System.out.println("Enter File Name: ");
				
				file = scan.nextLine();
				addData(file);
				break;
				
			case 's':
				System.out.println("Enter Student ID: ");
				String id = scan.nextLine();
				
				System.out.println("Enter File To Save To: ");
				file = scan.nextLine();
				
				saveData( id, file );
				break;
				
			case 'g': //case G is for the number of students with a certain grade. 
				
				System.out.println("(optional) Enter Course Number");
				String courseNum = scan.nextLine();
				
				System.out.println("(optional) Enter Year: ");
				String year = scan.nextLine();
				String semester = "";
				if (year != "none") {
					System.out.println("Enter Semester (ie: Spring): ");
					semester = scan.nextLine();
				}
				
				year = year.toLowerCase();
				semester = semester.toLowerCase(); 
				courseNum = courseNum.toLowerCase();
				
				//if else to determine which stuentByGrade to use. 
				if (year.isEmpty() && courseNum.isEmpty() || year.contains("none") && courseNum.contains("none") ) {
					System.out.println("Please enter either year and semester or course");
					
				}else if(year.isEmpty() || year.contains("none") ){
					studentsByGrade(courseNum);

				}else if (courseNum.isEmpty() || courseNum.contains("none")) {
					studentByGrade(year, semester);
					
				}else {
					studentByGrade(year, semester, courseNum);
				}
				break;
				
			case 'e':
				
				exitProgram();
				break;
			}
		}
		else{
			
			System.out.println("Please enter valid input (a, s, g, e)");
		}
	}
	
	/**
	 *  Add data (�a� or �A�):
	 *		i. Take file name from the user (course-semester-year.csv).
	 *		ii. Read the provided file (if it exists), extract the data and add it to the repository along with the course number, semester and year data.
	 *		
	 *		iii. Print the number of students whose data it just read, and how many students already existed in the repository.
	 */
	public void addData(String fileName) {
		
		
		try {
			if (fileName.contains(".csv")) {
				organizer.readFile(fileName);
			}
			else{
				organizer.readFile(fileName + ".csv");
			}
			
			if (organizer.getCounter() == -1) {
				System.out.println("The File was empty");
			}
			else{
				System.out.println( "Files have been read into the repository\nNumber of Newly Added Students: " + organizer.getCounter() + "\n\n" );
			}
		} catch (Exception e) {
			
			System.out.println("Invalid File Name File does not exist ");
		}
		
	}
	
	/**
	 * Save data for a student (�s� or �S�):
	 *		i. Take student ID (e.g. �ashesh�). Note that the student ID may have numbers.
	 *		ii. Take the name of the file where data must be exported/saved.
	 *		iii. Find all data from the repository pertaining to this student, and export it in csv format with the following columns:
	 *			1. Column headings: �Student Id�, �Course�, �Semester�, �Year�, �Assignment name�, �Points�
	 */
	public void saveData(String studentID, String fileName) {
		//151vxw8
		//Variables for Student ID, Year, Semester, and class
		String year;
		String sem; 
		String classID;
		
		
		//Variables for file writing
		FileWriter fw = null; 
		BufferedWriter buff = null;
		System.out.println("Save Data\n");
		
		//Try Catch actual work
		int currentClass = 0;
		try {

			fw = new FileWriter(fileName + ".csv");
			buff = new BufferedWriter(fw);
			
			//get students array list
			ArrayList<ArrayList<String>> fileData = organizer.getStudentArray();
			year = fileData.get(0).get(3);
			sem = fileData.get(0).get(2);
			classID = fileData.get(0).get(1);
			
			//check
			if (fileData.isEmpty()) {
				System.out.println("There is no data in students file");
			}
			
			
			for (int i = 0; i < fileData.size(); i++) {

				if (fileData.get(i).get(0).equalsIgnoreCase("Student ID") ) {
					currentClass = i;
					year = fileData.get(i).get(3);
					sem = fileData.get(i).get(2);
					classID =fileData.get(i).get(1);
							
				}
				if ( fileData.get(i).get(0).equals(studentID ) ) {
					
					for (int j = 5; j < fileData.get(i).size(); j++) {
						
						
						System.out.print(fileData.get(i).get(j).toString() + ", ");
						buff.write( studentID + "," + classID + "," + sem + "," + year + "," + fileData.get(currentClass).get(j) + ", " + fileData.get(i).get(j).toString() + ", ");
						buff.newLine();
					}
					System.out.println("\n");
					
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("This is a terrible error but something went wrong");
			e.printStackTrace();
		}finally {

			try {

				if (buff != null)
					buff.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
	
	/**
	 * Return number of students who got a specific grade in a specific course in a specific semester (�g� or �G�):
	 *		i. Take the course number (e.g. 437). The user must have the option of skipping this input by typing �none�.
	 *		ii. Take the semester and year from the user (e.g. �Fall and �2005�). The user must have the option of skipping this input by typing �none�.
	 *		iii. If both inputs are skipped, the program should return to the menu without doing anything.
	 *		iv. Return an array of integers. The array must be of length 
	 *		v. The first position should store the number of A�s, the next one the number of �B�s and so on.
	 *		
	 *		1. If the course number and semester/year were specified, it should contain data only for that course during that semester/year.
	 *		2. If the course number is missing, it should contain data for all the courses during that semester/year.
	 *		3. If the semester/year is missing, it should contain data for the given course across all semesters.
	 */

	public int[] studentsByGrade( String _coursenum ) {
		ArrayList<String[]> fileData = organizer.getArray();

		int[] gradeCount = new int[fileData.size()];
		int studentCount = 0;

		try {
			int courseNum = Integer.parseInt( _coursenum );
			//TODO: more stuff

			for(int i = 0; i < fileData.size(); i++){
				if (_coursenum.equals(fileData.get(3))){
					
				}
				gradeCount[i] = studentCount;
			}

		} catch (Exception e) {
			System.out.println( "Couldn't understand Course Number" );
		}
		return gradeCount;
	}
	public int[] studentByGrade(String _year, String _semester){
		
		int[] gradeCounter = new int[5];
		
		try {
			
			ArrayList<ArrayList<String>> fileData = organizer.getStudentArray();

			for (int i = 0; i < fileData.size(); i++) {
				if (fileData.get(i).get(2).equals(_semester) && fileData.get(i).get(3).equals(_year)) {					
					
					if (!fileData.get(i).get( fileData.get(i).size() - 1 ).equals( "Total" ) ) {
					
						if ( Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 60 ) {
							gradeCounter[4]++;
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 60 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 70) {
							gradeCounter[3]++;
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 70 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 80) {
							gradeCounter[2]++;
						}
						else if ( Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 80 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 90) {
							gradeCounter[1]++;
							
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 90) {
							gradeCounter[0]++;
						}
					}
					
				}
			}
			
			System.out.println("Students with an A: " + gradeCounter[0] +"\nStudents with a B: " + gradeCounter[1] + "\nStudents with a C: " + gradeCounter[2] + "\nStudens with a D: " + gradeCounter[3] + "\nStudnets with an F: " + gradeCounter[4]);
			return gradeCounter;
			
		} catch (Exception e) {
			return null;
		}
		
	}
	public int[] studentByGrade( String _year, String _semester, String _courseNum ){
		try {
			
			int[] gradeCounter = new int[5];
			
			ArrayList<ArrayList<String>> fileData = organizer.getStudentArray();

			for (int i = 0; i < fileData.size(); i++) {
				if (fileData.get(i).get(1).equals(_courseNum) && fileData.get(i).get(2).equals(_semester) && fileData.get(i).get(3).equals(_year)) {					
					
					if (!fileData.get(i).get( fileData.get(i).size() - 1 ).equals( "Total" ) ) {
					
						if ( Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 60 ) {
							gradeCounter[4]++;
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 60 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 70) {
							gradeCounter[3]++;
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 70 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 80) {
							gradeCounter[2]++;
						}
						else if ( Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 80 && Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) < 90) {
							gradeCounter[1]++;
							
						}
						else if (Double.parseDouble( fileData.get(i).get(fileData.get(i).size() - 1 ) ) >= 90) {
							gradeCounter[0]++;
						}
					}
					
				}
			}
			
			System.out.println("Students with an A: " + gradeCounter[0] +"\nStudents with a B: " + gradeCounter[1] + "\nStudents with a C: " + gradeCounter[2] + "\nStudens with a D: " + gradeCounter[3] + "\nStudnets with an F: " + gradeCounter[4]);
			return gradeCounter;
			
		} catch (NumberFormatException e) {
			System.out.println( "Couldn't understand grade" );
			return null;
		}
	}
	

	/**
	 * Exit the program (�e� or �E�).
	 */
	public void exitProgram() {
		
		System.out.println( "Exiting Program" );
		AcademicScanner.loop = false;
		scan.close();
	}


}
