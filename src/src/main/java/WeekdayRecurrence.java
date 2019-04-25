import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.*;

public class WeekdayRecurrence implements Recurrence{
    private static final List<DayOfWeek> DAY_ORDER;

    static {
        var tmpList = new ArrayList<DayOfWeek>(14);
        tmpList.addAll(Arrays.asList(DayOfWeek.values()));
        tmpList.addAll(Arrays.asList(DayOfWeek.values()));
        DAY_ORDER = Collections.unmodifiableList(tmpList);
    }

    private final Set<DayOfWeek> daysToRepeatOn;

    public WeekdayRecurrence(DayOfWeek... weekdays){
        daysToRepeatOn = Set.of(weekdays);
    }

    @Override
    public ZonedDateTime calculateNext(ZonedDateTime current) {
        var currentDay = current.getDayOfWeek();
        var counting = false;
        var daysPassed = 0;
        for (var day : DAY_ORDER) {

            if(counting && daysToRepeatOn.contains(day)){
                break;
            }

            // Have we reached the current day? Start counting.
            if(!counting && day.equals(currentDay)){
                counting = true;
            }

            // Are we counting? Count.
            if(counting){
                daysPassed++;
            }
        }
        return current.plusDays(daysPassed);
    }

    @Override
    public String toDisplayString() {
        return null;
    }
}
