# Jackson User Guide

## Table of Content
- [Jackson User Guide](#jackson-user-guide)
  - [Table of Content](#table-of-content)
  - [Features](#features)
    - [Adding todo task: `todo`](#adding-todo-task-todo)
    - [Adding deadline task: `deadline`](#adding-deadline-task-deadline)
    - [Adding event task: `event`](#adding-event-task-event)
    - [Removing a task: `delete`](#removing-a-task-delete)
    - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
    - [Finding task by a keyword: `find`](#finding-task-by-a-keyword-find)
    - [Showing tasks: `list`](#showing-tasks-list)
    - [Exiting the program: `exit`, `bye`](#exiting-the-program-exit-bye)
  - [Command Summary](#command-summary)


## Features
> Notes about the command format:
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> e.g. in `deadline /by DATE`, `DATE` is a parameter which can be used as `deadline /by 2025-01-01`.
> 
> - Items in square brackets are optional.
> e.g `DATE [TIME]` can be used as `2025-01-01 04:00` or as `2025-01-01`.
> 
> - Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `bye`) will be ignored.
> e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.
> 
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### Adding todo task: `todo`

Add a todo with description.

Format: `todo DESCRIPTION`

Example: `todo hw1`

### Adding deadline task: `deadline`

Add a deadline task with date and time being the deadline of the task.

Format: `deadline DESCRIPTION /by DATE [TIME]`

Example: 
 - `deadline hw1 /by 2025-09-30`
 - `deadline hw2 /by 2025-10-02 12:00`
  
Description:
 - `DATE` should be in the format of `YYYY-MM-DD`.
 - `TIME` should be in the format of `HH:MM`.


### Adding event task: `event`

Add a event task with starting date and time and ending date and time.

Format: `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]`

Example: 
 - `event activity1 /from 2025-09-30 /to 2025-10-01`
 - `event activity2 /from 2025-03-02 10:00 /to 2025-10-02`
 - `event vacation /from 2025-06-02 10:00 /to 2025-10-02 12:00`
  
Description:
 - `DATE` should be in the format of `YYYY-MM-DD`.
 - `TIME` should be in the format of `HH:MM`.
 - The end time should be after the start time.

### Removing a task: `delete`

Delete a task from the list.

Format: `delete TASK_INDEX`

Example: 
 - `delete 1`
 - `delete 2`
  
Description:
 - The `TASK_INDEX` should be an integer and is in the valid range of current taks.


### Marking a task as done: `mark`

Mark a task a done.

Format: `mark TASK_INDEX`

Example:
 - `mark 1`
 - `mark 10`

Description:
 - The `TASK_INDEX` should be an integer and is in the valid range of current taks.
 - It is Ok to mark a marked task again. The task will stay marked.


### Marking a task as not done: `unmark`

Mark a task a not done.

Format: `unmark TASK_INDEX`

Example:
 - `unmark 1`
 - `unmark 10`

Description:
 - The `TASK_INDEX` should be an integer and is in the valid range of current taks.
 - It is Ok to unmark a not marked task. The task will stay not done.


### Finding task by a keyword: `find`

Find the tasks that contain keywords in description.

Format: `find KEYWORD`

Example:
 - `find hw`
 - `find vacation`

Description:
 - The `KEYWORD` can contain space.

### Showing tasks: `list`

### Exiting the program: `exit`, `bye`

Exits the program.

Format: `exit` or `bye`


## Command Summary

| Action | Format, Example |
| ------ | --------------- |
| **Add** |  `todo DESCRIPTION` <br> `deadline DESCRIPTION /by DATE [TIME]` <br> `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]` |
| **Mark** | `mark TASK_INDEX` |
| **Unmark** | `unmark TASK_INDEX` |
| **Delete** | `delete TASK_INDEX` |
| **Exit**   | `exit`, `bye` |
| **Find**  | `find KEYWORD`|
| **List**   | `list` |