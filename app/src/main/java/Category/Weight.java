package Category;

import Unit.Unit;

public class Weight extends Category{
    public Weight()
    {
        super();

        controlUnit = new Unit("kg", 1, 0);

        unitDictionary.put("Milligram", new Unit("mg", 1000000, 0));
        unitDictionary.put("Gram", new Unit("g", 1000, 0));
        unitDictionary.put("Kilogram", controlUnit);
        unitDictionary.put("Ton", new Unit("t", 0.001, 0));
        unitDictionary.put("Pound", new Unit("lb", 2.2046244202, 0));
        unitDictionary.put("Ounce", new Unit("oz", 35.273990723, 0));

    }
}
