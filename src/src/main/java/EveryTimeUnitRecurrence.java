import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;

public class EveryTimeUnitRecurrence implements Recurrence{

    private final TemporalUnit unit;
    private final int quantifier;

    public EveryTimeUnitRecurrence(TemporalUnit unit){
        this(unit, 1);
    }

    public EveryTimeUnitRecurrence(TemporalUnit unit, int quantifier){
        this.unit = unit;
        this.quantifier = quantifier;
    }

    @Override
    public ZonedDateTime calculateNext(ZonedDateTime current) {
        return unit.addTo(current, quantifier);
    }

    @Override
    public String toDisplayString() {
        if(quantifier == 1){
            return String.format("Every %s", unit.toString());
        }else{
            return String.format("Every %n %ss", quantifier, unit.toString());
        }
    }
}
