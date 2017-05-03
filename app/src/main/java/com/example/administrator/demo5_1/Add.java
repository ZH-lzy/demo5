package com.example.administrator.demo5_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Add extends Activity implements OnClickListener {
    private EditText Name;
    private EditText Address;
    private EditText Number;

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnOK:

                getIntent().putExtra("text", Name.getText().toString());
                getIntent().putExtra("text1", Address.getText().toString());
                getIntent().putExtra("text2", Number.getText().toString());

                setResult(2, getIntent());
                break;

            case R.id.btnCancel:
                setResult(6);
                break;
        }
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Name = (EditText) findViewById(R.id.Name);
        Address = (EditText) findViewById(R.id.Address);
        Number = (EditText) findViewById(R.id.Number);
        Button btnOK = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

}