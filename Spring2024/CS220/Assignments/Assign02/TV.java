import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public abstract class TV implements Logger {

    protected int currentChannel;
    protected int currentVolume;
    protected String model;

    public TV(String model) {

        // Values set according to assignment guidelines
        this.currentChannel = 2;
        this.currentVolume = 10;
        this.model = model;

    }

    public void incChannel() {

        if (this.currentChannel < 50) {
            this.currentChannel++;
            writeToLogFile("increasing channel to " + currentChannel);
        } else {
            writeToLogFile("cannot increase channel");
        }

    }

    public void decChannel() {
        if (currentChannel > 1) {
            this.currentChannel--;
            writeToLogFile("decreasing channel to " + currentChannel);
        } else {
            writeToLogFile("cannot decrease channel");
        }
    }

    public void changeChannel(int channel) {
        if (channel < 1 || channel > 5) {
            writeToLogFile("cannot change channel to " + channel);
        } else {
            this.currentChannel = channel;
            writeToLogFile("changing channel to " + currentChannel);
        }
    }

    public void incVolume() {
        if (currentVolume < 50) {
            this.currentVolume++;
            writeToLogFile("increasing volume to " + currentVolume);
        } else {
            writeToLogFile("cannot increase volume");
        }
    }

    public void decVolume() {
        if (currentVolume > 1) {
            this.currentVolume--;
            writeToLogFile("decreasing volume to " + currentVolume);
        } else {
            writeToLogFile("cannot decrease volume");
        }
    }

    public void writeToLogFile(String message) {
        try {
            PrintWriter logger = new PrintWriter(new BufferedWriter(new FileWriter(this.model + ".txt", true)));
            logger.println("\"" + message + "\"");
            logger.flush();
            logger.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TV [currentChannel=" + currentChannel + ", currentVolume=" + currentVolume + ", model=" + model + "]";
    }

}

