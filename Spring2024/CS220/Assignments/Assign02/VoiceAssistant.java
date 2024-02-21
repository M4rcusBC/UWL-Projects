/**
 * This interface represents a VoiceAssistant.
 * It defines methods to process a speech command and say something.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public interface VoiceAssistant {

   /**
     * This method processes a speech command and performs the corresponding action.
     *
     * @param speech The speech command to be processed.
     * @throws SpeechNotUnderstoodException If the speech command is not recognized.
     */
   public void processSpeech(String speech) throws SpeechNotUnderstoodException;

   /**
     * This method prints speech to the console and writes it to the log file.
     *
     * @param speech The speech to be printed and logged.
     */
   public void saySomething(String speech); 

}