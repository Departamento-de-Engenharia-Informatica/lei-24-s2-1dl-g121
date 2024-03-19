# US001 - Add a skill 


## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager (HRM), I want to register skills that may
be appointed to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Thus, an employee has a main occupation (job) and a set of skills
that enable him to perform/take on certain tasks/responsibilities, for example, driving
vehicles of different types (e.g. light, or heavy), operating machines such as backhoes
or tractors; tree pruning; application of phytopharmaceuticals.

**From the client clarifications:**

> **Question:** What criteria is needed to add a skill? What information does it have?
>
> **Answer:** The skills name.


### 1.3. Acceptance Criteria

* **AC1:** Ability to add a new skill.
* **AC2:** Skill description should not exceed 50 characters.
* **AC3:** Skills can be organized by category.

### 1.4. Found out Dependencies

* No dependencies were found.
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Skill name
    * Skill description
    * Skill category


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* No other remarks.