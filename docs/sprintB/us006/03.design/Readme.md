# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                  | Justification (with patterns)                                                                                 |
|:-------------  |:----------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 | 	... interacting with the actor?              | CreateVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 | 	... coordinating the US?                     | CreateVehicleController | Controller                                                                                                    |
| Step 3  		 | 	...saving the inputted data?                 | VehicleReposiory        | IE: object created in step 1 has its own data.                                                                |
| Step 5  		 | 	... saving the selected category?            | VehicleRepository       | IE: object created in step 1 is classified in one Category.                                                   |
| Step 7  		 | 	... validating all data (local validation)?  | VehicleRepository       | IE: owns its data.                                                                                            | 
| 			  		 | 	... saving the created collaborator?         | VehicleRepository       | IE: owns all its tasks.                                                                                       | 
| Step 8  		 | 	... informing operation success?             | CreateVehicleUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* VehicleRepository

Other software classes (i.e. Pure Fabrication) identified: 

* CreateVehicleUI  
* CreateVehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)