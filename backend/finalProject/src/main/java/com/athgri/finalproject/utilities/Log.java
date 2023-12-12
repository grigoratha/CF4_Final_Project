package com.athgri.finalproject.utilities;

import java.text.SimpleDateFormat;

/**
 * This is a helper function to output message to the console for internal logging purposes.
 */
public class Log {
    final static String initialColor = "\u001B[0m";
    final static String blueColor = "\u001B[34m";
    final static String yellowColor = "\u001B[33m";
    final static String redColor = "\u001B[31m";

    /**
     * Get current timestamp in the format dd/mm/yyyy
     * @return String representation of current timestamp
     */
    static String getCurrentTimestamp() {
        String timestamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new java.util.Date());

        return timestamp;
    }

    /**
     * Output information to the console
     * @param owner Owner of the message
     * @param message Information message
     */
    public static void logInfo(String owner, String message) {
        System.out.println(getCurrentTimestamp() + blueColor + " | INFO | " + initialColor + owner + " | " + message);
    }

    /**
     * Output warning to the console
     * @param owner Owner of the message
     * @param message Warning message
     */
    public static void logWarning(String owner, String message) {
        System.out.println(getCurrentTimestamp() + yellowColor + " | WARNING | " + initialColor + owner + " | " + message);
    }

    /**
     * Output error to the console
     * @param owner Owner of the message
     * @param message Error message
     */
    public static void logError(String owner, String message) {
        System.out.println(getCurrentTimestamp() + redColor + " | ERROR | " + initialColor + owner + " | " + message);
    }
}
