public class NebulaTV extends TV {
    public NebulaTV() {
        super("nebula");
    }

    public void saySomething(String speech) {
        System.out.println(speech);
        writeToLogFile("assistant: " + "\"" + speech + "\"");
    }

    public void processSpeech(String speech) throws SpeechNotUnderstoodException {

        if (speech.equals("increase channel")) {

            if (this.currentChannel < 50) {
            System.out.println("increasing channel");
            this.writeToLogFile("assistant: \"increasing channel\"");
            this.incChannel();
            } else {
                System.out.println("invalid channel, cannot increase");
            }

        } else if (speech.equals("decrease channel")) {

            if (this.currentChannel > 1) {
            System.out.println("decreasing channel");
            this.writeToLogFile("assistant: \"decreasing channel\"");
            this.decChannel();
            } else {
                System.out.println("invalid channel, cannot decrease");
            }

        } else if (speech.equals("increase volume")) {

            if (this.currentVolume < 50) {
            System.out.println("increasing volume");
            this.writeToLogFile("assistant: \"increasing volume\"");
            this.incVolume();
            } else {
                System.out.println("invalid volume, cannot increase");
            }

        } else if (speech.equals("decrease volume")) {

            if (this.currentVolume > 1) {
            System.out.println("decreasing volume");
            this.writeToLogFile("assistant: \"decreasing volume\"");
            this.decVolume();
            } else {
                System.out.println("invalid volume, cannot decrease");
            }

        /*
         * TODO: parse int correctly from speech string
         */
        } else if (speech.contains("change")) {
            int channel = Integer.parseInt(speech);
            if (channel < 1 || channel > 50) {
                System.out.println("invalid channel, cannot change");
            } else {
                System.out.println("changing channel to " + channel);
                this.writeToLogFile("assistant: \"changing channel to " + channel + "\"");
                this.changeChannel(channel);
            }

        } else {
            System.out.println("exception occured");
            throw new SpeechNotUnderstoodException("\"" + speech + "\" is not understood by the voice assistant for " + this.model + " tvs.");
        }
    }
}
