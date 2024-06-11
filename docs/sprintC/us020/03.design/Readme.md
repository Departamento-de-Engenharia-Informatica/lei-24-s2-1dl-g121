# US020 - As a Green Space Manager (GSM), I want to register a green space (garden, medium-sized park or large-sized park) and its respective area.

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                  | Justification (with patterns)                                                                                 |
|:-------------  |:----------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 | 	... interacting with the actor?              | CreateGreenSpaceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 | 	... coordinating the US?                     | CreateGreenSpaceController | Controller                                                                                                    |
| Step 3  		 | 	...saving the inputted data?                 | GreenSpaceRepository       | IE: object created in step 1 has its own data.                                                                |
| Step 5  		 | 	... saving the selected category?            | GreenSpaceRepository     | IE: object created in step 1 is classified in one Category.                                                   |
| Step 7  		 | 	... validating all data (local validation)?  | GreenSpaceRepository | IE: owns its data.                                                                                            | 
| 			  		 | 	... saving the created collaborator?         | GreenSpaceRepository      | IE: owns all its tasks.                                                                                       | 
| Step 8  		 | 	... informing operation success?             | CreateGreenSpaceUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* GreenSpace

Other software classes (i.e. Pure Fabrication) identified: 

* CreateGreenSpaceUI
* CreateGreenSpaceController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us020-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us020-class-diagram.svg)