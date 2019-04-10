---
title: "Requirements Review"
subtitle: "Task 03 - Team Green"
author:
    - Christian Kocher
    - Fabio Caggiano
    - Marc Häsler
    - Marius Schär
    - Severin Kaderli
rule-color: 00ba34
link-color: 00ba34
...

# Requirement Review
With this document we review the Requirements Document for [Team Blue](https://github.com/LucaRitz/ch.bfh.bti7081.s2019.blue).

-----------------------------------------------------------------------------------------
   **Page** **Criteria**      **Reason / Comment / Improvement suggestion**
----------- ----------------- -----------------------------------------------------------         
          4 Completeness      Non Functional Use Cases are missing.       

          4 Traceability      The origin of the requirements is not explicitly stated
                              (in this case it would be the interviewees). Perhaps
                              add it to the introduction or to the indiviual
                              requirements.

          8 Comprehensibility Variants are hard to follow. An (activity) diagram or
                              multiple tables might be easier.
                              
      8 + 9 Adaptability      The defined requirements seem to be neatly organised and
                              independent of each other.

          9 Completeness      SRS_105 describes that masterdata can be created.\
                              A Use Case for this requirment is missing.       
                              
         11 Adaptability      Take note that any changes to the datamodel will likely
                              require changes to both clients which connect to the
                              backend via API. This means that changes may potentially
                              cause twice as much work to implement.                              
                             
         14 Verifiability     Usablity: It is not definied for example how the\
                              User Interface should be, that someone else is able to use
                              the PMS. 
        
         14 Verifiability     Scalability: General formulation. It is not defined\ 
                              how many Users, etc.
-----------------------------------------------------------------------------------------

The scope may be too large for the time constraints of this course,
but we don't view that as anything worth dockign points for.

**We award Team Blue 8 / 10 points.**
