package problem1;

import java.text.ParseException;

public class Main {

  public static void main(String[] args) throws Exception {
    try {
      CommandLineParser clp = new CommandLineParser(args);
      FlagFactory ff = new FlagFactory();
      ff.produceFlags(clp.getFlags());
      FlagProcessor flagP = new FlagProcessor(ff.getFlags());
      String csvFile = flagP.generateCSVPath();
      FileProcessor fileP = new FileProcessor(csvFile);
      fileP.readCSVFile();
      ToDoList initialList = fileP.createInitialTodoList();
      ToDoListController(flagP, initialList);
      fileP.updateCsvFile(initialList);
    }
    catch (Exception a){
      System.out.println(a.toString());
    }
  }

  private static void ToDoListController(FlagProcessor flagP, ToDoList initialList)
      throws ParseException {
    try {
      if (flagP.needToAddTodo()) {
        Todo newTodo = flagP.createTodo();
        initialList.addTodo(newTodo);
      }
      flagP.needsToMarkComplete();
      for (int idx : flagP.getIdxToMarkCompleteList()) {
        String id = flagP.idToMarkComplete(idx);
        initialList.markTodoComplete(id);
      }
      flagP.toggleOtherDisplayCommands();
      flagP.toggleOnlyDisplayAllCommand();
      if (flagP.isNeedsToDisplay()) {
        initialList.display();
      }
      if (flagP.isNeedsToShowIncomplete()) {
        initialList.showIncomplete();
      }
      if (flagP.getCategoryToDisplay() != null) {
        initialList.showCategory(flagP.getCategoryToDisplay());
      }
      if (flagP.isNeedsToSortDate()) {
        initialList.sortByDate();
      }
      if (flagP.isNeedsToSortPriority()) {
        initialList.sortByPriority();
      }
      if (flagP.needToAddTodo()) {
        Todo newTodo = flagP.createTodo();
        initialList.addTodo(newTodo);
      }
    }
    catch (ParseException e){
      System.out.println(e.toString());
    }
  }
}