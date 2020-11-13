<p align="center">
  <span><strong>To-Do List Task Management Application</strong></span>
  <br>
  <span>Project Dates: Mar 2020 - Apr 2020</span>
</p>
• Designed a fully functional command-line task management application in Java with basic Model View Controller architecture.
<br>
• Allows users to create and organize their 'to-do' lists.
<br>
• Implemented a command-line parser to determine what the client wants and allows flexibility in how the 'to-do' list is generated.
<br>
• Used a Builder pattern to create 'to-do' objects.
<br>
• Implemented efficient sorting and filtering methodologies for the 'to-do' list using knowledge of lambda functions.
<br>
• Used knowledge of lambda functions to implement efficient sorting and filtering methodologies for the 'to-do' list.
<br>
• Performed code reviews and exchanged information with teammates through Git.
<br>
<br>
<br>
<p align="center">
  <span><strong>The program accepts the following command line arguments in any order:</strong></span>
</p>
<br>
1) --csv-file <path/to/file>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The CSV file containing the todos. This option is required.
<br>
2) --add-todo Add a new todo.    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If this option is provided, then --todo-text must also be provided.
<br>
3) --todo-text <description of todo>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A description of the todo.
<br>
4) --completed    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Optional) Sets the completed status of a new todo to true.
  <br>
5) --due <due date>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Optional) Sets the due date of a new todo. You may choose how the date should be formatted.
  <br>
6) --priority <1, 2, or 3>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Optional) Sets the priority of a new todo. The value can be 1, 2, or 3.
  <br>
7) --category <a category name>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined.
  <br>
8) --complete-todo <id>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mark the Todo with the provided ID as complete.
  <br>
9) --display Display todos.    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If none of the following optional arguments are provided, displays all todos.
  <br>
10) --show-incomplete (Optional)    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If --display is provided, only incomplete todos should be displayed.
  <br>
11) --show-category <category>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Optional) If --display is provided, only todos with the given category should be displayed.
  <br>
12) --sort-by-date (Optional)    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by-priority.
<br>
13) --sort-by-priority (Optional)    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.
<br>
 <br>
A user can request the program perform all three tasks (add, complete, and display) in one run of the program. They may only add one Todo at a time but they may complete multiple Todos by repeating the --complete-todo option for each Todo to complete. E.g. --complete-todo 5 --complete-todo 2 would complete the Todo with ID 5 and the
Todo with ID 2.
