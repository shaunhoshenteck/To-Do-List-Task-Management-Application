package assignment9;

/**
 * Interface for CommandLineProcessor
 */
public interface ICommandLineProcessor {

  /**
   * Process the command line arguments
   * @param args command line arguments, expressed as a String list
   * @throws CommandLineException if any of the arguments is invalid
   */
  void processArgument(String[] args) throws CommandLineException;

  /**
   * Prints out the error message to console
   * @param errorMsgFile the .txt file given for error message
   */
  void printErrorMsg(String errorMsgFile);

  /**
   * Getter for input csv file
   * @return name of the input csv file, as a String
   */
  String getCsvFile();

}
