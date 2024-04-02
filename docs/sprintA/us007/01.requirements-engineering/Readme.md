# US007 - Register vehicle's check-up 


## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I wish to register a vehicle’s check-up.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>	Vehicles are needed to carry out the tasks assigned to the teams as well as to transport machines and equipment. This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks.

**From the client clarifications:**

> **Question:** What´s the supposed vehicle check up supposed to look like?
>
> **Answer:** while registering a checkup, the vehicle id, date and current kms, should be considered.

> **Question:** What is the unit of measurement used to estimate the check-up frequency (Kms, months, etc.)?
>
> **Answer:** In real context all could be considered, in the scope of this project just kms will be considered.

### 1.3. Acceptance Criteria

* **AC1:** The FM should be able to enter relevant details about the vehicle check-up, including the km, time, location, and purpose (e.g., routine maintenance, repair).
* **AC2:** The system should allow the FM to identify the vehicle undergoing the check-up accurately. This could be done by entering the vehicle's registration number or selecting it from a list of registered vehicles.
* **AC3:** The FM should have the ability to edit or remove check-ups from vehicles if needed.

### 1.4. Found out Dependencies

* There is a dependency on "As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Check-up Frequency (in Kms)." as there must be at least one vehicle to assign a check-up to.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * a distance in km

* Selected data:
  * a vehicle

**Output Data:**

* List of the check-ups
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* No other remarks.