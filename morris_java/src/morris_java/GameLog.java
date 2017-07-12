/**
 * 
 */
package morris_java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameLog {
	private BufferedWriter writer;
	
	public GameLog(String logFileName) {
		createLogFile(logFileName);
    }
	
	public GameLog() {
		String logFileName = generateLogFileName();
		createLogFile(logFileName);
	}
	
	public void addToLog(String msg) {
		System.out.println(msg);
		try {
			writer.write(msg + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createLogFile(String logFileName) {
		String logDir = "log";
		new File(logDir).mkdirs();
        try {
            File logFile = new File(logDir + File.separator + logFileName);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private String generateLogFileName() {
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeLog + ".log";
	}
}
