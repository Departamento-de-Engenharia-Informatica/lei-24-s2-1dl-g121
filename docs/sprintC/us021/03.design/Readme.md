# US006 - Register a vehicle

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...      | Answer                    | Justification (with patterns)                                                                                 |
|:---------------|:-------------------------------------------------|:--------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                 | RegisterVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                        | RegisterVehicleController | Controller                                                                                                    |
| Step 2  		     | ...obtaining the types of vehicles that exist?   | RegisterVehicleController | Controller                                                                                                    |
|                | ... displaying the types of vehicles that exist? | RegisterVehicleUI         | IE: is responsible for user interactions.                                                                     |
| Step 3  		     | 	...registering the vehicle?                     | VehicleRepository         | Creator(Rule 2):records instances of Vehicles.                                                                |
| Step 4  		     | 	... validating all data (local validation)?     | Vehicle                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)?    | VehicleRepository         | IE: knows all its vehicles.                                                                                   | 
| 			  		        | 	... saving the created vehicle?                 | VehicleRepository         | IE: owns all its vehicles.                                                                                    | 
| Step 5  		     | 	... informing operation success?                | RegisterVehicleUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Vehicle

Other software classes (i.e. Pure Fabrication) identified:

* VehicleRepository
* RegisterVehicleUI
* RegisterVehicleController

## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us021-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us021-class-diagram.svg)