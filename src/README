# Sudoku Java CLI

A Sudoku game developed in Java for terminal execution.

The application allows users to start a new game, insert numbers, remove numbers, check game status, and finish the match through an interactive menu.

---

# Features

* Start a new game
* Insert numbers into the board
* Remove numbers
* View the current board
* Check game status
* Reset the game
* Finish the game

---

# Project Structure

```text
src/
 ├─ Main.java
 ├─ model/
 │   ├─ Board.java
 │   └─ Space.java
 └─ util/
     └─ BoardTemplate.java
```

---

# How to Compile

Inside the `src` folder, run:

```bash
javac Main.java model/*.java util/*.java
```

---

# How to Run

## PowerShell

In PowerShell, every argument must be wrapped in quotes because `;` is interpreted as a command separator.

Example:

```powershell
java Main "0,0;4,false" "1,0;7,false"
```

## CMD

In the traditional Windows CMD (`cmd.exe`), quotes are not required.

---

# Argument Structure

Each argument represents one board position.

Format:

```text
row,column;value,fixed
```

Example:

```text
0,0;4,false
```

Meaning:

| Part    | Description                        |
| ------- | ---------------------------------- |
| `0,0`   | row/column position                |
| `4`     | expected value                     |
| `false` | defines whether the value is fixed |

---

# Boolean Values

| Value   | Meaning            |
| ------- | ------------------ |
| `true`  | fixed board number |
| `false` | editable position  |

---

# Full Execution Command

## PowerShell

```powershell
java Main "0,0;4,false" "1,0;7,false" "2,0;9,true" "3,0;5,false" "4,0;8,true" "5,0;6,true" "6,0;2,true" "7,0;3,false" "8,0;1,false" "0,1;1,false" "1,1;3,true" "2,1;5,false" "3,1;4,false" "4,1;7,true" "5,1;2,false" "6,1;8,false" "7,1;9,true" "8,1;6,true" "0,2;2,false" "1,2;6,true" "2,2;8,false" "3,2;9,false" "4,2;1,true" "5,2;3,false" "6,2;7,false" "7,2;4,false" "8,2;5,true" "0,3;5,true" "1,3;1,false" "2,3;3,true" "3,3;7,false" "4,3;6,false" "5,3;4,false" "6,3;9,false" "7,3;8,true" "8,3;2,false" "0,4;8,false" "1,4;9,true" "2,4;7,false" "3,4;1,true" "4,4;2,true" "5,4;5,true" "6,4;3,false" "7,4;6,true" "8,4;4,false" "0,5;6,false" "1,5;4,true" "2,5;2,false" "3,5;3,false" "4,5;9,false" "5,5;8,false" "6,5;1,true" "7,5;5,false" "8,5;7,true" "0,6;7,true" "1,6;5,false" "2,6;4,false" "3,6;2,false" "4,6;3,true" "5,6;9,false" "6,6;6,false" "7,6;1,true" "8,6;8,false" "0,7;9,true" "1,7;8,true" "2,7;1,false" "3,7;6,false" "4,7;4,true" "5,7;7,false" "6,7;5,false" "7,7;2,true" "8,7;3,false" "0,8;3,false" "1,8;2,false" "2,8;6,true" "3,8;8,true" "4,8;5,true" "5,8;1,false" "6,8;4,true" "7,8;7,false" "8,8;9,false"
```

---

# System Menu

When the program starts, the following menu will be displayed:

```text
1 - Start a new game
2 - Insert a new number
3 - Remove a number
4 - View current game
5 - Check game status
6 - Reset game
7 - Finish game
8 - Exit
```

---

# Notes

* The game requires all 81 Sudoku positions as arguments.
* If any position is missing, a `NullPointerException` will occur.
* To avoid PowerShell parsing errors, wrap all arguments in quotes.

---

# Technologies Used

* Java
* Object-Oriented Programming
* Collections API
* Streams API
