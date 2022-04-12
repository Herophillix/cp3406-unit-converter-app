package com.example.unit_converter_app.UnitSystem;

import com.example.unit_converter_app.Category.*;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;

public class UnitedKingdom extends UnitSystem {
    public UnitedKingdom()
    {
        super();

        units.put(CATEGORY_NAME.TEMPERATURE, new Temperature().GetUnit("Celsius"));
        units.put(CATEGORY_NAME.LENGTH, new Length().GetUnit("Meter"));
        units.put(CATEGORY_NAME.VOLUME, new Volume().GetUnit("Milliliter"));
        units.put(CATEGORY_NAME.WEIGHT, new Weight().GetUnit("Gram"));
        systemName = "UK";
    }
}
