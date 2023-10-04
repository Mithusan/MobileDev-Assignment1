package com.example.mobiledevassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int principal;
    double interest;
    int term;
    double monthlyPayment;

    EditText principalInput;
    EditText interestInput;
    EditText termInput;

    public boolean checkIsEmpty(int[] ids) {
        boolean isEmpty = false;

        for(int id: ids) {
            EditText input = (EditText)findViewById(id);

            if(TextUtils.isEmpty(input.getText().toString())) {
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = (TextView) findViewById(R.id.text_output);
        Button calc = (Button) findViewById(R.id.btn_calculate);

        int[] ids = new int[]{
                R.id.input_principalAmount,
                R.id.input_interestRate,
                R.id.input_term
        };

        principalInput = (EditText) findViewById(R.id.input_principalAmount);
        interestInput = (EditText) findViewById(R.id.input_interestRate);
        termInput = (EditText) findViewById(R.id.input_term);

        calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!checkIsEmpty(ids)){
                    principal = Integer.valueOf(principalInput.getText().toString());
                    interest = ((Double.valueOf(interestInput.getText().toString())) / 100) / 12;
                    term = Integer.valueOf(termInput.getText().toString());

                    monthlyPayment = (principal * interest * (Math.pow(1 + interest, term))) / ((Math.pow(1 + interest, term)) - 1);
                    output.setText("Monthly Payment: $" + String.format("%.2f", monthlyPayment));
                } else {
                    Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}