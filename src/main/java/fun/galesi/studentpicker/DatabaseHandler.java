package fun.galesi.studentpicker;

import java.io.*;
import java.util.ArrayList;

public class DatabaseHandler {

    private static final String DATA_LOCATION = "./PeriodData/";

    private final File file;
    private FileReader reader;
    private BufferedReader bufferedReader;
    private PrintWriter write;

    public DatabaseHandler(String fileName) {
        this.file = new File(DATA_LOCATION, fileName + ".txt");

        try {
            // Create the containing directories and file if it does not exist.
            file.getParentFile().mkdirs();
            file.createNewFile();
            
            write = new PrintWriter(new FileWriter(file, true));
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readLines() {
        ArrayList<String> toReturn = new ArrayList<>();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.print(line + "\n");
                toReturn.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }


    public String read() {
        StringBuilder returnString = new StringBuilder();
        String line = null;
        try {

            while ((line = bufferedReader.readLine()) != null) {
                System.out.print(line + "\n");
                returnString.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString.toString();
    }

    public void write(String toWrite) {
        write.write(toWrite);
        write.close();
    }

    public void clear() {
        try {
            new PrintWriter(file).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
