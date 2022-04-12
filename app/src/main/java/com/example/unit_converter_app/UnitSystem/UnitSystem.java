package com.example.unit_converter_app.UnitSystem;

import com.example.unit_converter_app.Unit.Unit;

import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;

import java.util.Hashtable;

public class UnitSystem {

    public enum SYSTEM_NAME
    {
        METRIC,
        IMPERIAL,
        US,
        UK,
    }

    protected Hashtable<CATEGORY_NAME, Unit> units;

    public UnitSystem()
    {
        units = new Hashtable<>();
    }

    public Unit GetUnit(CATEGORY_NAME categoryName)
    {
        return units.get(categoryName);
    }

    public String systemName;
}
