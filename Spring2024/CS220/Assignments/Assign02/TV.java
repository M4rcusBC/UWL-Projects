import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

/**
 * This abstract class represents a TV.
 * It implements the Logger interface and has methods to increase/decrease the channel, change the channel, increase/decrease the volume, write to a log file, and return a string representation of the TV.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public abstract class TV implements Logger {

    protected int currentChannel;
    protected int currentVolume;
    protected String model;

    /**
     * Constructor for the TV class.
     *
     * @param model The model of the TV.
     */
    public TV(String model) {

        // Values set according to assignment guidelines
        this.currentChannel = 2;
        this.currentVolume = 10;
        this.model = model;

    }

    /**
     * This method increases the current channel by 1 if it is less than 50 and writes a message to the log file.
     */
    public void incChannel() {

        if (this.currentChannel < 50) {
            this.currentChannel++;
            writeToLogFile("increasing channel to " + currentChannel);
        } else {
            writeToLogFile("cannot increase channel");
        }

    }
    /**
     * This method decreases the current channel by 1 if it is greater than 1 and writes a message to the log file.
     */
    public void decChannel() {
        if (currentChannel > 1) {
            this.currentChannel--;
            writeToLogFile("decreasing channel to " + currentChannel);
        } else {
            writeToLogFile("cannot decrease channel");
        }
    }

    /**
     * This method changes the current channel to a specified channel if it is between 1 and 50 and writes a message to the log file.
     *
     * @param channel The channel to change to.
     */
    public void changeChannel(int channel) {
        if (channel < 1 || channel > 50) {
            writeToLogFile("cannot change channel to " + channel);
        } else {
            this.currentChannel = channel;
            writeToLogFile("changing channel to " + currentChannel);
        }
    }

    /**
     * This method increases the current volume by 1 if it is less than 50 and writes a message to the log file.
     */
    public void incVolume() {
        if (currentVolume < 50) {
            this.currentVolume++;
            writeToLogFile("increasing volume to " + currentVolume);
        } else {
            writeToLogFile("cannot increase volume");
        }
    }

    /**
     * This method decreases the current volume by 1 if it is greater than 1 and writes a message to the log file.
     */
    public void decVolume() {
        if (currentVolume > 1) {
            this.currentVolume--;
            writeToLogFile("decreasing volume to " + currentVolume);
        } else {
            writeToLogFile("cannot decrease volume");
        }
    }

    /**
     * This method writes a message to a log file.
     *
     * @param message The message to be written to the log file.
     */
    public void writeToLogFile(String message) {
        try {
            PrintWriter logger = new PrintWriter(new BufferedWriter(new FileWriter("./" + this.model + ".txt", true))); // true appends to the file
            logger.println(message);
            logger.flush();
            logger.close(); // Flush and close the writer
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representation of the TV.
     *
     * @return A string representation of the TV.
     */
    @Override
    public String toString() {
        return "TV [currentChannel=" + currentChannel + ", currentVolume=" + currentVolume + ", model=" + model + "]";
    }

}