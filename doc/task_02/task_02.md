---
title: "Requirements Specification"
subtitle: "Task 02 - Team Green"
author:
    - Christian Kocher
    - Fabio Caggiano
    - Marc Häsler
    - Marius Schär
    - Severin Kaderli
rule-color: 00ba34
link-color: 00ba34
lof: true
lot: true
glossary: true
glossary-file: "assets/glossary.tex"
...

# Preface
This document is for system engineers, end-users,
system administrator and managers which
are involved in the project.

## History
: History

| Version | Author          | Changelog                | Date       |
|:-------:|-----------------|--------------------------|------------|
|   0.1   | All             | Document created         | 2019-03-27 |
|   0.2   | Severin Kaderli | Add glossary and testing | 2019-04-03 |

# Introduction
: Roles

| Initial | Name             | Role                   |
|:-------:|------------------|------------------------|
|   CK    | Christian Kocher | Student / Developer    |
|   FC    | Fabio Caggiano   | Student / Developer    |
|   MH    | Marc Häsler      | Student / Developer    |
|   MS    | Marius Schär     | Student / Developer    |
|   SK    | Severin Kaderli  | Student / Developer    |
|   JV    | Jürgen Vogel     | Lecturer / Stakeholder |
|   UK    | Urs Künzler      | Lecturer / Stakeholder |

This document describes the requirements for software that supports patients with
depression in therapy. 
A patient management system contains multiple individual subsystems. This software
should help patients with depression.
We have set the focus of this document on the patient role.

# User requirements definition

## Functional User Requirements
| **No.** | **Requirement** |
|-----|-------------|
| 1.0 | XXX         |

## Non-Functional User Requirements
| **No.** | **Requirement** |
|-----|-------------|
| 1.0 | XXX         |

\newpage
## Use-Cases

### 001 - Medication Reminders
----------------------- --------------------------------------------------------
**No.:**                001

**Name:**               Medication Reminders

**Short Description:**  The patient takes their medication when reminded
                        by the system at the time that the therapist specified
                        and ticks it off when taken.

**Scenario A:**         Bob's Therapist prescribed him Fluoxetine to
                        take daily before noon.
                        The therapist entered these data into the PMS.
                        Everyday at 11:30 Bob's devices buzz to remind
                        him to take his medication.
                        After he takes it,
                        Bob checks off on his device
                        that he has taken the medication.

**Variant A.1:**        Today Bob cannot take his medication when his device
                        reminds him because he is driving.
                        The system automatically defers his reminder
                        by 30 minutes for today and reminds him again then.

**Involved Actors:**    Patient, Therapist, System

**Pre-Condition:**      Patient has medication assigned by therapist.

**Result:**             Patient is reminded about medication and takes it.

**Post-Condition:**     Patient has taken medication, the system knows about it.

**Side-Effects:**       -

----------------------- --------------------------------------------------------

: Use-Case 001: Medication Reminders

\newpage

#### Activity Diagram
![Activity Diagram describing Use-Case 001](assets/activity_medication_reminders.png){ height=80% }

\newpage

#### Steps
| Number | Involved | Description |
|--------|----------|-------------|
| 1.0    | XXX      | XXX         |

#### Exceptions
| Number | Involved | Description                               |
|--------|----------|-------------------------------------------|
| 1.0    | Patient  | Cannot take their medication at this time. |

\newpage

### 002 - Patient-Therapist Communication
----------------------- --------------------------------------------------------
**No.:**                002

**Name:**               Patient-Therapist Communication

**Short Description:**  The patient wants to talk to their therapist
                        and can do this quickly and asynchronously.

**Scenario:**           Mallorie wants to talk to her therapist about some
                        symptoms she's been experiencing.
                        Through a text interface they can communicate
                        quickly and asynchronously to discuss if and what
                        further steps should be taken in order to mitigate
                        Mallorie's symptoms.
                        The therapist asks Mallorie to keep an eye on her mood
                        in the coming days and report it to him.

**Involved Actors:**    Patient, Therapist

**Pre-Condition:**      Patient wants to talk to their therapist.\
                        $\lor$\
                        Therapist wants to talk to their patient.

**Result:**             The patient and therapist communicated.

**Post-Condition:**     -

**Side-Effects:**       -

----------------------- --------------------------------------------------------

: Use-Case 002: Patient-Therapist Communication

\newpage

#### Steps
| Number | Involved | Description |
|--------|----------|-------------|
| 1.0    | XXX      | XXX         |

#### Exceptions
| Number | Involved | Description                               |
|--------|----------|-------------------------------------------|
| 1.0    | XXX      | XXX                                       |

\newpage

### 003 - Mood Diary
----------------------- --------------------------------------------------------
**No.:**                003

**Name:**               Mood Diary    

**Short Description:**  The patient can record their mood, activity, sleep,
                        and food for any given date
                        and share this information with their therapist.
                        
**Scenario:**           Her therapist has asked Mallorie to record her mood
                        for two weeks in order to better gauge the success
                        of a new medication.\
                        Every evening, Mallorie rates how she felt today,
                        writes down what she did, what she ate, how she slept
                        and any other significant occurences.\
                        Her therapist sees this information and can
                        adjust her dosage relatively quickly.

**Involved Actors:**    Patient

**Pre-Condition:**      Therapist asks patient to track their mood.

**Result:**             Therapist and patient see the progress of the patient.

**Post-Condition:**     

**Side-Effects:**       It may be detrimental for some patients to have to write
                        down how they feel. Thus the therapist should have to
                        enable the feature for the patient.

----------------------- --------------------------------------------------------

: Use-Case 003: Mood Diary

\newpage

#### Steps
| Number | Involved | Description |
|--------|----------|-------------|
| 1      | XXX      | XXX         |

#### Exceptions
| Number | Involved | Description                                                          |
|--------|----------|----------------------------------------------------------------------|
| 1.0    | Patient  | It's detrimental to the patients progress to spell out how they feel |
| 2.0    | Patient  | The patient forgets to write their mood diary or writes inaccurately |

\newpage

# System architecture

# System requirements

## Functional System Requirements
: Functional System Requirements

| # | Requirement |
|---|-------------|
| 1 | XXX         |

## Non functional System Requirements
: Non functional System Requirements

| # | Requirement |
|---|-------------|
| 1 | XXX         |

# System models

# System evolution

# Testing
This application is meticulously tested using the following methods.

## Component Tests
The code of the application is gonna be tested using numerous methods including
unit tests by the individual developers and code reviews. This way a high
software quality can be ensured.

## Integration Tests
To ensure all components are behaving correctly together they are thoroughly
tested before deploying a new version.

## System Tests
After the aforementioned integration tests the result is compared agains the
specification to ensure that the requirements and functionality are correctly
implemented and working.

## Validation Tests
At the end of the project the application is tested together with the customer.
This way it can be ensured the software is behaving exactly like the customer
wants and that the user experience is acceptable

# Appendices
 * System requirement
   * User:
     * Devices with internet connection (Computer, Tablet, Phone)
     * Browser for example Firefox, Safari, Chrome
   * Server:
     * Enough space for data
     * Enough performance (Ram Memory, Processor)
     * Reliable and fast internet connection 
