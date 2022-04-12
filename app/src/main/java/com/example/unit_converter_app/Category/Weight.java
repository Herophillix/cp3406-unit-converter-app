package com.example.unit_converter_app.Category;

import com.example.unit_converter_app.Unit.Unit;

public class Weight extends Category{
    public Weight()
    {
        super();

        controlUnit = new Unit("kg", 1, 0);

        PutUnit("Milligram", new Unit("mg", 1000000, 0));
        PutUnit("Gram", new Unit("g", 1000, 0));
        PutUnit("Kilogram", controlUnit);
        PutUnit("Ton", new Unit("t", 0.001, 0));
        PutUnit("Pound", new Unit("lb", 2.2046244202, 0));
        PutUnit("Ounce", new Unit("oz", 35.273990723, 0));

    }
}
