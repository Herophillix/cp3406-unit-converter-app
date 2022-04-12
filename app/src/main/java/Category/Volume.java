package Category;

import Unit.Unit;

public class Volume extends Category{
    public Volume()
    {
        super();

        controlUnit = new Unit("l", 1, 0);

        unitDictionary.put("Milliliter", new Unit("ml", 1000, 0));
        unitDictionary.put("Liter", controlUnit);
        unitDictionary.put("Cubic Centimeter", new Unit("cm^3", 1000, 0));
        unitDictionary.put("Cubic Meter", new Unit("m^3", 0.001, 0));
        unitDictionary.put("Cup", new Unit("cup", 4.2267548297, 0));
        unitDictionary.put("Pint", new Unit("pint", 2.1133774149, 0));
        unitDictionary.put("Quart", new Unit("qt", 1.0566887074, 0));
        unitDictionary.put("Gallon", new Unit("gal", 0.2641721769, 0));
        unitDictionary.put("Teaspoon", new Unit("tsp", 202.88423183, 0));
        unitDictionary.put("Tablespoon", new Unit("tbsp", 67.628077276 , 0));
    }
}
