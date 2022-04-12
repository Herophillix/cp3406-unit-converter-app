package com.example.unit_converter_app.UnitSystem;

import com.example.unit_converter_app.Category.*;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;

public class Imperial extends UnitSystem {
    public Imperial()
    {
        super();

        units.put(CATEGORY_NAME.TEMPERATURE, new Temperature().GetUnit("Fahrenheit"));
        units.put(CATEGORY_NAME.LENGTH, new Length().GetUnit("Inch"));
        units.put(CATEGORY_NAME.VOLUME, new Volume().GetUnit("Gallon"));
        units.put(CATEGORY_NAME.WEIGHT, new Weight().GetUnit("Pound"));
        systemName = "Imperial";
    }
}
