package com.example.study36;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    final String LOG_TAG = "myLogs";

    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    DBHelper dbHelper;
    SQLiteDatabase db;

    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);
        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // проверка существования записей
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            // заполним таблицу
            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close();
        dbHelper.close();
        // эмулируем нажатие кнопки btnAll
        onClick(btnAll);

    }

    public void onClick(View v) {

        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // данные с экрана
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        // переменные для query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // курсор
        Cursor c = null;

        // определяем нажатую кнопку
        if(v.getId() == R.id.btnAll) {
            // Все записи
            Log.d(LOG_TAG, "--- Все записи ---");
            c = db.query("mytable", null, null, null, null, null, null);
        }else if(v.getId() == R.id.btnFunc) {
            // Функция
            Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
            columns = new String[]{sFunc};
            c = db.query("mytable", columns, null, null, null, null, null);
        }else if(v.getId() == R.id.btnPeople) {
            // Население больше, чем
            Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");
            selection = "people > ?";
            selectionArgs = new String[]{sPeople};
            c = db.query("mytable", null, selection, selectionArgs, null, null,
                    null);
        }else if(v.getId() == R.id.btnGroup) {
            // Население по региону
            Log.d(LOG_TAG, "--- Население по региону ---");
            columns = new String[]{"region", "sum(people) as people"};
            groupBy = "region";
            c = db.query("mytable", columns, null, null, groupBy, null, null);
        }else if(v.getId() == R.id.btnHaving) {
            // Население по региону больше чем
            Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople
                    + " ---");
            columns = new String[]{"region", "sum(people) as people"};
            groupBy = "region";
            having = "sum(people) > " + sRegionPeople;
            c = db.query("mytable", columns, null, null, groupBy, having, null);
        }else{//v.getId() == R.id.btnSort
            // Сортировка
                // сортировка по
            if(rgSort.getCheckedRadioButtonId() == R.id.rName) {
                // наименование
                Log.d(LOG_TAG, "--- Сортировка по наименованию ---");
                orderBy = "name";
                // население
            }else if(rgSort.getCheckedRadioButtonId() == R.id.rPeople) {
                Log.d(LOG_TAG, "--- Сортировка по населению ---");
                orderBy = "people";
            }else if(rgSort.getCheckedRadioButtonId() == R.id.rRegion) {
                // регион
                Log.d(LOG_TAG, "--- Сортировка по региону ---");
                orderBy = "region";
            }
            c = db.query("mytable", null, null, null, null, null, orderBy);
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}