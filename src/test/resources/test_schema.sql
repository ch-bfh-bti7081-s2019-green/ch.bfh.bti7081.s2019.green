DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS "DEFER_TIMES";
CREATE TABLE IF NOT EXISTS "DEFER_TIMES"
(
    "REMINDER_ID" INTEGER     NOT NULL,
    "DEFER_TIME"  VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS "MEDICATION";
CREATE TABLE IF NOT EXISTS "MEDICATION"
(
    "ID"   INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "NAME" VARCHAR(255) NOT NULL,
    "EAN"  INTEGER      NOT NULL,
    UNIQUE ("NAME", "EAN")
);

DROP TABLE IF EXISTS "INTAKE";
CREATE TABLE IF NOT EXISTS "INTAKE"
(
    "ID"              INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "TIME"            VARCHAR(30) NOT NULL,
    "PRESCRIPTION_ID" INTEGER     NOT NULL,
    "PATIENT_ID"      INTEGER     NOT NULL
);

DROP TABLE IF EXISTS "DOSE";
CREATE TABLE IF NOT EXISTS "DOSE"
(
    "ID"     INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "UNIT"   VARCHAR(255) NOT NULL,
    "AMOUNT" INTEGER      NOT NULL,
    UNIQUE ("UNIT", "AMOUNT")
);

DROP TABLE IF EXISTS "CONTACT";
CREATE TABLE IF NOT EXISTS "CONTACT"
(
    "ID"        INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "PHONE"     VARCHAR(255),
    "EMAIL"     VARCHAR(255),
    "STREET"    VARCHAR(255),
    "CITY"      VARCHAR(255),
    "COUNTRY"   VARCHAR(255),
    "PERSON_ID" INTEGER NOT NULL UNIQUE
);

DROP TABLE IF EXISTS "PERSON";
CREATE TABLE IF NOT EXISTS "PERSON"
(
    "ID"         INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "AHV"        INTEGER      NOT NULL,
    "FIRST_NAME" VARCHAR(255) NOT NULL,
    "LAST_NAME"  VARCHAR(255) NOT NULL,
    "USERNAME"   VARCHAR(255) NOT NULL,
    "BIRTH_DATE" TIMESTAMP    NOT NULL
);

DROP TABLE IF EXISTS "PRESCRIPTION";
CREATE TABLE IF NOT EXISTS "PRESCRIPTION"
(
    "ID"            INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "PATIENT_ID"    INTEGER     NOT NULL,
    "THERAPIST_ID"  INTEGER     NOT NULL,
    "MEDICATION_ID" INTEGER     NOT NULL,
    "DOSE_ID"       INTEGER     NOT NULL,
    "ISSUE_DATE"    TIMESTAMP   NOT NULL,
    "VALID_UNTIL"   TIMESTAMP   NOT NULL,
    "REMINDER_ID"   INTEGER     NOT NULL,
    "FIRST_INTAKE"  VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS "EMERGENCY_CONTACT";
CREATE TABLE IF NOT EXISTS "EMERGENCY_CONTACT"
(
    "PATIENT_ID"   INTEGER NOT NULL,
    "EC_PERSON_ID" INTEGER NOT NULL,
    UNIQUE ("PATIENT_ID", "EC_PERSON_ID")
);

DROP TABLE IF EXISTS "PATIENT";
CREATE TABLE IF NOT EXISTS "PATIENT"
(
    "ID"           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "PATIENT_ID"   INTEGER ,
    "THERAPIST_ID" INTEGER,
    "DIARY_ID"     INTEGER
);

DROP TABLE IF EXISTS "THERAPIST";
CREATE TABLE IF NOT EXISTS "THERAPIST"
(
    "ID" INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT
);

DROP TABLE IF EXISTS "REMINDER";
CREATE TABLE IF NOT EXISTS "REMINDER"
(
    "ID"                INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "NOTIFICATION_TIME" VARCHAR(30) NOT NULL,
    "DEFER_TIME"        INTEGER
);

DROP TABLE IF EXISTS "ACTIVITIES";
CREATE TABLE IF NOT EXISTS "ACTIVITIES"
(
    "ID"    INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "TEXT"  VARCHAR(255) NOT NULL,
    "TIME"  TIME NOT NULL,
    "TYPE"  VARCHAR(255) NOT NULL,
    "ENTRY" INTEGER NOT NULL
);

DROP TABLE IF EXISTS "ENTRIES";
CREATE TABLE IF NOT EXISTS "ENTRIES"
(
    "ID"          INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "DATE"        TIMESTAMP NOT NULL,
    "MOOD"        INTEGER NOT NULL,
    "SLEEP_HOURS" DOUBLE NOT NULL,
    "WATER_DRUNK" DOUBLE NOT NULL,
    "NOTES"       VARCHAR(255) NOT NULL,
    "DIARY_ID"    INTEGER NOT NULL
);

DROP TABLE IF EXISTS "MOOD_DIARIES";
CREATE TABLE IF NOT EXISTS "MOOD_DIARIES"
(
    "ID"          INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "PATIENT_ID"  INTEGER
);
