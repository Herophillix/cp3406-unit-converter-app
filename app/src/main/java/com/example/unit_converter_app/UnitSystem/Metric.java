package com.example.unit_converter_app.UnitSystem;

import com.example.unit_converter_app.Category.*;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;

public class Metric extends UnitSystem {
    public Metric()
    {
        super();

        units.put(CATEGORY_NAME.TEMPERATURE, new Temperature().GetUnit("Kelvin"));
        units.put(CATEGORY_NAME.LENGTH, new Length().GetUnit("Meter"));
        units.put(CATEGORY_NAME.VOLUME, new Volume().GetUnit("Cubic Meter"));
        units.put(CATEGORY_NAME.WEIGHT, new Weight().GetUnit("Kilogram"));
        systemName = "Metric";
    }
}
