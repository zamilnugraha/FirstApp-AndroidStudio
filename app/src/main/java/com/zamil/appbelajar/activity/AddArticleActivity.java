package com.zamil.appbelajar.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.zamil.appbelajar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddArticleActivity extends AppCompatActivity {

    EditText editTextPublishDate;
    final Calendar myCalendar = Calendar.getInstance();
    Spinner spinner;
    Button buttonSave;
    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        setTitle("Add Article");
        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArticleSave();
            }
        });

        editTextTitle = findViewById(R.id.titleArticle);
        editTextDescription = findViewById(R.id.description);
        editTextPhoneNo = findViewById(R.id.phoneNo);

        initialSprinner();
        initialDate();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextPublishDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void initialSprinner(){
        spinner = (Spinner) findViewById(R.id.category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void initialDate(){
        editTextPublishDate = (EditText) findViewById(R.id.publishDate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editTextPublishDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddArticleActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void addArticleSave() {
        String titleValidation = editTextTitle.getText().toString();
        String descriptionValidation = editTextDescription.getText().toString();
        String phoneValidation = editTextPhoneNo.getText().toString();
        String publishDateValidation = editTextPublishDate.getText().toString();
        String spinnerValidation = spinner.toString();
        if (titleValidation.equalsIgnoreCase("")) {
            Toast.makeText(AddArticleActivity.this, "Title Empty", Toast.LENGTH_SHORT).show();
        } else if (descriptionValidation.equalsIgnoreCase("")) {
            Toast.makeText(AddArticleActivity.this, "Description Empty", Toast.LENGTH_SHORT).show();
        } else if (phoneValidation.equalsIgnoreCase("")) {
            Toast.makeText(AddArticleActivity.this, "Phone Empty", Toast.LENGTH_SHORT).show();
        } else if (spinnerValidation.equalsIgnoreCase("")) {
            Toast.makeText(AddArticleActivity.this, "Categoty Empty", Toast.LENGTH_SHORT).show();
        }
    }
}
