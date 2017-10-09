import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileOrganizer {
	String csvFile = "/Users/mkyong/csv/country.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    
    public void readFile(String file) {
    	
    	 try {

    	        br = new BufferedReader(new FileReader(csvFile));
    	        while ((line = br.readLine()) != null) {

    	            // use comma as separator
    	            String[] country = line.split(cvsSplitBy);

    	            System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

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