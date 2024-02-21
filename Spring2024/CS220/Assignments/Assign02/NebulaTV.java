/**
 * This class represents a NebulaTV, which is a type of TV that implements the VoiceAssistant interface.
 * It has methods to process speech commands and perform actions like changing channels or adjusting volume.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public class NebulaTV extends TV implements VoiceAssistant {

    /**
     * Constructor for the NebulaTV class.
     * It calls the superclass constructor with the model name "nebula".
     */
    public NebulaTV() {
        super("nebula");
    }

    /**
     * This method prints a speech to the console and writes it to the log file.
     *
     * @param speech The speech to be printed and logged.
     */
    public void saySomething(String speech) {
        System.out.println(speech);
        writeToLogFile("assistant: " + "\"" + speech + "\"");
    }

    /**
     * This method processes a speech command and performs the corresponding action.
     * It can increase or decrease the channel or volume, or change the channel to a specific number.
     * If the speech command is not recognized, it throws a SpeechNotUnderstoodException.
     *
     * @param speech The speech command to be processed.
     * @throws SpeechNotUnderstoodException If the speech command is not recognized.
     */
    public void processSpeech(String speech) throws SpeechNotUnderstoodException {

        if (speech.equals("increase channel")) {

            // If the current channel is less than 50, increase the channel
            if (this.currentChannel < 50) {
            System.out.println("increasing channel");
            this.writeToLogFile("assistant: \"increasing channel\"");
            this.incChannel();
            } else {
                System.out.println("invalid channel, cannot increase");
            }

        } else if (speech.equals("decrease channel")) {

            // If the current channel is greater than 1, decrease the channel
            if (this.currentChannel > 1) {
            System.out.println("decreasing channel");
            this.writeToLogFile("assistant: \"decreasing channel\"");
            this.decChannel();
            } else {
                System.out.println("invalid channel, cannot decrease");
            }

        } else if (speech.equals("increase volume")) {

            // If the current volume is less than 50, increase the volume
            if (this.currentVolume < 50) {
            System.out.println("increasing volume");
            this.writeToLogFile("assistant: \"increasing volume\"");
            this.incVolume();
            } else {
                System.out.println("invalid volume, cannot increase");
            }

        } else if (speech.equals("decrease volume")) {

            // If the current volume is greater than 1, decrease the volume
            if (this.currentVolume > 1) {
            System.out.println("decreasing volume");
            this.writeToLogFile("assistant: \"decreasing volume\"");
            this.decVolume();
            } else {
                System.out.println("invalid volume, cannot decrease");
            }

        } else if (speech.contains("change")) {
            
            // Split the speech into tokens and parse the channel number
            String[] channelTokens = speech.split(" ");
            int channelNum = Integer.parseInt(channelTokens[1]);
            if (channelNum < 1 || channelNum > 50) {
                System.out.println("invalid channel, cannot change");
            } else {
                System.out.println("changing channel to " + channelNum);
                this.writeToLogFile("assistant: \"changing channel to " + channelNum + "\"");
                this.changeChannel(channelNum);
            }

        } else {

            // If the speech command is not recognized, throw a SpeechNotUnderstoodException
            System.out.println("exception occured");
            throw new SpeechNotUnderstoodException("\"" + speech + "\" is not understood by the voice assistant for " + this.model + " tvs.");
        }
    }
}