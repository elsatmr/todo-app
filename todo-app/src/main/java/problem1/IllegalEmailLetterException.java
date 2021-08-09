package problem1;

public class IllegalEmailLetterException extends Exception {
  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   */
  public IllegalEmailLetterException() {
    super("Error: When providing --email, it has to be followed by --email-template."
        + "When providing --letter, it also has to be followed by --letter-template"
        + " All templates have to be in .txt file format\n"
        + "Usage:\n"
        + "--email Generate email messages. If this option is provided, "
        + "then --email-template must also be provided.\n"
        + "--email-template <path/to/file> A filename for the email template. "
        + "--letter Generate letters. If this option is provided, "
        + "then --letter-template must also be provided.\n"
        + "--letter-template <path/to/file> A filename for the letter template.\n"
        + "--output-dir <path/to/folder> The folder to store all generated files. "
        + "This option is required.\n"
        + "--csv-file <path/to/folder> The CSV file to process. This option is required.\n"
        + "Examples:\n"
        + "--email --email-template email-template.txt --output-dir emails "
        + "--csv-file customer.csv\n"
        + "--letter --letter-template letter-template.txt --output-dir letters "
        + "--csv-file customer.csv");
  }
}