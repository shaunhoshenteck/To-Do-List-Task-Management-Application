package assignment9;

import java.rmi.activation.ActivationGroup_Stub;
import java.util.Set;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class Main represents the driver code for the program
 */
public class Main {
    // extracts from program arguments separated by spaces
    public static void main(String[] args) throws CommandLineException {
        CommandLineProcessor commandLineProcessor = new CommandLineProcessor("error-msg.txt");
        commandLineProcessor.processArgument(args);

        Set<IDisplayOption<ToDo>> displayOptionSet =  commandLineProcessor.getDisplayOptionSet();
        Set<Integer> completeTodoSet = commandLineProcessor.getCompleteTodoSet();
        List<ToDo> todoList = ToDoHelper.parseToDoCSV(commandLineProcessor.getCsvFile());

        if (commandLineProcessor.isRequestedDisplay()) {
            ActualProcessor.displayToDos(todoList, displayOptionSet);
        }

        if (commandLineProcessor.isRequestedToAdd()) {
            ActualProcessor.setID(todoList, commandLineProcessor.getTdb());
            ActualProcessor.addToDo(todoList, commandLineProcessor.getTdb());
        }

        if (commandLineProcessor.isRequestedComplete()) {
            ActualProcessor.setComplete(completeTodoSet, todoList);
        }

        System.out.println();

        ToDoWriter.writeToCSV(ToDoHelper.formattedHeaders(), todoList, "todos.csv");

    }
}
