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
    ArrayList<String[]> array = new ArrayList();
    
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
  	  	
    	 try {
    	        br = new BufferedReader(new FileReader(file));
    	        
    	        //The first row holds the column headings for the file.
    	        //These next few lines determine which headings are which to help 
    	        //identify lines in the rest of the file.
    	        
    	        int idLoc, oneNameLoc, twoNameLoc, fullNameLoc, gradeLoc;
    	        line = br.readLine();
    	        String[] firstLine = line.split(cvsSplitBy);
    	        
    	        for(int i = 0; i<firstLine.length; i++) {
    	        	if (firstLine[i].toLowerCase().indexOf("id") != -1) {
    	        		idLoc = i;
    	        		return;
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
    	        }
    	        
    	        
    	        
    	        while ((line = br.readLine()) != null) {
    	        	 String[] lineElements = line.split(cvsSplitBy);
    	        	 for(int i=0; i<lineElements.length; i++) {
    	        		 System.out.println(lineElements[i]);
    	        	 }
//    	        	 System.out.println(country);
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
}