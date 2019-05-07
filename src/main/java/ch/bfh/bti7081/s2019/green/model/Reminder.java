package ch.bfh.bti7081.s2019.green.model;

import java.time.ZonedDateTime;
import java.util.List;

//TODO model and sql
public class Reminder extends AbstractBaseEntity{
    private Prescription prescription;
    private ZonedDateTime notificationTime;
    private List<ZonedDateTime> deferTimes;
    private List<Recurrence> recurrences;
}
