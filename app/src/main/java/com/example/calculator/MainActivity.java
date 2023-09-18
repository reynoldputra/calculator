package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private TextView textViewResult, textViewOperant;
    private int num1, num2;
    private String operant;

    private void initNumber () {
        num1 = 0;
        num2 = 0;
        changeOperant("+");
        showResult(0);
    }

    private void getNumber () {
        editText1 = (EditText) findViewById(R.id.first);
        editText2 = (EditText) findViewById(R.id.second);
        num1 = Integer.parseInt(editText1.getText().toString());
        num2 = Integer.parseInt(editText2.getText().toString());
    }


    private void showResult (int res) {
        textViewResult = (TextView) findViewById(R.id.result);
        textViewResult.setText(num1 + " " + operant + " " + num2 + " = " + res);
    }

    private void changeOperant (String newOperant) {
        operant = newOperant;
        textViewOperant = (TextView) findViewById(R.id.operant);
        textViewOperant.setText(operant);
    }


    public void doSum (View v) {
        getNumber();
        changeOperant("+");
        showResult(num1+num2);
    }

    public void doSub (View v) {
        getNumber();
        changeOperant("-");
        showResult(num1-num2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initNumber();
    }
}