// НЕ РАБОТАЕТ, КАК ОПИСАНО В УРОКЕ!!!!
package com.example.study32;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        (findViewById(R.id.btnWeb)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//"com.example.study32.Browser");
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.ya.ru"));
                startActivity(intent);
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ya.ru")));
            }
        });
    }
}