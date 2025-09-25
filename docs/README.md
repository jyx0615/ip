# Jackson User Guide

**Jackson** is a desktop application for managing your tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Jackson can get your task management done faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `jackson.jar` file from [here](https://github.com/jyx0615/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Jackson task manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jackson.jar` command to run the application.
5. Type the command in the terminal and press Enter to execute it. e.g. typing `list` and pressing Enter will show all your tasks.
6. Refer to the [Features](#features) below for details of each command.

## Table of Content
- [Jackson User Guide](#jackson-user-guide)
  - [Quick Start](#quick-start)
  - [Table of Content](#table-of-content)
  - [Features](#features)
    - [Showing available commands: `help`](#showing-available-commands-help)
    - [Adding todo task: `todo`](#adding-todo-task-todo)
    - [Adding deadline task: `deadline`](#adding-deadline-task-deadline)
    - [Adding event task: `event`](#adding-event-task-event)
    - [Removing a task: `delete`](#removing-a-task-delete)
    - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
    - [Finding task by a keyword: `find`](#finding-task-by-a-keyword-find)
    - [Showing tasks: `list`](#showing-tasks-list)
    - [Exiting the program: `exit`, `bye`](#exiting-the-program-exit-bye)
  - [Data Storage](#data-storage)
  - [Command Summary](#command-summary)
  - [FAQ](#faq)
  - [Known Issues](#known-issues)


## Features
> **Notes about the command format:**
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

### Showing available commands: `help`

Shows a list of all available commands and their basic usage.

**Format:** `help`

**Expected output:**
```
Here are the available commands:
1. todo <description>
2. deadline <description> /by <date and time>
3. event <description> /from <start date and time> /to <end date and time>
4. list
5. list deadline/event before/after <date> [time]
6. mark <task number>
7. unmark <task number>
8. delete <task number>
9. find <keyword>
10. bye or exit
```

**Details:**
- The `help` command provides a quick reference for all available commands
- Shows the basic syntax and examples for each command type
- Includes formatting guidelines for dates and times


### Adding todo task: `todo`

Adds a todo task with a description.

**Format:** `todo DESCRIPTION`

**Example:** 
```
todo read book
todo submit assignment
```

**Expected output:**
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### Adding deadline task: `deadline`

Adds a deadline task with a description and deadline date/time.

**Format:** `deadline DESCRIPTION /by DATE [TIME]`

**Examples:** 
```
deadline submit report /by 2025-09-30
deadline project presentation /by 2025-10-02 14:00
```

**Expected output:**
```
Got it. I've added this task:
  [D][ ] submit report (by: Sep 30 2025)
Now you have 2 tasks in the list.
```

**Details:**
- `DATE` should be in the format of `YYYY-MM-DD`
- `TIME` should be in the format of `HH:MM` (24-hour format)
- If time is not specified, the task is treated as due by the end of the day


### Adding event task: `event`

Adds an event task with a description and start/end date/time.

**Format:** `event DESCRIPTION /from START_DATE [START_TIME] /to END_DATE [END_TIME]`

**Examples:** 
```
event team meeting /from 2025-09-30 /to 2025-09-30
event conference /from 2025-10-02 09:00 /to 2025-10-02 17:00
event vacation /from 2025-12-20 /to 2025-12-30
```

**Expected output:**
```
Got it. I've added this task:
  [E][ ] team meeting (from: Sep 30 2025 to: Sep 30 2025)
Now you have 3 tasks in the list.
```

**Details:**
- `DATE` should be in the format of `YYYY-MM-DD`
- `TIME` should be in the format of `HH:MM` (24-hour format)
- The end date/time should be after the start date/time
- If time is not specified, defaults to the start/end of the day

### Removing a task: `delete`

Deletes a task from your task list.

**Format:** `delete TASK_INDEX`

**Examples:** 
```
delete 1
delete 3
```

**Expected output:**
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

**Details:**
- `TASK_INDEX` must be a positive integer within the valid range of current tasks
- Use `list` command to see the current task indices


### Marking a task as done: `mark`

Marks a task as completed.

**Format:** `mark TASK_INDEX`

**Examples:**
```
mark 1
mark 2
```

**Expected output:**
```
Nice! I've marked this task as done:
  [T][X] read book
```

**Details:**
- `TASK_INDEX` must be a positive integer within the valid range of current tasks
- Marking an already completed task will have no additional effect


### Marking a task as not done: `unmark`

Marks a task as not completed.

**Format:** `unmark TASK_INDEX`

**Examples:**
```
unmark 1
unmark 2
```

**Expected output:**
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

**Details:**
- `TASK_INDEX` must be a positive integer within the valid range of current tasks
- Unmarking an already incomplete task will have no additional effect


### Finding task by a keyword: `find`

Finds tasks that contain the specified keyword in their description.

**Format:** `find KEYWORD`

**Examples:**
```
find book
find meeting
find project
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][ ] read book
3. [D][ ] book report (by: Oct 15 2025)
```

**Details:**
- `KEYWORD` can contain spaces
- Only tasks with descriptions containing the exact keyword will be found

### Showing tasks: `list`

Shows all tasks or tasks that meet certain criteria.

**Format:** 
- `list` - Shows all tasks
- `list deadline/event before/after DATE [TIME]` - Shows filtered tasks

**Examples:**
```
list
list deadline after 2025-09-30
list event before 2025-12-25 15:00
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: Sep 30 2025)
3. [E][X] team meeting (from: Oct 1 2025 to: Oct 1 2025)
```

**Details:**
- `DATE` should be in the format of `YYYY-MM-DD`
- `TIME` should be in the format of `HH:MM` (24-hour format)
- When filtering, only deadlines or events matching the date criteria will be shown

### Exiting the program: `exit`, `bye`

Exits the Jackson application.

**Format:** `exit` or `bye`

**Expected output:**
```
Bye. Hope to see you again soon!
```

## Data Storage

Jackson automatically saves your tasks to a data file located at `./data/jackson.txt`. The data file is created automatically when you first run Jackson.

- Tasks are saved automatically after each command
- No manual save is required
- The data file uses a simple text format for easy backup and portability
- If the data file is corrupted, Jackson will show an error message on startup

## Command Summary

| Action | Format | Example |
|--------|--------|---------|
| **Help** | `help` | `help` |
| **Add Todo** | `todo DESCRIPTION` | `todo read book` |
| **Add Deadline** | `deadline DESCRIPTION /by DATE [TIME]` | `deadline submit report /by 2025-09-30` |
| **Add Event** | `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]` | `event meeting /from 2025-10-01 14:00 /to 2025-10-01 16:00` |
| **Mark** | `mark TASK_INDEX` | `mark 1` |
| **Unmark** | `unmark TASK_INDEX` | `unmark 1` |
| **Delete** | `delete TASK_INDEX` | `delete 2` |
| **Find** | `find KEYWORD` | `find meeting` |
| **List All** | `list` | `list` |
| **List Filtered** | `list TYPE before/after DATE [TIME]` | `list deadline after 2025-09-30` |
| **Exit** | `exit` or `bye` | `bye` |

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Simply copy the `./data/jackson.txt` file to the same location on your new computer.

**Q: Can I edit the data file directly?**  
A: While possible, it is not recommended as incorrect formatting may cause Jackson to fail to load your tasks.

**Q: What happens if I enter an invalid date format?**  
A: Jackson will display an error message and ask you to enter the command again with the correct format.

**Q: Can I have tasks with the same description?**  
A: Yes, Jackson allows multiple tasks with identical descriptions.

## Known Issues

- Date parsing is strict and requires exact format (YYYY-MM-DD)
- Time must be in 24-hour format (HH:MM)
- Very long task descriptions may cause display formatting issues
- The application does not support undo operations