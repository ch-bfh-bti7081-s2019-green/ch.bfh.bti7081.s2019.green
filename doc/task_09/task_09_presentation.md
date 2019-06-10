---
title: "Final Presentation"
subtitle: "Task 09 - Team Green"
author:
    - Christian Kocher
    - Fabio Caggiano
    - Marc Häsler
    - Marius Schär
    - Severin Kaderli
main-color: 00ba34
lang: "en"
...

# Introduction (Reminder)

- Support patients suffering from depression
- Allows doctors and patients to work closely together

# Demo

# Design Thinking

- Implemented
  - Reminder
  - Mood Diary
  - Chat

- Planned
  - Prescriptions from medications
  - Chat with other patients (self help)
  - Place for document sharing
  - Overview for appointments/Make appointment by a doctor
  - Information page about the illness
  - Financial information (ex. Assurances)

# Architecture
 - Hibernate
 - PostreSQL
 - Vaadin

# Code Highlights
  - DB Seeder
  - Scheduler
  - CI/CD

# Patterns
  - Singelton
  - Listener/Observer
  - Repository Pattern
  - (MVP Pattern)

# List of personal contributions
\colsbegin
\col{50}
- Christian Kocher
    - Reminders
- Fabio Caggiano
    - Login
- Marc Häsler
    - Parts of Mood Diary
    - Emergency Page
    - Scrum Master

\col{50}

- Marius Schär
    - Persistence Architecture
    - Chat
    - Scheduler for reminders
- Severin Kaderli
    - Parts of Mood Diary
    - Database
    - CI/CD

\colsend

# Lessons Learnt

## Lessons Learnt

### Do
- Use PostgreSQL instead of SQLite
- First make the basic framework, then add features (ex. DB, Login, etc.)

### Don't
- Attempt a project with completely unknown technologies.
- Plan too many features at once
- Choose a framework with lackluster docs
