package com.example.unit_converter_app.Category;

import com.example.unit_converter_app.Unit.Unit;

public class Length extends Category{

    public Length()
    {
        super();
        controlUnit = new Unit("m", 1, 0);

        PutUnit("Millimeter", new Unit("mm", 1000, 0));
        PutUnit("Centimeter", new Unit("cm", 100, 0));
        PutUnit("Meter", controlUnit);
        PutUnit("Kilometer", new Unit("km", 0.001, 0));
        PutUnit("Mile", new Unit("mi", 0.0006213689, 0));
        PutUnit("Yard", new Unit("yd", 1.0936132983, 0));
        PutUnit("Foot", new Unit("ft", 3.280839895, 0));
        PutUnit("Inch", new Unit("in", 39.37007874, 0));

    }
}
