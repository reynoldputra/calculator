package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.DatabaseActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private TextView textViewResult, textViewOperant, textViewName;
    private int num1, num2;
    private String operant;

    private Button btAlert;
    private Button btInputDialog;
    private String m_Text;

    private Button b1  ;

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

        b1 = (Button) findViewById(R.id.navigate);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, DatabaseActivity.class);
                        startActivity(i);
                    }
                }
        );
        btAlert = (Button) findViewById(R.id.closedialog);
        btAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        btInputDialog = (Button) findViewById(R.id.inputdialog);
        btInputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Hello World");

        alertDialogBuilder
                .setMessage("Have a nice day !")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void showInputDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input your name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                textViewName = (TextView) findViewById(R.id.name);
                textViewName.setText(m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
