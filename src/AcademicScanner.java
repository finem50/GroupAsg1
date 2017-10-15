import java.util.ArrayList;

public class AcademicScanner {

    public static void main(String[] args) {
        String file226F97 = "226-fall-1997.csv";
        String file326F96 = "326-fall-1996.csv";
        String file326F97 = "326-fall-1997.csv";

        FileOrganizer org = new FileOrganizer();
//
//        ArrayList<String[]> exampleList = org.getArray();
//        String[] firstIndex = exampleList.get(i);

        //Read files
        org.readFile(file226F97);
        org.readFile(file326F96);
        org.readFile(file326F97);

        //Write to one mega-file
        org.writeCSVFile();
    }
}
