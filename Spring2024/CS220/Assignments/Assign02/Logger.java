/**
 * This is an interface for a Logger.
 * It defines a method to write a message to a log file.
 *
 * @author Marcus Clements
 * @since 2024-02-21
 */
public interface Logger {
    /**
     * This method writes a message to a log file.
     *
     * @param message The message to be written to the log file.
     */
    public void writeToLogFile(String message);
}