package com.example.study30;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlignActivity extends AppCompatActivity implements OnClickListener {

    Button btnLeft;
    Button btnCenter;
    Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.align);

        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnCenter = (Button) findViewById(R.id.btnCenter);
        btnRight = (Button) findViewById(R.id.btnRight);

        btnLeft.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.btnLeft)
            intent.putExtra("alignment", Gravity.LEFT);
        else if(v.getId() == R.id.btnCenter)
            intent.putExtra("alignment", Gravity.CENTER);
        else //v.getId() == R.id.btnRight
            intent.putExtra("alignment", Gravity.RIGHT);

        setResult(RESULT_OK, intent);
        finish();
    }
}