import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOrganizer {
	String csvFile = "/Users/mkyong/csv/country.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    ArrayList<String[]> array = new ArrayList<String[]>();
    
    String classNumber, semester, year, firstName, lastName, grade;
    String classData[] = new String[3];
    boolean fullNameUsed = false;

    
    public void readFile(String file) {
    	String fileName = file;
    	int number;
    	
    	number = fileName.indexOf("-");
  	  	classNumber = fileName.substring(0, number);
  	  	fileName = fileName.substring(number+1);
  	  	number = fileName.indexOf("-");
  	  	semester = fileName.substring(0, number);
  	  	fileName = fileName.substring(number+1);
  	  	number = fileName.indexOf(".");
  	  	year = fileName.substring(0, number);
  	  	
  	  	classData[0] = classNumber;
  	  	classData[1] = semester;
  	  	classData[2] = year;
  	  	
  	  	array.add(classData);
  	  	
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
    	        classData = new String [rowLength];
    	        
    	        for(int i = 0; i<firstLine.length; i++) {
    	        	if (firstLine[i].toLowerCase().indexOf("id") != -1) {
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
    	        			fullNameLoc = i;
    	        			fullNameUsed = true;
    	        		}
    	        	}
    	        	else if (firstLine[i].toLowerCase().indexOf("grade") != -1) {
    	        		gradeLoc = i;
    	        	}
    	        }
    	        
    	        int startingIndex = 4;
    	        if (fullNameUsed == true) {
    	        	if (fullNameLoc < gradeLoc) {
    	        		gradeLoc++;
    	        	}
    	        	if (fullNameLoc < idLoc) {
    	        		idLoc++;
    	        	}
    	        }
    	        
    	        while ((line = br.readLine()) != null) {
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