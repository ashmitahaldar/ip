# MayoBot User Guide

![MayoBot Logo](Ui.png)
( ˶ˆᗜˆ˵ )

MayoBot is a personal task management chatbot that helps you organize your daily tasks with a friendly and intuitive interface. 
Whether you need to track todos, deadlines, or events, MayoBot makes task management simple and efficient with its conversational approach.


## Adding todo tasks

Add simple tasks that you need to complete without any specific timing constraints.

Example: `todo buy groceries`

MayoBot will add the task to your list and confirm the addition with a friendly message.

```
Got it! I've added this task: 
	[T][ ] buy groceries 
Now you have 1 task(s) in the list ₊˚⊹⋆
```

## Adding deadlines

Add tasks that have a specific due date and time. MayoBot supports flexible date formats for your convenience.

Examples:
- `deadline submit assignment /by 15-03-2024 23:59`
- `deadline return library book /by tomorrow`
- `deadline pay bills /by next Friday`

MayoBot will parse the date and store your deadline task with the specified due date.

```
Got it! I've added this task: 
	[D][ ] submit assignment (by: Mar 15 2024, 11:59 PM) 
Now you have 2 task(s) in the list ₊˚⊹⋆
```

## Adding events

Add tasks that occur at a specific time period, perfect for meetings, appointments, or scheduled activities.

Examples:
- `event team meeting /from 14-03-2024 14:00 /to 14-03-2024 16:00`
- `event doctor appointment /from Monday 9am /to Monday 10am`

MayoBot will schedule your event and confirm the time slot.

```
Got it! I've added this task: 
	[E][ ] team meeting (from: Mar 14 2024, 2:00 PM to: Mar 14 2024, 4:00 PM) 
Now you have 3 task(s) in the list ₊˚⊹⋆
```

## Viewing all tasks

Display all tasks in your current task list with their completion status and details.

Example: `list`

Shows all tasks with their type indicators and completion status.

```
₊˚⊹ ♡ Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] submit assignment (by: Mar 15 2024, 11:59 PM)
3. [E][ ] team meeting (from: Mar 14 2024, 2:00 PM to: Mar 14 2024, 4:00 PM)
```

## Marking tasks as done

Mark completed tasks to track your progress.

Example: `mark 1`

MayoBot will update the task status and celebrate your completion.

```
ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧ I've marked this task as done:
	[T][X] buy groceries
```

## Unmarking tasks

Unmark tasks if you need to redo them or marked them by mistake.

Example: `unmark 1`

MayoBot will revert the task to incomplete status.

```
ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧ I've marked this task as not done yet:
	[T][ ] buy groceries
```

## Deleting tasks

Remove tasks that are no longer needed from your list.

Example: `delete 2`

MayoBot will remove the specified task and update your task count.

```
(˵ •̀ ᴗ - ˵ ) ✧ I've removed this task:
	[D][X] submit assignment (by: Mar 15 2024, 11:59 PM)
Now you have 2 task(s) in the list ₊˚⊹⋆
```

## Finding tasks

Search for tasks containing specific keywords with multiple search modes for flexibility.

Examples:
- `find meeting` - partial matching (default)
- `find --fuzzy meting` - fuzzy matching (handles typos)
- `find --strict meeting` - exact word matching

MayoBot will display all matching tasks with their original list positions.

```
(ㅅ´ ˘ `) Here are the matching tasks: (1 found)
3. [E][ ] team meeting (from: Mar 14 2024, 2:00 PM to: Mar 14 2024, 4:00 PM)
```

## Saving and loading

MayoBot automatically saves your tasks to a file and loads them when you restart the application. No manual saving required!

## Exiting the application

End your session with MayoBot using the bye command.

Example: `bye`

MayoBot will save your tasks and bid you farewell.

```
Bye! Hope to see you again soon!
```

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Add Todo | `todo <description>` | `todo read book` |
| Add Deadline | `deadline <description> /by <date>` | `deadline submit report /by 15-03-2024 23:59` |
| Add Event | `event <description> /from <start> /to <end>` | `event meeting /from today 2pm /to today 4pm` |
| List Tasks | `list` | `list` |
| Mark Done | `mark <index>` | `mark 1` |
| Unmark | `unmark <index>` | `unmark 1` |
| Delete | `delete <index>` | `delete 2` |
| Find | `find [--fuzzy/--strict] <keyword>` | `find --fuzzy meting` |
| Exit | `bye` | `bye` |

## Tips

- Task indices start from 1
- Date formats are not flexible (yet) :( follow the format: "DD-MM-YYYY HH:MM"
- Use fuzzy search (`--fuzzy`) when you're unsure of exact spelling
- Use strict search (`--strict`) when you want exact word matches only
- MayoBot is case-insensitive for most operations