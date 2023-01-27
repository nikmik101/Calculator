package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNum1, etNum2, etOperation;
    private TextView tvResulText;
    private Button btnCalculateResult;
    private Toast toatsError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.num1_edit_text);
        etNum2 = findViewById(R.id.num2_edit_text);
        etOperation = findViewById(R.id.operation_edit_text);

        tvResulText = findViewById(R.id.result_text_view);

        btnCalculateResult = findViewById(R.id.calculate_result_button);
        btnCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1, num2, result = 0;
        boolean correctOperation = true;
        String operation = "";

        try {
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());

            operation = etOperation.getText().toString();

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "/":
                    if(num2 == 0) throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }
        }catch (ArithmeticException e){
            int duration = Toast.LENGTH_LONG;
            if(toatsError != null){
                toatsError.cancel();
            }
            toatsError = Toast.makeText(this, R.string.divide_zero, duration);
            toatsError.show();
            return;
        }
        catch (NullPointerException e){
            int duration = Toast.LENGTH_LONG;
            if(toatsError != null){
                toatsError.cancel();
            }
            toatsError = Toast.makeText(this, R.string.null_data, duration);
            toatsError.show();
            return;
        }
        catch (NumberFormatException e){
            int duration = Toast.LENGTH_LONG;
            if(toatsError != null){
                toatsError.cancel();
            }
            toatsError = Toast.makeText(this, R.string.wrong_format, duration);
            toatsError.show();
            return;
        }

        if(correctOperation == true) {
            tvResulText.setText(num1 + " " + operation + " " + num2 + "=" + result);
        }
        else{
            int duration = Toast.LENGTH_LONG;
            if(toatsError != null){
                toatsError.cancel();
            }
            toatsError = Toast.makeText(this, R.string.wrong_operation, duration);
            toatsError.show();
            return;
        }
    }
}