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

| Version | Author          | Changelog                    | Date       |
|:-------:|-----------------|------------------------------|------------|
|   0.1   | All             | Document created             | 2019-03-27 |
|   0.2   | Severin Kaderli | Add glossary and testing     | 2019-04-03 |
|   0.3   | Fabio Caggiano  | Add system model & evolution | 2019-04-04 |

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
: Functional User Requirements

| # | Requirement |
|:-:|-------------|
| 1 | XXX         |

## Non-Functional User Requirements
: Non-Functional User Requirements

| # | Requirement |
|:-:|-------------|
| 1 | XXX         |

## Use-Case Szenarios
|                       |          |
|-----------------------|----------|
| **Nr. and Name**      | 01 - XXX |
| **Scene**             | XXX      |
| **Short description** | XXX      |
| **Involved**          | XXX      |
| **Start-Event**       | XXX      |
| **Result**            | XXX      |

### Steps
: Steps

| Number | Involved | Description |
|--------|----------|-------------|
| 1      | XXX      | XXX         |

### Exceptions
: Exceptions

| Number | Involved | Description |
|--------|----------|-------------|
| 1      | XXX      | XXX         |

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

To following data-flow-diagram describes the interaction of different system components from the patient's perspective in the patient management system.

![data-flow-diagram](./assets/system_model_dfd.PNG)

You can read the above diagram like this: The patient enters the login details, the application server queries the database (patient table) if the right login credentials have been provided and either informs the patient that the username or password were invalid or forwards the patient to the main page of the application. From the main page, the user has the possibility to open the communication, mood diary, medication reminder or prescription overview tab. Each one of these features retrieve and write data to their corresponding database table.


# System evolution

After the development of this web application, patients should have all fundamental features to manage their data. For the time being there are no new features planned. However, new features requested by our customer could be integrated without any problems in the future because our development team preferably strives to write good structered generic code. 

With growing patient data and workloads in the future, the hardware can be enhanced with load balancers for example to coordinate traffic and more servers to distribute the databases and link them together.

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
