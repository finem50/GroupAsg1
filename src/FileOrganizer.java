import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//README

/*
 * The files are arranged in an array list of type String[].
 * The following array list has this specific format:
 * 
 * array.get(0) "Index 0"
 * String[0] = Class Number: e.g. 226
 * String[1] = Semester: e.g. fall
 * String[2] = Year: e.g. 1996
 * 
 * After Index 0, each Index n afterwards will have the format of 
 * String[0] = Student ID
 * String[1] = First Name
 * String[2] = Last Name
 * TO MAX: For file names that have a complete name in one of the columns,
 * 			you will need to erase the quotations and extra spaces and also find out
 * 			whether the first name being shown is actually their first name
 * 			or their last name. (You can probably check by writing a loop that 
 * 			cross-references each student's ID number until you find a match between
 * 			the files given to us.
 * String[1] = Quote + 'First Name'
 * String[2] = Space + 'Last Name' (Single quotes because we're not sure whether this 
 * 									is right.)
 * 
 * String[3] = Final Grade
 * String[4, array length] = Grades on assignments (Unnecessary possibly?)
 */

public class FileOrganizer {
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    ArrayList<String[]> array = new ArrayList<String[]>();
    
    String classNumber, semester, year, firstName, lastName, grade;
    String classData[] = new String[3];
    boolean fullNameUsed = false;

    //Method used to read files and allocate data to an 
    //ArrayList of type String[].
    public void readFile(String file) {
    	String fileName = file;
    	int indexLocation;
    	
    	//This code separates the file name and initializes
    	//the list's index 0 to contain information for the
    	//entire array.
    	indexLocation = fileName.indexOf("-");
  	  	classNumber = fileName.substring(0, indexLocation);
  	  	fileName = fileName.substring(indexLocation+1);
  	  	indexLocation = fileName.indexOf("-");
  	  	semester = fileName.substring(0, indexLocation);
  	  	fileName = fileName.substring(indexLocation+1);
  	  	indexLocation = fileName.indexOf(".");
  	  	year = fileName.substring(0, indexLocation);
  	  	
  	  	classData[0] = classNumber;
  	  	classData[1] = semester;
  	  	classData[2] = year;
  	  	
  	  	//This is where we had the data to Index 0.
  	  	array.add(classData);
  	  	
  	  	//Since classData's data is passed by reference (possibly),
  	  	//the array needs to be reinitialized each time we change its values.
  	  	classData = null;
  	  	
    	 try {
    	        br = new BufferedReader(new FileReader(file));
    	        
    	        //The first row holds the column headings for the file.
    	        //These next few lines determine which headings are which to help 
    	        //identify lines in the rest of the file.
    	        int idLoc = -1, oneNameLoc = -1, twoNameLoc = -1, fullNameLoc = -1, gradeLoc = -1;
    	        line = br.readLine();
    	        String[] firstLine = line.split(cvsSplitBy);
    	        int rowLength = firstLine.length;
    	        
    	        //Here we are identifying the index locations of special data
    	        //values from the file.
    	        for(int i = 0; i<firstLine.length; i++) {
    	        	if ((firstLine[i].toLowerCase().indexOf("student id") != -1) || (firstLine[i].toLowerCase().indexOf("user id") != -1)) {
    	        		idLoc = i;
    	        	}
    	        	
    	        	else if(firstLine[i].toLowerCase().indexOf("name") != -1) {
    	        		if(firstLine[i].toLowerCase().indexOf("first") != -1) {
    	        			oneNameLoc = i;
    	        		}
    	        		else if (firstLine[i].toLowerCase().indexOf("last") != -1) {
    	        			twoNameLoc = i;
    	        		}
    	        		else {
    	        			//This boolean value is necessary for reading past the commas
    	        			//in the file for full names.
    	        			fullNameLoc = i;
    	        			fullNameUsed = true;
    	        		}
    	        	}
    	        	else if (firstLine[i].toLowerCase().indexOf("grade") != -1) {
    	        		gradeLoc = i;
    	        	}
    	        }
    	        
    	        int startingIndex = 4;
    	        
    	        //If a complete name is being used in the file,
    	        //one extra comma will be seen as an extra delimiter as we scan
    	        //the file. This condition makes sure that grades and student IDs
    	        //are where they are supposed to be.
    	        if (fullNameUsed == true) {
    	        	rowLength++;
    	        	if (fullNameLoc < gradeLoc) {
    	        		gradeLoc++;
    	        	}
    	        	if (fullNameLoc < idLoc) {
    	        		idLoc++;
    	        	}
    	        }
    	        
    	        //Now that we have the index locations for our specific
    	        //data values, now we loop through the file gathering data
    	        //and inputting them into our array.
    	        while ((line = br.readLine()) != null) {
    	        	classData = new String [rowLength];
    	        	 String[] lineElements = line.split(cvsSplitBy);
    	        	 for(int i=0; i<lineElements.length; i++) {
    	        		 if (i == idLoc) {
    	        			 classData[0] = lineElements[i]; 
    	        		 }
    	        		 else if (i==oneNameLoc) {
    	        			 classData[1] = lineElements[i];
    	        		 }
    	        		 else if(i==twoNameLoc) {
    	        			 classData[2] = lineElements[i];
    	        		 }
    	        		 else if(i==fullNameLoc) {
    	        			 classData[1] = lineElements[i];
    	        		 }
    	        		 else if(i==(fullNameLoc+1)) {
    	        			 classData[2] = lineElements[i];
    	        		 }
    	        		 else if (i == gradeLoc) {
    	        			 classData[3] = lineElements[i];
    	        		 }
    	        		 else {
    	        			 classData[startingIndex] = lineElements[i];
    	        			 startingIndex++;
    	        		 }
    	        	 }
    	        	 
    	        	 array.add(classData);
    	        	 
    	        	 //Necessary deletion of classData.
    	        	 classData = null;
    	        	 classData = new String[rowLength];
    	        	 
    	        	 startingIndex = 4;
    	        }

    	    } catch (FileNotFoundException e) {
    	        e.printStackTrace();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    } finally {
    	        if (br != null) {
    	            try {
    	                br.close();
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	            }
    	        }
    	    }
    	
    }
    
    
    //Getter for our array. To Grab a value, say, 
    //a student's ID from the first row of the array's 1st index:
    //	ArrayList<String[]> exampleList = className.getArray();
    //	String[] firstIndex = exampleList.get(i);
    //	String idNumber = firstIndex[0];
    public ArrayList<String[]> getArray() {
    	return array;
    }
    
    //Unnecessary printing method for our class, but this
    //can be used to help understand our the array has been
    //built.
    public void printArray() {
    	int size = array.size();
    	
    	for (int i = 1; i < size; i++) {
    		String[] example = array.get(i);
        	for(int j = 0; j<example.length; j++) {
        		System.out.print(example[j] + " ");
        	}
        	System.out.println();
    	}
    	
    }
}