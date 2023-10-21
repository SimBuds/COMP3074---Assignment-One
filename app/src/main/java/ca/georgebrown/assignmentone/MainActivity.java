package ca.georgebrown.assignmentone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Student Name Casey Hsu, 101376814

    // Initialize UI elements
    private EditText usersHours, usersRate;
    private TextView pay, overtime, totalPay, tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding about me button
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Initialize UI elements
        usersHours = findViewById(R.id.usersHours);
        usersRate = findViewById(R.id.usersRate);
        Button buttonCalculate = findViewById(R.id.calculate);
        pay = findViewById(R.id.pay);
        overtime = findViewById(R.id.overtime);
        totalPay = findViewById(R.id.totalPay);
        tax = findViewById(R.id.tax);

        // Setup listeners for minimizing keyboard
        setupEditorActionListener(usersHours);
        setupEditorActionListener(usersRate);

        // Setup listener for calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePayAndTax();
                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && imm.isAcceptingText()) { // verify if the soft keyboard is open
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    private void calculatePayAndTax() {
        // Check for empty fields
        if (usersHours.getText().toString().isEmpty()) {
            usersHours.setError("Please enter your hours");
            return;
        }
        if (usersRate.getText().toString().isEmpty()) {
            usersRate.setError("Please enter your rate");
            return;
        }

        // Get user input and parse to double
        double hours = Double.parseDouble(usersHours.getText().toString());
        double rate = Double.parseDouble(usersRate.getText().toString());

        double regularPay, overtimePay, total, taxAmount;

        // Calculate pay and tax
        if (hours <= 40) {
            regularPay = hours * rate;
            overtimePay = 0;
        } else {
            regularPay = 40 * rate;
            overtimePay = (hours - 40) * rate * 1.5;
        }

        total = regularPay + overtimePay;
        taxAmount = total * 0.18;

        // Display results
        pay.setText(String.format("Pay: $%.2f", regularPay));
        overtime.setText(String.format("Overtime Pay: $%.2f", overtimePay));
        totalPay.setText(String.format("Total Pay: $%.2f", total));
        tax.setText(String.format("Tax: $%.2f", taxAmount));
    }

    private void setupEditorActionListener(EditText editText) {
        // Setup listener for minimizing keyboard after user presses enter
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && imm.isAcceptingText()) { // verify if the soft keyboard is open
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}