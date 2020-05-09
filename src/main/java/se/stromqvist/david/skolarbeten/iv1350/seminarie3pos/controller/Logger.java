package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Logs exceptions to pos-logfile.txt
 * @author David
 */
public class Logger {
    private static final String LOG_FILE = "pos-logfile.txt";
    
    private PrintWriter logFile;
    
    public Logger () throws IOException
    {
        logFile = new PrintWriter(new FileWriter(LOG_FILE, true));
    }
    
    /**
     * Writes a log entry in the log file pos-logfile.txt
     * @param exception the exception to be logged.
     */
    public void logException(Exception exception)
    {
        StringBuilder stringToLog = new StringBuilder();
        stringToLog.append(getTime());
        stringToLog.append(", An exception was thrown: ");
        stringToLog.append(exception.getMessage());
        logFile.println(stringToLog);
        exception.printStackTrace(logFile);
    }

    private String getTime()
    {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return time.format(formatter);
    }
}
