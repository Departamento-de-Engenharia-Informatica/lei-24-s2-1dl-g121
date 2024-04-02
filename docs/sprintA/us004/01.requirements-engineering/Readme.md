# US004 - Assign a skill 


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to assign one or more skills to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Thus, an employee has a main occupation (job) and a set of skills that enable him to perform/take on certain tasks/responsibilities, for example, driving vehicles of different types (e.g. light, or heavy), operating machines such as backhoes or tractors; tree pruning; application of agriculture phytopharmaceuticals.

**From the client clarifications:**

> **Question:** Can a collaborator have no skills assigned?
>
> **Answer:** yes.

> **Question:** Is there a maximum and minimum number of skills that can be assigned?
>
> **Answer:** no.

> **Question:** Is there are any special characteristics that the collaborator needs to have in order to have these skills assigned?
>
> **Answer:** no.

### 1.3. Acceptance Criteria

* **AC1:** The HRM should be able to assign skills to one or multiple collaborators simultaneously.
* **AC2:** The HRM should be able to select one or more skills from a predefined list or enter new skills if necessary.
* **AC3:** The HRM should have the ability to edit or remove assigned skills for collaborators as needed.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - I want to register skills that may be appointed to a collaborator." as there must be at least one skill to assign to the collaborator.
* There is a dependency on "US003 - I want to register a collaborator with a job and fundamental characteristics." as there must be at least one collaborator to assign the skill to.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
  * a skill
  * a collaborator

**Output Data:**

* List of the collaborator's assign skills
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* No other remarks.