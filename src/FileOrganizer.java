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
    ArrayList array = new ArrayList();

    
    public void readFile(String file) {
    	
    	 try {

    	        br = new BufferedReader(new FileReader(file));
    	        while ((line = br.readLine()) != null) {
    	        	 String[] lineElements = line.split(cvsSplitBy);
    	        	 for(int i=0; i<lineElements.length; i++) {
    	        		 System.out.println(lineElements[i]);
    	        	 }
//    	        	 System.out.println(country);
    	        }

//    	        while ((line = br.readLine()) != null) {
//
//    	            // use comma as separator
//    	            String[] country = line.split(cvsSplitBy);
//
//    	            System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//
//    	        }

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