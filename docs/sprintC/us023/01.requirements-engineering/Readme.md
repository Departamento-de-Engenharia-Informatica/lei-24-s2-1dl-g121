# US023 - Assign a team to an Entry in the Agenda.

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign a Team to an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 


**From the client clarifications:**

> **Question:**  Can a Team be assigned to multiple entrys?
>
> **Answer:** yes.

> **Question:** Can an Agenda entry have more than one team assigned to it?
>
> **Answer:** no.

### 1.3. Acceptance Criteria

* **AC1:** A message must be sent to all team members informing
  them about the assignment.
* **AC2:** Different email services can send the message. These ser- vices must be defined through a configuration file to allow the use of different platforms (e.g. Gmail, DEI’s email service, etc.).



### 1.4. Found out Dependencies

* Dependencies on US005 and US022

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
* Selected data: Team to  which an entry will be assigned; Entry to assign a team.
 

**Output Data:**

* Confirmation message

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

<?xml version="1.0" encoding="us-ascii" standalone="no"?><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" contentStyleType="text/css" height="395px" preserveAspectRatio="none" style="width:501px;height:395px;background:#FFFFFF;" version="1.1" viewBox="0 0 501 395" width="501px" zoomAndPan="magnify"><defs/><g><text fill="#000000" font-family="sans-serif" font-size="14" font-weight="bold" lengthAdjust="spacing" textLength="363" x="68" y="28.5352">System Sequence Diagram (SSD) - Alternative One</text><rect fill="#FFFFFF" height="176.8633" style="stroke:#181818;stroke-width:1.0;" width="10" x="81" y="128.9766"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="150.2871"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="208.9082"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="267.5293"/><line style="stroke:#181818;stroke-width:0.5;stroke-dasharray:5.0,5.0;" x1="86" x2="86" y1="118.9766" y2="314.8398"/><line style="stroke:#181818;stroke-width:0.5;stroke-dasharray:5.0,5.0;" x1="462" x2="462" y1="118.9766" y2="314.8398"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="156" x="5" y="116.0234">Green Spaces Manager</text><ellipse cx="86" cy="50.9883" fill="#E3E3E3" rx="8" ry="8" style="stroke:#181818;stroke-width:0.5;"/><path d="M86,58.9883 L86,85.9883 M73,66.9883 L99,66.9883 M86,85.9883 L73,100.9883 M86,85.9883 L99,100.9883 " fill="none" style="stroke:#181818;stroke-width:0.5;"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="156" x="5" y="327.375">Green Spaces Manager</text><ellipse cx="86" cy="338.8281" fill="#E3E3E3" rx="8" ry="8" style="stroke:#181818;stroke-width:0.5;"/><path d="M86,346.8281 L86,373.8281 M73,354.8281 L99,354.8281 M86,373.8281 L73,388.8281 M86,373.8281 L99,388.8281 " fill="none" style="stroke:#181818;stroke-width:0.5;"/><rect fill="#E3E3E3" height="30.4883" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="66" x="429" y="87.4883"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="52" x="436" y="108.0234">:System</text><rect fill="#E3E3E3" height="30.4883" rx="2.5" ry="2.5" style="stroke:#181818;stroke-width:0.5;" width="66" x="429" y="313.8398"/><text fill="#000000" font-family="sans-serif" font-size="14" lengthAdjust="spacing" textLength="52" x="436" y="334.375">:System</text><rect fill="#FFFFFF" height="176.8633" style="stroke:#181818;stroke-width:1.0;" width="10" x="81" y="128.9766"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="150.2871"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="208.9082"/><rect fill="#FFFFFF" height="29.3105" style="stroke:#181818;stroke-width:1.0;" width="10" x="457" y="267.5293"/><polygon fill="#181818" points="445,146.2871,455,150.2871,445,154.2871,449,150.2871" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="91" x2="451" y1="150.2871" y2="150.2871"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="98" y="145.5449">1</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="270" x="111" y="145.5449">asks to assign an Agenda's entry to a team</text><polygon fill="#181818" points="102,175.5977,92,179.5977,102,183.5977,98,179.5977" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;stroke-dasharray:2.0,2.0;" x1="96" x2="461" y1="179.5977" y2="179.5977"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="108" y="174.8555">2</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="329" x="121" y="174.8555">request the team to which the entry will be assigned</text><polygon fill="#181818" points="445,204.9082,455,208.9082,445,212.9082,449,208.9082" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="91" x2="451" y1="208.9082" y2="208.9082"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="98" y="204.166">3</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="111" x="111" y="204.166">chooses the team</text><polygon fill="#181818" points="102,234.2188,92,238.2188,102,242.2188,98,238.2188" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;stroke-dasharray:2.0,2.0;" x1="96" x2="461" y1="238.2188" y2="238.2188"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="108" y="233.4766">4</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="317" x="121" y="233.4766">request the entry that will be assigned to the team</text><polygon fill="#181818" points="445,263.5293,455,267.5293,445,271.5293,449,267.5293" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;" x1="91" x2="451" y1="267.5293" y2="267.5293"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="98" y="262.7871">5</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="112" x="111" y="262.7871">chooses the entry</text><polygon fill="#181818" points="102,292.8398,92,296.8398,102,300.8398,98,296.8398" style="stroke:#181818;stroke-width:1.0;"/><line style="stroke:#181818;stroke-width:1.0;stroke-dasharray:2.0,2.0;" x1="96" x2="461" y1="296.8398" y2="296.8398"/><text fill="#000000" font-family="sans-serif" font-size="13" font-weight="bold" lengthAdjust="spacing" textLength="9" x="108" y="292.0977">6</text><text fill="#000000" font-family="sans-serif" font-size="13" lengthAdjust="spacing" textLength="170" x="121" y="292.0977">displays operation success</text><!--SRC=[fPBFJiCm3CRlVGfh5-2m5zW1QT8G9yIXJ-2bfecME2LsD_NjyR9sW1WNy2N_xE_xsOeS0WzOC47Ad9qlEH5e6QcHwyk0xe0zjJf5Wa9EaVluDK4yTlaKk8StZ49Deq4jjPr4AK5BxoEn8zW5xC_fzssxUu0rRADIOTHm97XbAyDHCuzfJyNsJdE1rNCXOcWDWGHUa8sZh005dj8GyqJK68865mPaXTNcubapBjkg58wez5dIWCLoWlNZGhgnieE0Pbibz0p8iEs9ExmJ8DOorJTGmbGrhjAB5ymn2wvD- -gpiTdP94H1FLMDizh91-VhpSNW56A4FSq4r5N9ZcuwtMp1-Pp5XhMO_1TqxjkZ_c0xyt-p-IjdzVWBQ1TaY3W9v86A_QJC8AEploA_y7m0]--></g></svg>
