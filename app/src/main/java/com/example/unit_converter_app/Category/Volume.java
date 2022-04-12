package com.example.unit_converter_app.Category;

import com.example.unit_converter_app.Unit.Unit;

public class Volume extends Category{
    public Volume()
    {
        super();

        controlUnit = new Unit("l", 1, 0);

        PutUnit("Milliliter", new Unit("ml", 1000, 0));
        PutUnit("Liter", controlUnit);
        PutUnit("Cubic Centimeter", new Unit("cm³", 1000, 0));
        PutUnit("Cubic Meter", new Unit("m³", 0.001, 0));
        PutUnit("Cup", new Unit("cup", 4.2267548297, 0));
        PutUnit("Pint", new Unit("pint", 2.1133774149, 0));
        PutUnit("Quart", new Unit("qt", 1.0566887074, 0));
        PutUnit("Gallon", new Unit("gal", 0.2641721769, 0));
        PutUnit("Teaspoon", new Unit("tsp", 202.88423183, 0));
        PutUnit("Tablespoon", new Unit("tbsp", 67.628077276 , 0));
    }
}
