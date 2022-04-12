package com.example.unit_converter_app.Category;

import com.example.unit_converter_app.Unit.Unit;

public class Temperature extends Category{

    public Temperature()
    {
        controlUnit = new Unit("°C", 1, 0);

        PutUnit("Celsius", controlUnit);
        PutUnit("Kelvin", new Unit("K", 1, 273.15));
        PutUnit("Fahrenheit", new Unit("°F", 1.80, 32));
    }

}
