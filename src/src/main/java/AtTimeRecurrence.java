import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AtTimeRecurrence implements Recurrence{

    private final LocalTime time;

    public AtTimeRecurrence(LocalTime time){
        this.time = time;
    }

    @Override
    public ZonedDateTime calculateNext(ZonedDateTime current) {
        ZoneId zone = current.getZone();
        return ZonedDateTime.of(current.toLocalDate().atTime(time), zone);
    }

    @Override
    public String toDisplayString() {
        return null;
    }
}
