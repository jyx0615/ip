# Jackson User Guide

## Table of Content
- [Jackson User Guide](#jackson-user-guide)
  - [Table of Content](#table-of-content)
  - [Features](#features)
    - [Adding todo task: `todo`](#adding-todo-task-todo)
    - [Adding deadline task: `deadline`](#adding-deadline-task-deadline)


## Features
<p class="callout info">
Notes about the command format:

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `deadline /by DATE`, `DATE` is a parameter which can be used as `deadline /by 2025-01-01`.

- Items in square brackets are optional.
e.g `DATE [TIME]` can be used as `2025-01-01 04:00` or as `2025-01-01`.

- Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `bye`) will be ignored.
e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</p>

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
 - `DATE` should be in the format of `YYYY-MM-DD`
 - `TIME` should be in the format of `HH:MM`
