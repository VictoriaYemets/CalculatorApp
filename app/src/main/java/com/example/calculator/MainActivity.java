package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNum1, etNum2, etOperations;
    TextView tvOutputResult;
    Button btnCalculateResult;
    private float result;
    Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.number_1_EditText);
        etNum2 = findViewById(R.id.number_2_EditText);
        tvOutputResult = findViewById(R.id.resultTextView);
        etOperations = findViewById(R.id.operationEditText);
        btnCalculateResult = findViewById(R.id.calculateButton);
        btnCalculateResult.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        float number1;
        float number2;
        result = 0;
        String specificOperation;
        boolean correctOperation = true;

        try {
            number1 = Float.parseFloat(etNum1.getText().toString());
            number2 = Float.parseFloat(etNum2.getText().toString());
            specificOperation = etOperations.getText().toString();

            switch (specificOperation) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if(number2 == 0) throw new ArithmeticException();
                    result = number1 / number2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }
        }catch(ArithmeticException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.divide_zero,duration);
            toastError.show();
            return;
        }catch(NullPointerException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.null_data,duration);
            toastError.show();
            return;
        }catch(NumberFormatException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.wrong_format,duration);
            toastError.show();
            return;
        }

        if(correctOperation){
            tvOutputResult.setText(number1+" "+specificOperation+" "+number2+" "+" = "+result+"");
        }else {
            int duration = Toast.LENGTH_SHORT;
            if (toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this,R.string.wrong_operation,duration);
            toastError.show();
            return;
        }

    }
}

