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

    
    public void readFile(String file) {
    	String fileName = file;
    	int number;
    	
    	number = fileName.indexOf("-");
  	  	classNumber = fileName.substring(0, number);
  	  	fileName = fileName.substring(number+1);
  	  	number = fileName.indexOf("-");
  	  	semester = fileName.substring(0, number);
  	  	fileName = fileName.substring(number+1);
  	  	year = fileName;
  	  	
  	  	classData[0] = classNumber;
  	  	classData[1] = semester;
  	  	classData[2] = year;
  	  	
  	  	array.add(classData);
  	  	
    	 try {
    		 file.indexOf("-");
    	        br = new BufferedReader(new FileReader(file));
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