import java.time.ZonedDateTime;

public interface Recurrence {
    ZonedDateTime calculateNext(ZonedDateTime current);
    String toDisplayString();
}
