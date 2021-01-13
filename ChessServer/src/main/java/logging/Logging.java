package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {
    private String path;
    private Date date = new Date();
    public Logging(){

        SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
        path = "LOGGING/" + Date.format(date) + " logging.txt";
    }

    private void createFile(){
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void writeToLog(Object text, String Sender){
        createFile();
        try {
            String time = getTime();
            FileWriter myWriter = new FileWriter(path, true);
            myWriter.write(time + " [From: " + Sender + "] " + text.toString() + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    private String getTime(){
        SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss");
        date = new Date();
        return "[Time: " + Time.format(date) + "]";
    }


}
