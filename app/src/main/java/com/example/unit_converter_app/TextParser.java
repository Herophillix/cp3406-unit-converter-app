package com.example.unit_converter_app;

import com.example.unit_converter_app.Category.Category;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;
import com.example.unit_converter_app.Unit.Unit;
import com.example.unit_converter_app.UnitSystem.UnitSystem;

import static com.example.unit_converter_app.Unit.Unit.RoundTo10sf;

import java.util.Hashtable;

public class TextParser {
    public TextParser()
    {

    }

    public String Parse(String text, Hashtable<CATEGORY_NAME, Category> categoryDictionary, UnitSystem targetUnitSystem)
    {
        String[] words = text.split(" ");
        String returnString = "";
        for(int i = 0; i < words.length; ++i)
        {
            String valueString = words[i];
            boolean isValueConverted = false;
            if(isNumeric(valueString))
            {
                if(i + 1 < words.length)
                {
                    String signString = words[i + 1].replaceAll("\\p{Punct}", "");
                    for(CATEGORY_NAME catName : categoryDictionary.keySet())
                    {
                        Category category = categoryDictionary.get(catName);
                        Unit unit = category.GetUnit(signString);
                        if(unit != null)
                        {
                            i = i + 1;
                            isValueConverted = true;
                            Unit targetSystemUnit = targetUnitSystem.GetUnit(catName);

                            double value = Double.parseDouble(valueString);
                            double result = category.Convert(value, unit, targetSystemUnit);
                            result = RoundTo10sf(result);

                            returnString = returnString + result + " " + targetSystemUnit.GetSign() + " ";
                        }
                    }
                }
            }
            if (!isValueConverted)
            {
                returnString = returnString + valueString + " ";
            }
        }
        return returnString;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
