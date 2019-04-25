import java.time.ZonedDateTime;

public class RecurrenceStacker {
   public static ZonedDateTime applyStacked(final ZonedDateTime current, final Recurrence... recurrences){
        ZonedDateTime result = current;
        for(Recurrence recurrence : recurrences){

            result = recurrence.calculateNext(result);
        }
        return result;
    }
}
