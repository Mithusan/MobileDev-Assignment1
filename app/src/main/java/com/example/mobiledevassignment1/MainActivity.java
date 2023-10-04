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
    //Initialize Variables
    int principal;
    double interest;
    int term;
    double monthlyPayment;
    EditText principalInput;
    EditText interestInput;
    EditText termInput;

    //Function to check if TextFields are Valid, Returns Boolean
    public boolean checkIsValid(int[] ids) {
        boolean isValid = true;//Variable Starts Valid until Further Checks

        //Loops for amount of items in ids array
        for(int id: ids) {
            EditText input = (EditText)findViewById(id);

            //If Current TextField is Empty
            if(TextUtils.isEmpty(input.getText().toString())) {
                Toast.makeText(MainActivity.this, "Empty fields not allowed!", Toast.LENGTH_SHORT).show();//Error Msg
                isValid = false;
                break;//Prevent Error - If Value is Empty next Check cannot occur
            }

            //If Value of Current TextField is <= 0
            if (Double.valueOf(input.getText().toString()) <= 0){
                Toast.makeText(MainActivity.this, "Values Entered Must be Greater than 0!", Toast.LENGTH_SHORT).show();//Error Msg
                isValid = false;
            }
        }
        return isValid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Put Id of Input Field in an Array to be used later in ValidChecks
        int[] ids = new int[]{
                R.id.input_principalAmount,
                R.id.input_interestRate,
                R.id.input_term
        };

        //Initialize Elements
        TextView output = (TextView) findViewById(R.id.text_output);
        Button calc = (Button) findViewById(R.id.btn_calculate);

        //Grab User Input
        principalInput = (EditText) findViewById(R.id.input_principalAmount);
        interestInput = (EditText) findViewById(R.id.input_interestRate);
        termInput = (EditText) findViewById(R.id.input_term);

        //Listens for when 'Calculate' Button is Clicked
        calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(checkIsValid(ids)){ //If Valid
                    //Converts String Inputs into Integers
                    principal = Integer.valueOf(principalInput.getText().toString());
                    interest = ((Double.valueOf(interestInput.getText().toString())) / 100) / 12;
                    term = Integer.valueOf(termInput.getText().toString());

                    //Compute
                    monthlyPayment = (principal * interest * (Math.pow(1 + interest, term))) / ((Math.pow(1 + interest, term)) - 1);

                    //Output
                    output.setText("Monthly Payment: $" + String.format("%.2f", monthlyPayment));
                }
            }
        });
    }
}