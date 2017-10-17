import java.io.BufferedWriter;
import java.io.FileDescriptor;
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
				
			case 'g':
				
				System.out.println("(optional) Enter Course Number");
				String courseNum = scan.nextLine();
				
				System.out.println("(optional) Enter Year and semester (ie: spring-2017) ");
				String yearSem = scan.nextLine();

				if(yearSem.isEmpty()){
					studentsByGrade(courseNum);

				} else {
					studentByGrade(yearSem, courseNum);
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
	 *		iii. Print the number of students whose data it just read, and how many students already existed in the repository.
	 */
	public void addData(String fileName) {
		
		try {
			organizer.readFile(fileName);
			ArrayList data = organizer.entireArray;
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Invalid File Name ");
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
		int num1 = 0, num2 = 0;
		try {

			fw = new FileWriter(fileName + ".csv");
			buff = new BufferedWriter(fw);
			
			//get students arraylist
			ArrayList<ArrayList<String>> fileData = organizer.getStudentArray();
			year = fileData.get(0).get(3);
			sem = fileData.get(0).get(2);
			classID = fileData.get(0).get(1);
			
			ArrayList<ArrayList<String>> saveData;
			//organizer.printStudents();
			//check
			if (fileData.isEmpty()) {
				System.out.println("There is no data in students file");
			}
			
			
			for (int i = 0; i < fileData.size(); i++) {
				num1 =i;
				if ( fileData.get(i).get(0).equals(studentID ) ) {
					
					for (int j = 5; j < fileData.get(i).size(); j++) {
						num2 =j;
						
						System.out.print(fileData.get(i).get(j).toString() + ", ");
						buff.write( studentID + "," + classID + "," +sem +","+year + "," + fileData.get(i).get(j).toString() + ", ");
						buff.newLine();
					}
					System.out.println("\n");
					
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("i: " + num1 + " j: " + num2);
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

		int iA = 0, iB = 0, iC = 0, iD = 0, iF = 0;
		String sA = "A";
		String sB = "B";
		String sC = "C";
		String sD = "D";
		String sF = "F";

		ArrayList<String[]> fileData = organizer.getArray();

		int[] gradeCount = new int[5];


		try {
			int courseNum = Integer.parseInt( _coursenum );
			//TODO: more stuff

			for(int i = 0; i < fileData.size(); i++){
				

				if(fileData.get(i + 1)[3].equals(sA))
					iA++;
					else if(fileData.get(i + 1)[3].equals(sB))
						iB++;
						else if(fileData.get(i + 1)[3].equals(sC))
							iC++;
							else if(fileData.get(i + 1)[3].equals(sD))
								iD++;
									else if(fileData.get(i + 1)[3].equals(sF))
										iF++;
			}

			gradeCount[0] = iA;
			gradeCount[1] = iB;
			gradeCount[2] = iC;
			gradeCount[3] = iD;
			gradeCount[4] = iF;

		} catch (Exception e) {
			System.out.println( "Couldn't understand Course Number" );
		}
		return gradeCount;
	}
	
	public void studentByGrade( String yearSem, String _coursenum ){
		try {
			int courseNum = Integer.parseInt( _coursenum );
			//TODO: more stuff
		} catch (Exception e) {
			System.out.println( "Couldn't understand Course Number" );
		}
	}
	
	/**
	 * Exit the program (�e� or �E�).
	 */
	public void exitProgram() {
		
		System.out.println( "Exiting Program" );
		AcademicScanner.loop = false;
	}


}
