import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

/**
 * This abstract class represents a Smartphone.
 * It implements the VoiceAssistant and Logger interfaces and has methods to take a picture, talk on the phone, process a speech command, say something, write to a log file, and charge the battery.
 * It also has a private method to check if a string can be parsed as an integer.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public abstract class Smartphone implements VoiceAssistant, Logger {

    private double batteryCapacity;
    protected double currentBatteryAmount;
    protected String model;

    /**
     * Constructor for the Smartphone class. 
     * It initializes the battery capacity and model, and sets the current battery amount to the battery capacity.
     *
     * @param batteryCapacity The capacity of the battery.
     * @param model The model of the smartphone.
     */
    public Smartphone(double batteryCapacity, String model) {
        this.batteryCapacity = batteryCapacity;
        this.model = model;
        this.currentBatteryAmount = batteryCapacity;
    }

    /**
     * This abstract method simulates taking a picture with the smartphone.
     */
    public abstract void takePicture();

    /**
     * This abstract method simulates talking on the phone with the smartphone for a certain number of minutes.
     *
     * @param minutes The number of minutes to talk on the phone.
     */
    public abstract void talkOnPhone(int minutes);

    /**
     * This method processes a speech command and performs the corresponding action.
     * If the speech command is "picture", it takes a picture.
     * If the speech command contains "talk", it talks on the phone for a specified number of minutes.
     * If the speech command is not recognized, it writes an exception message to the log file and throws a SpeechNotUnderstoodException.
     *
     * @param speech The speech command to be processed.
     * @throws SpeechNotUnderstoodException If the speech command is not recognized.
     */
    public void processSpeech(String speech) throws SpeechNotUnderstoodException {
        if (speech.equals("picture")) {
            this.takePicture();
        } else if (speech.contains("talk")) {
            // Split the speech command into tokens and parse the number of minutes
            String[] tokens = speech.split(" ");
            int minutes = Integer.parseInt(tokens[1]);
            this.talkOnPhone(minutes);
        } else {
            writeToLogFile("exception: \"" + speech + "\"");
            throw new SpeechNotUnderstoodException(
                    "\"" + speech + "\" is not understood by the voice assistant for " + this.model + " smartphones.");
        }
    }

    /**
     * This method prints a speech to the console and writes it to the log file.
     *
     * @param speech The speech to be printed and logged.
     */
    public void saySomething(String speech) {
        System.out.println(speech);
        writeToLogFile("assistant: \"" + speech + "\"");
    }

    /**
     * This method writes a message to a log file.
     * If the message can be parsed as an integer, it writes it as an integer.
     * Otherwise, it writes it as a UTF string.
     *
     * @param message The message to be written to the log file.
     */
    public void writeToLogFile(String message) {

        try {
            DataOutputStream logger = new DataOutputStream(new FileOutputStream("./" + this.model + ".bin", true)); // true appends to the file
            if (isInt(message)) { // If the message can be parsed as an integer, write it as an integer
                logger.writeInt(Integer.parseInt(message));
            } else { // Otherwise, write it as a UTF string
                logger.writeUTF(message);
            }
            logger.flush();
            logger.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method charges the battery to its full capacity and prints the battery percentage before charging.
     */
    public void charge() {
        double percentage = ((this.currentBatteryAmount / this.batteryCapacity) * 100);
        System.out.printf("The phone battery is at %.1f%%\nCharging\n", percentage);
        this.currentBatteryAmount = this.batteryCapacity;
    }

    /**
     * Returns a string representation of the smartphone.
     *
     * @return A string representation of the smartphone.
     */
    @Override
    public String toString() {
        return "Smartphone [batteryCapacity=" + this.batteryCapacity + ", currentBatteryAmount="
                + this.currentBatteryAmount + ", model=" + this.model + "]";
    }

    /**
     * This method checks if a string can be parsed as an integer.
     *
     * @param s The string to be checked.
     * @return true if the string can be parsed as an integer, false otherwise.
     */
    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) { // If an exception is thrown, the string cannot be parsed as an integer
            return false;
        }
        return true;
    }

}