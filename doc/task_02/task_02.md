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
depression in therapy as well as doctors in their day-to-day work with their 
patients.

Our goal is to develop an application which allows for doctors and patients with
depression to work closely together. The software is to provide assistance to the
patient where needed and allow the doctors to grant access to information and help 
where appropriate.
Individuals suffering from depression should be able to have a source
of information and help at their fingertips when they require it.

The application's functions will be on a "permission" basis determined by the 
patient's doctor. This means that the doctor will be able to decide which functions 
are most helpful to his patients and which are not suited to certain cases.

The doctor will be able to track a patient's progress through the system and monitor 
any changes.

The idea is to allow for a cooperative process between doctor and patient. It is not
intended to replace any systems already in place but to support them, especially from
the point of view of the patient.

The application should help to support a patient by allowing them to write mood diaries,
keep track of medication intake and prescriptions, contact their doctor for help and
advice and provide information and emergency contacts. These are all things which, 
according to our interviewees, can help a person suffering from depression in their 
day-to-day lives.

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
![](.assets/system_architecture.png)

##Client
A user of the system will access the application through a web-browser
on their client device. All requests to the server will be made over an
encrypted connection using HTTPS (SSL/TLS).

##Application Server
The application server will handle all client requests and generate the 
required pages which are then sent back to the client. All business logic,
authentication and database queries are executed here. 

##Database
User information, patient information and all other required data will be 
stored in the database. It is of utmost importance that the information stored
and being sent to and from the database is extremely secure. The database will
contain very sensitive patient information and all the users authentication 
information.

# System requirements

##Functional System Requirements

###User Administration
The system should allow for user management through an admin login. Here the
system administrator will be able to add, remove and edit existing users. The
system administrator will also be able to edit any available settings from here.

###Login and Dashboard
A user should be able to log into his/her account and have an overview of their
profile. The user should be able to configure their profile wherever possible.
This includes updating personal information and authentication details.

###Patient Management
A doctor should be able to login and have an overview of his/her patients.
A doctor should also be able to set certain permissions for patients.

###Saving Data and Documents
The system should allow all users to save relevant information and documents 
to a database.
The information stored should be secure and only visible to users with the 
correct permissions.

##Non-Functional System Requirements

###Usability
The application should be self-explanatory to use and it should be easy and clear 
to navigate. Important information and links should stand out on the individual
pages. Emergency numbers should be displayd clearly on all pages.

###Availability
The application should be accessible and usable at all times.

###Security
All information sent to and from the server should be secure and encrypted.
The data stored in the database should not be accessible by any third party.

###Performance
The system should offer consistently high performance. This means pages and
data should be loaded in less than 2 seconds.

###Stability
The application should remain stable. A user should never be interrupted 
while on the site and data should never be lost due to faults in the application.


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
