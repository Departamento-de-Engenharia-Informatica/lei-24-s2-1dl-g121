# US006 - Register a vehicle

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer             | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | ToDoListUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | ToDoListController | Controller                                                                                                    |
| Step 2  		     | ...obtaining the types of tasks that exist?   | ToDoListController | Controller                                                                                                    |
| Step 3  		     | 	...registering the task?                     | ToDoList           | Creator(Rule 2):records instances of Tasks.                                                                   |
| Step 4  		     | 	... validating all data (local validation)?  | Task               | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | ToDoList           | IE: knows all its tasks.                                                                                      | 
| 			  		        | 	... saving the created task?                 | ToDoList           | IE: owns all its tasks.                                                                                       | 
| Step 5  		     | 	... informing operation success?             | ToDoListUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Task
* ToDoList
* ToDoListUI
* ToDoListController


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us021-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us021-class-diagram.svg)