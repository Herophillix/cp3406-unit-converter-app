package com.example.unit_converter_app;

import com.example.unit_converter_app.Category.*;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;
import com.example.unit_converter_app.UnitSystem.*;

import java.util.Hashtable;

import static com.example.unit_converter_app.Unit.Unit.RoundTo3sf;

public class Main {

    private static Hashtable<CATEGORY_NAME, Category> categoryDictionary;

    public static void main(String[] args) {
        categoryDictionary = new Hashtable<>();
        categoryDictionary.put(CATEGORY_NAME.TEMPERATURE, new Temperature());
        categoryDictionary.put(CATEGORY_NAME.LENGTH, new Length());
        categoryDictionary.put(CATEGORY_NAME.VOLUME, new Volume());
        categoryDictionary.put(CATEGORY_NAME.WEIGHT, new Weight());

        Convert(CATEGORY_NAME.TEMPERATURE, 234, "Celsius", "Fahrenheit");
        Convert(CATEGORY_NAME.LENGTH, 23.5623, "Meter", "Foot");
        Convert(CATEGORY_NAME.VOLUME, 2234, "Tablespoon", "Liter");
        Convert(CATEGORY_NAME.WEIGHT, 10.7234, "Pound", "Gram");

        TextParser textParser = new TextParser();

        String[] cakeRecipe = new String[] {
                "1 cup white sugar",
                "0.5 cup unsalted butter",
                "2 large eggs",
                "2 tsp vanilla extracts",
                "1.5 cup all-purpose flour",
                "1.75 tsp baking powder",
                "0.5 cup milk"
        };

        UnitSystem targetSystem = new UnitedKingdom();

        System.out.println();
        System.out.println("Recipe for White Cake:");

        for(String item : cakeRecipe)
        {
            System.out.println(textParser.Parse(item, categoryDictionary, targetSystem));
        }
    }

    public static void Convert(CATEGORY_NAME categoryName, double value, String inputUnit, String outputUnit)
    {
        if(categoryDictionary.containsKey(categoryName))
        {
            Category category = categoryDictionary.get(categoryName);

            String inputSign = category.GetUnit(inputUnit).GetSign();
            String outputSign = category.GetUnit(outputUnit).GetSign();

            double outputValue = category.Convert(value, inputUnit, outputUnit);
            outputValue = RoundTo3sf(value);

            System.out.println(value + " " + inputSign + " = " + outputValue + " " + outputSign);
        }
    }
}