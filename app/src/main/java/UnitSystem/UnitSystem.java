package UnitSystem;

import Unit.Unit;

import Category.Category.CATEGORY_NAME;

import java.util.Hashtable;

public class UnitSystem {
    protected Hashtable<CATEGORY_NAME, Unit> units;

    public UnitSystem()
    {
        units = new Hashtable<>();
    }

    public Unit GetUnit(CATEGORY_NAME categoryName)
    {
        return units.get(categoryName);
    }
}
