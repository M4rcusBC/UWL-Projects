/**
 * This class represents a P8 smartphone, which is a type of Smartphone.
 * It has methods to take a picture and talk on the phone, both of which reduce the current battery amount.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public class P8 extends Smartphone {
   
    /**
     * Constructor for the P8 class.
     * It calls the superclass constructor with the battery capacity of 2000 and the model name "p8".
     */
    public P8() {
        super(2000, "p8");
    }

    /**
     * This method simulates taking a picture with the P8 smartphone.
     * It prints a message to the console, reduces the current battery amount by 200, and writes "picture" to the log file.
     */
    public void takePicture() {
        System.out.println(this.model + ": Taking picture at 1200 x 900 pixels.");
        this.currentBatteryAmount -= 200;
        writeToLogFile("picture\n");
    }

    /**
     * This method simulates talking on the phone with the P8 smartphone for a certain number of minutes.
     * It prints a message to the console, reduces the current battery amount by 20 times the number of minutes, 
     * and writes "talk: " followed by the number of minutes to the log file.
     *
     * @param minutes The number of minutes to talk on the phone.
     */
    public void talkOnPhone(int minutes) {
        System.out.println(this.model + ": Talking on phone for " + minutes + " minutes.");
        this.currentBatteryAmount -= (20 * minutes);
        writeToLogFile("talk: ");
        writeToLogFile(String.valueOf(minutes));
        writeToLogFile("\n");
    }

}