package Category;

import Unit.Unit;

public class Length extends Category{

    public Length()
    {
        super();
        controlUnit = new Unit("m", 1, 0);

        unitDictionary.put("Millimeter", new Unit("mm", 1000, 0));
        unitDictionary.put("Centimeter", new Unit("cm", 100, 0));
        unitDictionary.put("Meter", controlUnit);
        unitDictionary.put("Kilometer", new Unit("km", 0.001, 0));
        unitDictionary.put("Mile", new Unit("mi", 0.0006213689, 0));
        unitDictionary.put("Yard", new Unit("yd", 1.0936132983, 0));
        unitDictionary.put("Foot", new Unit("ft", 3.280839895, 0));
        unitDictionary.put("Inch", new Unit("in", 39.37007874, 0));

    }
}
