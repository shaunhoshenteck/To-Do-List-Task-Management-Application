package assignment9;

import assignment9.ToDo.ToDoBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class CommandLineProcessor
 */
public class CommandLineProcessor implements ICommandLineProcessor {
  private String csvFile;
  private Set<Integer> completeTodoSet = new HashSet<>();
  private String errorMsgFile;
  private Set<IDisplayOption<ToDo>> displayOptionSet = new HashSet<>();
  private ToDoBuilder tdb = new ToDoBuilder();
  private boolean requestedToAdd = false;
  private boolean requestedDisplay = false;
  private boolean requestedComplete = false;

  /**
   * Constructs a CommandLineProcessor object
   * @param errorMsgFile name of error message file, expressed as a String
   */
  public CommandLineProcessor(String errorMsgFile) {
    this.errorMsgFile = errorMsgFile;
  }

  /**
   * Process the command line arguments
   * @param args command line arguments, expressed as a String list
   * @throws CommandLineException if any of the arguments is invalid
   */
  public void processArgument(String[] args) throws CommandLineException {
    if (checkCSVFile(args)) {
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "--add-todo":
            addProcessor(args);
            requestedToAdd = true;
            break;
          case "--todo-text":
          case "--completed":
          case "--due":
          case "--priority":
          case "--category":
            checkAddTodo(args);
            break;
          case "--complete-todo":
            if (i == args.length - 1) {
              System.out.println("Error: --complete-todo requested but todo id not found");
              printErrorMsg(this.errorMsgFile);
              throw new CommandLineException();
            }
            requestedComplete = true;
            completeProcessor(args[i+1]);
            break;
          case "--display":
            displayProcessor(args);
            requestedDisplay = true;
            break;
          case "--show-incomplete":
          case "--show-category":
          case "--sort-by-date":
          case "--sort-by-priority":
            checkDisplay(args);
            break;
        }
      }
    }
  }

  /**
   * Check if the --csv-file option exists, if exists, get the name of the input csv file
   * @param args command line arguments, expressed as a String list
   * @return true if option exists and the file name also exists and is valid
   * @throws CommandLineException if the option or the csv file name doesn't exist
   */
  private Boolean checkCSVFile(String[] args) throws CommandLineException {
    if (!Arrays.asList(args).contains("--csv-file")) {
      System.out.println("Error: --csv-file is missing");
    } else {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("--csv-file")) {
          if (i == args.length - 1) {
            System.out.println("Error: --csv-file provided but name of csv file not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (!args[i+1].matches(".+\\.csv")) {
            System.out.println("Error: --csv-file provided but name of csv file not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          } else {
            csvFile = args[i+1];
            return true;
          }
        }
      }
      System.out.println("Error: path for csv file not found");
    }
    printErrorMsg(this.errorMsgFile);
    throw new CommandLineException();
  }

  /**
   * Process the request of adding a Todo object
   * @param args command line arguments, expressed as a String list
   * @throws CommandLineException if any of the arguments is invalid
   */
  private void addProcessor(String[] args) throws CommandLineException {
    if (!Arrays.asList(args).contains("--todo-text")) {
      System.out.println("Error: --add-todo is requested but --todo-text not found");
      printErrorMsg(this.errorMsgFile);
      throw new CommandLineException();
    }

    String text = "";
    String completed = "false";
    String dueDate = "?";
    String priority = "?";
    String category = "?";
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--todo-text":
          if (i == args.length - 1) {
            System.out.println("Error: --todo-text provided but description of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (args[i+1].matches("--\\w+")) {
            System.out.println("Error: --todo-text provided but description of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          } else {
            int textIndex = i + 1;
            text = args[textIndex] + " ";
            while (textIndex != args.length - 1) {
              textIndex++;
              if (args[textIndex].matches("--\\w+"))
                break;
              else {
                text = text + args[textIndex] + " ";
              }
            }
            text = text.substring(0, text.length() -1);
          }
          break;
        case "--completed":
          completed = "true";
          break;
        case "--due":
          if (i == args.length - 1) {
            System.out.println("Error: --due provided but due date not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (args[i+1].matches("--\\w+")) {
            System.out.println("Error: --due provided but due date not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          } else {
            dueDate = args[i+1];
          }
          break;
        case "--priority":
          if (i == args.length - 1) {
            System.out.println("Error: --priority provided but priority of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (args[i+1].matches("--\\w+")) {
            System.out.println("Error: --priority provided but priority of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          } else {
            priority = args[i+1];
          }
          break;
        case "--category":
          if (i == args.length - 1) {
            System.out.println("Error: --category provided but category of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (args[i+1].matches("--\\w+")) {
            System.out.println("Error: --category provided but category of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          } else {
            category = args[i+1];
          }
          break;
      }
    }

    tdb.setText(text);
    tdb.setCompleted(completed);
    tdb.setDue(dueDate);
    tdb.setPriority(priority);
    tdb.setCategory(category);
  }

  /**
   * Process the request of completing a Todo object
   * @param arg command line arguments, expressed as a String list
   * @throws CommandLineException if the Todo id is missing
   */
  private void completeProcessor(String arg) throws CommandLineException {
    if (!arg.matches("\\d+")) {
      System.out.println("Error: --complete-todo requested but todo id not found");
      printErrorMsg(this.errorMsgFile);
      throw new CommandLineException();
    }
    completeTodoSet.add(Integer.parseInt(arg));
  }

  private void displayProcessor(String[] args) throws CommandLineException {
    if (Arrays.asList(args).contains("--sort-by-date") &&
        Arrays.asList(args).contains("--sort-by-priority")) {
      System.out.println("Error: --sort-by-date can't be combined with --sort-by-priority");
      printErrorMsg(this.errorMsgFile);
      throw new CommandLineException();
    }


    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--show-incomplete":
          displayOptionSet.add(new FilterByIncomplete());
          break;
        case "--show-category":
          if (i == args.length - 1) {
            System.out.println("Error: --show-category provided but category of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          if (args[i + 1].matches("--\\w+")) {
            System.out.println("Error: --show-category provided but category of the todo not found");
            printErrorMsg(this.errorMsgFile);
            throw new CommandLineException();
          }
          String category = args[i+1];
          displayOptionSet.add(new FilterByCategory(category));
          break;
        case "--sort-by-date":
          displayOptionSet.add(new SortByDate());
          break;
        case "--sort-by-priority":
          displayOptionSet.add(new SortByPriority());
          break;
      }
    }
  }


  /**
   * Check if the --add-todo request exists
   * @param args command line arguments, expressed as a String list
   * @throws CommandLineException if the request doesn't exist
   */
  private void checkAddTodo(String[] args) throws CommandLineException {
    if (!Arrays.asList(args).contains("--add-todo")) {
      System.out.println("Error: --add-todo request not found");
      printErrorMsg(this.errorMsgFile);
      throw new CommandLineException();
    }
  }

  /**
   * Check if the --display request exists
   * @param args command line arguments, expressed as a String list
   * @throws CommandLineException if the request doesn't exist
   */
  private void checkDisplay(String[] args) throws CommandLineException {
    if (!Arrays.asList(args).contains("--display")) {
      System.out.println("Error: --display request not found");
      printErrorMsg(this.errorMsgFile);
      throw new CommandLineException();
    }
  }

  /**
   * Prints out the error message to console
   * @param errorMsgFile the .txt file given for error message
   */
  public void printErrorMsg(String errorMsgFile) {
    FileReader errorFile;
    try {
      errorFile = new FileReader(errorMsgFile);
      BufferedReader bufferedReader = new BufferedReader(errorFile);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
      }
      bufferedReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("A file was not found: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException ioe) {
      System.out.print("Something went wrong: " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  public Set<IDisplayOption<ToDo>> getDisplayOptionSet() {
    return displayOptionSet;
  }

  public String getCsvFile() {
    return csvFile;
  }

  public Set<Integer> getCompleteTodoSet() {
    return completeTodoSet;
  }

  public ToDoBuilder getTdb() {
    return tdb;
  }

  public boolean isRequestedToAdd() {
    return requestedToAdd;
  }

  public boolean isRequestedDisplay() {
    return requestedDisplay;
  }

  public boolean isRequestedComplete() {
    return requestedComplete;
  }
}


