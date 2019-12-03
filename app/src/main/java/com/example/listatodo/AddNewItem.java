package com.example.listatodo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewItem extends AppCompatActivity {

    private EditText etName;
    private RadioButton rbDone;
    private static TextView tvDate;
    private static TextView tvTime;
    private Button btnChooseTime;
    private Button btnChooseDate;
    private Button btnAdd;
    private Button btnCancel;

    static private final String TAG = "ToDoList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        etName = (EditText) findViewById(R.id.editTextName);
        rbDone = (RadioButton) findViewById(R.id.radioButtonDone);
        tvDate = (TextView) findViewById(R.id.textViewDate);
        tvTime = (TextView) findViewById(R.id.textViewTime);
        btnChooseTime = (Button) findViewById(R.id.buttonChooseTime);
        btnChooseDate = (Button) findViewById(R.id.buttonChooseDate);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("status", rbDone.isChecked());
                intent.putExtra("time", tvTime.getText());
                intent.putExtra("date", tvDate.getText());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnChooseDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered buttonDate.onClickListener.onClick");
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnChooseTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered buttonTime.onClickListener.onClick");
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            tvTime.setText(hourOfDay+":"+minute);
        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
            tvDate.setText(df.format(c.getTime()));
        }
    }
}
