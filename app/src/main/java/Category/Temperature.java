package Category;

import Unit.Unit;

public class Temperature extends Category{

    public Temperature()
    {
        controlUnit = new Unit("°C", 1, 0);

        unitDictionary.put("Celsius", controlUnit);
        unitDictionary.put("Kelvin", new Unit("K", 1, 273.15));
        unitDictionary.put("Fahrenheit", new Unit("°F", 1.80, 32));
    }

}
