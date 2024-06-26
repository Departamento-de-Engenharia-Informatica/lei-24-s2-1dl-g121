# US008 - List vehicles that need a check-up 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | CheckUpListUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | CheckUpListController | Controller                                                                                                    |
| 			  		        | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Organization          | IE: knows/has its own Employees                                                                               |
| 			  		        | 							                                       | Employee              | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | 							                                       |                       |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Vehicles              | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     | 	... validating all data (local validation)?  | Vehicles              | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | Organization          | IE: knows all its vehicles.                                                                                   | 
| 			  		        | 	... saving the check-up list?                | Organization          | IE: owns all its vehicles.                                                                                    | 
| Step 5  		     | 	... informing operation success?             | CheckUpListUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Vehicles

Other software classes (i.e. Pure Fabrication) identified: 

* CheckUpListUI  
* CheckUpListController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)