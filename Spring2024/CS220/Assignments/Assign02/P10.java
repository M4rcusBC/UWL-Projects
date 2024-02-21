/**
 * This class represents a P10 smartphone, which is a type of Smartphone.
 * It has methods to take a picture, take a video, and talk on the phone, all of which reduce the current battery amount.
 * It also has a method to process a speech command and perform the corresponding action.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public class P10 extends Smartphone {

    /**
     * Constructor for the P10 class.
     * It calls the superclass constructor with the battery capacity of 2900 and the model name "p10".
     */
    public P10() {
        super(2900, "p10");
    }

    /**
     * This method simulates taking a picture with the P10 smartphone.
     * It prints a message to the console, reduces the current battery amount by 150, and writes "picture" to the log file.
     */
    public void takePicture() {
        System.out.println(this.model + ": Taking picture at 1500 x 1125 pixels.");
        this.currentBatteryAmount -= 150;
        writeToLogFile("picture\n");
    }

    /**
     * This method simulates taking a video with the P10 smartphone for a certain number of seconds.
     * It prints a message to the console, reduces the current battery amount by 5 times the number of seconds, 
     * and writes "video: " followed by the number of seconds to the log file.
     *
     * @param seconds The number of seconds to take a video.
     */
    public void takeVideo(int seconds) {
        System.out.println(model + ": Taking video on phone for " + seconds + " seconds.");
        this.currentBatteryAmount -= (5 * seconds);
        writeToLogFile("video: ");
        writeToLogFile(String.valueOf(seconds));
        writeToLogFile("\n");
    }

    /**
     * This method simulates talking on the phone with the P10 smartphone for a certain number of minutes.
     * It prints a message to the console, reduces the current battery amount by 10 times the number of minutes, 
     * and writes "talk: " followed by the number of minutes to the log file.
     *
     * @param minutes The number of minutes to talk on the phone.
     */
    public void talkOnPhone(int minutes) {
        System.out.println(this.model + ": Talking on phone for " + minutes + " minutes.");
        this.currentBatteryAmount -= (10 * minutes);
        writeToLogFile("talk: ");
        writeToLogFile(String.valueOf(minutes));
        writeToLogFile("\n");
    }

    /**
     * This method processes a speech command and performs the corresponding action.
     * If the speech command contains "video", it takes a video for a certain number of seconds.
     * Otherwise, it calls the superclass's processSpeech method.
     *
     * @param speech The speech command to be processed.
     * @throws SpeechNotUnderstoodException If the speech command is not recognized by the superclass' processSpeech method.
     */
    @Override
    public void processSpeech(String speech) throws SpeechNotUnderstoodException {
        if (speech.contains("video")) {
            // Split the speech command into tokens and parse the number of seconds
            String[] tokens = speech.split(" ");
            int seconds = Integer.parseInt(tokens[1]);
            this.takeVideo(seconds);
        } else {
            super.processSpeech(speech);
        }
    }

}