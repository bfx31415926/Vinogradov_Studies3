//Делаем урок по вариантам

package com.example.study40;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    // Первый вариант
    /*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater ltInflater = getLayoutInflater();
        View view = ltInflater.inflate(R.layout.text, null, false);
        LayoutParams lp = view.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (lp == null));
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());
    }
    */

    // Второй вариант
    /*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater ltInflater = getLayoutInflater();
        View view = ltInflater.inflate(R.layout.text, null, false);
        LayoutParams lp = view.getLayoutParams();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        linLayout.addView(view);

        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (lp == null));
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());
    }
*/

    // Третий вариант
/*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater ltInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = ltInflater.inflate(R.layout.text, linLayout, false);
        LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + lp1.getClass().toString());
        Log.d(LOG_TAG, "Text of view1: " + ((TextView) view1).getText());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = ltInflater.inflate(R.layout.text, relLayout, false);
        LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
        Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());
    }
 */
    // Четвертый вариант
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater ltInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = ltInflater.inflate(R.layout.text, linLayout, true);
        LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + lp1.getClass().toString());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = ltInflater.inflate(R.layout.text, relLayout, true);
        LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
    }
}