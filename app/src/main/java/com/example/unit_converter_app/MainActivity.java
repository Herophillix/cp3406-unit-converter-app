package com.example.unit_converter_app;

import static com.example.unit_converter_app.Unit.Unit.RoundTo10sf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.example.unit_converter_app.Category.*;
import com.example.unit_converter_app.Category.Category.CATEGORY_NAME;

import com.example.unit_converter_app.Unit.Unit;
import com.example.unit_converter_app.UnitSystem.*;
import com.example.unit_converter_app.UnitSystem.UnitSystem.SYSTEM_NAME;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    // UI
    private Spinner categorySpinner;

    // Top
    private Spinner topSpinner;

    private LinearLayout topDecimal;
    private EditText topDecimalInput;
    private TextView topDecimalUnit;
    private TextWatcher topDecimalUnitWatcher;
    private Unit currentTopUnit;

    private EditText topInput;

    private Spinner bottomSpinner;

    // Bottom
    private LinearLayout bottomDecimal;
    private EditText bottomDecimalInput;
    private TextView bottomDecimalUnit;
    private TextWatcher bottomDecimalUnitWatcher;
    private Unit currentBottomUnit;

    private TextView bottomText;

    private View filler;

    // Backend
    private Hashtable<Category.CATEGORY_NAME, Category> categoryDictionary;
    private Category currentCategory;

    private Hashtable<UnitSystem.SYSTEM_NAME, UnitSystem> systemDictionary;
    private UnitSystem currentSystem;

    private TextParser textParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindAllWidgets();

        InitializeBackend();
        InitializeCategory();
        InitializeTextWidgets();

    }

    private void FindAllWidgets()
    {
        categorySpinner = findViewById(R.id.category_spinner);

        topSpinner = findViewById(R.id.top_spinner);
        topDecimal = findViewById(R.id.top_decimal);
        topDecimalInput = findViewById(R.id.top_decimal_input);
        topDecimalUnit = findViewById(R.id.top_decimal_unit);
        topInput = findViewById(R.id.top_input);

        bottomSpinner = findViewById(R.id.bottom_spinner);
        bottomDecimal = findViewById(R.id.bottom_decimal);
        bottomDecimalInput = findViewById(R.id.bottom_decimal_input);
        bottomDecimalUnit = findViewById(R.id.bottom_decimal_unit);
        bottomText = findViewById(R.id.bottom_text);

        filler = findViewById(R.id.filler);
    }

    private void InitializeBackend()
    {
        categoryDictionary = new Hashtable<>();
        categoryDictionary.put(CATEGORY_NAME.TEMPERATURE, new Temperature());
        categoryDictionary.put(CATEGORY_NAME.LENGTH, new Length());
        categoryDictionary.put(CATEGORY_NAME.VOLUME, new Volume());
        categoryDictionary.put(CATEGORY_NAME.WEIGHT, new Weight());
        currentCategory = categoryDictionary.get(CATEGORY_NAME.TEMPERATURE);

        systemDictionary = new Hashtable<>();
        systemDictionary.put(SYSTEM_NAME.METRIC, new Metric());
        systemDictionary.put(SYSTEM_NAME.IMPERIAL, new Imperial());
        systemDictionary.put(SYSTEM_NAME.US, new UnitedStates());
        systemDictionary.put(SYSTEM_NAME.UK, new UnitedKingdom());
        currentSystem = systemDictionary.get(SYSTEM_NAME.METRIC);

        textParser = new TextParser();
    }

    private void InitializeCategory()
    {
        ArrayList<String> spinnerChoice = new ArrayList<>();
        for(CATEGORY_NAME categoryName : categoryDictionary.keySet())
        {
            String choiceName = categoryName.toString();
            spinnerChoice.add(choiceName.substring(0,1).toUpperCase() + choiceName.substring(1).toLowerCase());
        }
        spinnerChoice.add("Text");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerChoice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                OnNewCategorySelected(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void InitializeTextWidgets()
    {
        topDecimalUnitWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                StartUnitConversion(topDecimalInput);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        topDecimalInput.addTextChangedListener(topDecimalUnitWatcher);

        bottomDecimalUnitWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                StartUnitConversion(bottomDecimalInput);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        bottomDecimalInput.addTextChangedListener(bottomDecimalUnitWatcher);

        topInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                StartSystemConversion();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void OnNewCategorySelected(String selectedItem)
    {
        if(selectedItem.equals("Text"))
        {
            ArrayList<String> spinnerChoices = new ArrayList<>();
            for(SYSTEM_NAME systemName : systemDictionary.keySet())
            {
                String systemNameString = systemDictionary.get(systemName).systemName;
                spinnerChoices.add(systemNameString);
            }

            topSpinner.setVisibility(View.GONE);
            topDecimal.setVisibility(View.GONE);
            topInput.setVisibility(View.VISIBLE);

            bottomDecimal.setVisibility(View.GONE);
            bottomText.setVisibility(View.VISIBLE);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, spinnerChoices);
            bottomSpinner.setAdapter(adapter);
            bottomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    OnNewSystemSelected(selectedItem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filler.setVisibility(View.GONE);
        }
        else
        {
            selectedItem = selectedItem.toUpperCase();
            for(CATEGORY_NAME categoryName : categoryDictionary.keySet())
            {
                if(selectedItem.equals(categoryName.toString()))
                {
                    currentCategory = categoryDictionary.get(categoryName);
                    break;
                }
            }

            // Top
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, currentCategory.GetUnitNameList());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            topSpinner.setAdapter(adapter);
            topSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    OnNewUnitSelected(selectedItem, topSpinner);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            topSpinner.setVisibility(View.VISIBLE);
            topDecimal.setVisibility(View.VISIBLE);
            topInput.setVisibility(View.GONE);

            // Bottom
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, currentCategory.GetUnitNameList());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            bottomSpinner.setAdapter(adapter);
            bottomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    OnNewUnitSelected(selectedItem, bottomSpinner);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            bottomDecimal.setVisibility(View.VISIBLE);
            bottomText.setVisibility(View.GONE);

            filler.setVisibility(View.VISIBLE);
        }
    }

    private void OnNewUnitSelected(String selectedItem, Spinner position)
    {
        if(position == topSpinner)
        {
            for (String unitName : currentCategory.unitDictionary.keySet())
            {
                if (selectedItem.startsWith(unitName))
                {
                    currentTopUnit = currentCategory.unitDictionary.get(unitName);
                    break;
                }
            }
            StartUnitConversion(topDecimalInput);
            if (currentTopUnit != null)
            {
                topDecimalUnit.setText(currentTopUnit.GetSign());
            }
        }
        else
        {
            for (String unitName : currentCategory.unitDictionary.keySet())
            {
                if (selectedItem.startsWith(unitName))
                {
                    currentBottomUnit = currentCategory.unitDictionary.get(unitName);
                    break;
                }
            }
            StartUnitConversion(topDecimalInput);
            if (currentBottomUnit != null)
            {
                bottomDecimalUnit.setText(currentBottomUnit.GetSign());
            }
        }
    }

    private void StartUnitConversion(EditText origin)
    {
        EditText destination;
        Unit originUnit, destinationUnit;
        TextWatcher toRemoveTemp;

        if(origin == topDecimalInput)
        {
            destination = bottomDecimalInput;
            toRemoveTemp = bottomDecimalUnitWatcher;
            originUnit = currentTopUnit;
            destinationUnit = currentBottomUnit;
        }
        else
        {
            destination = topDecimalInput;
            toRemoveTemp = topDecimalUnitWatcher;
            originUnit = currentBottomUnit;
            destinationUnit = currentTopUnit;
        }

        String valueString = origin.getText().toString();
        try {
            Double value = Double.parseDouble(valueString);

            Double result = currentCategory.Convert(value, originUnit, destinationUnit);
            result = RoundTo10sf(result);

            destination.removeTextChangedListener(toRemoveTemp);
            destination.setText(Double.toString(result));
            destination.addTextChangedListener(toRemoveTemp);

        } catch (Exception e)
        {

        }
    }

    private void OnNewSystemSelected(String selectedItem)
    {
        for(SYSTEM_NAME systemName : systemDictionary.keySet())
        {
            String systemNameString = systemDictionary.get(systemName).systemName;
            if(systemNameString.equals(selectedItem))
            {
                currentSystem = systemDictionary.get(systemName);
                break;
            }
        }
        StartSystemConversion();
    }

    private void StartSystemConversion()
    {
        String[] texts = topInput.getText().toString().split("\n");
        String output = "";
        for(String text: texts)
        {
            output = output + textParser.Parse(text, categoryDictionary, currentSystem) + "\n";
        }
        bottomText.setText(output);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}