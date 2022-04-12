package UnitSystem;

import Category.*;
import Category.Category.CATEGORY_NAME;

public class Imperial extends UnitSystem {
    public Imperial()
    {
        super();

        units.put(CATEGORY_NAME.TEMPERATURE, new Temperature().GetUnit("Fahrenheit"));
        units.put(CATEGORY_NAME.LENGTH, new Length().GetUnit("Inch"));
        units.put(CATEGORY_NAME.VOLUME, new Volume().GetUnit("Gallon"));
        units.put(CATEGORY_NAME.WEIGHT, new Weight().GetUnit("Pound"));
    }
}
