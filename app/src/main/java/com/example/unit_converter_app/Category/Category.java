package com.example.unit_converter_app.Category;

import com.example.unit_converter_app.Unit.Unit;

import java.util.ArrayList;
import java.util.Hashtable;

public class Category {
    protected Unit controlUnit;
    public Hashtable<String, Unit> unitDictionary;
    private ArrayList<String> unitNameList;

    public enum CATEGORY_NAME
    {
        TEMPERATURE,
        LENGTH,
        VOLUME,
        WEIGHT,
    }

    public Category()
    {
        controlUnit = new Unit("-None-", 1, 0);
        unitDictionary = new Hashtable<>();
        unitNameList = new ArrayList<>();
    }

    protected void PutUnit(String name, Unit unit)
    {
        unitDictionary.put(name, unit);
        unitNameList.add(name + " (" + unit.GetSign() + ")");
    }

    public Unit GetUnit(String name)
    {
        if(unitDictionary.containsKey(name))
            return unitDictionary.get(name);
        else
        {
            for(String unitName : unitDictionary.keySet())
            {
                Unit currentUnit = unitDictionary.get(unitName);
                if(currentUnit.GetSign().equalsIgnoreCase(name))
                    return currentUnit;
            }
        }
        return null;
    }

    public ArrayList<String> GetUnitNameList()
    {
        return unitNameList;
    }

    public double Convert(double value, String inputUnit, String outputUnit)
    {
        if(unitDictionary.containsKey(inputUnit) && unitDictionary.containsKey(outputUnit))
        {
            Unit input = unitDictionary.get(inputUnit);
            if(input != controlUnit)
                value = input.ConvertToControl(value);

            Unit output = unitDictionary.get(outputUnit);
            return output.Convert(value);
        }
        return value;
    }

    public double Convert(double value, Unit input, Unit output)
    {
        if(input != controlUnit)
            value = input.ConvertToControl(value);
        return output.Convert(value);
    }
}
