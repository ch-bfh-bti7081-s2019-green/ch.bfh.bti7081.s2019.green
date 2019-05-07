package ch.bfh.bti7081.s2019.green.model;

//TODO Mapping and SQL
//Inheritance -> MappedSuperClass: https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
public abstract class Recurrence {
    /**
     * Takes a reminder and calculates the "next instance" of that reminder.
     * Taking into account the entire list of recurrences in the reminder;
     */
    public static Reminder calculateNext(Reminder reminder){
        throw new RuntimeException("Not yet implemented, have fun Christian");
    }

    public static String toDisplayString(){
        throw new RuntimeException("Not yet implemented, have fun Christian");
    }
}
