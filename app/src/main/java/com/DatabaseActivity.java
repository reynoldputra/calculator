package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculator.R;

public class DatabaseActivity extends AppCompatActivity {
    private EditText nrp, nama;
    private Button simpan, ambildata, delete, update;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper Opendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        nrp = findViewById(R.id.nrp);
        nama = findViewById(R.id.nama);
        simpan = findViewById(R.id.Simpan);
        ambildata = findViewById(R.id.ambildata);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);


        Opendb = new SQLiteOpenHelper(this, "db.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // Kode untuk membuat tabel jika belum ada
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // Kode untuk mengupgrade database jika diperlukan
            }
        };
        dbku = Opendb.getWritableDatabase();
        dbku.execSQL("CREATE TABLE IF NOT EXISTS mhs(nrp TEXT, nama TEXT);");

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
            }
        });

        ambildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambildata();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }


    @Override
    protected void onStop() {
        dbku.close();
        Opendb.close();
        super.onStop();
    }


    private void simpan() {
        ContentValues dataku = new ContentValues();

        dataku.put("nrp", nrp.getText().toString());
        dataku.put("nama", nama.getText().toString());
        dbku.insert("mhs", null, dataku);

        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show();
    }

    private void ambildata() {
        Cursor cur = dbku.rawQuery("select * from mhs where nrp='" + nrp.getText().toString() + "'", null);

        if (cur.getCount() > 0) {
            cur.moveToFirst();
            int idx = cur.getColumnIndex("nama");
            if (idx != -1) {
                String nameStr = cur.getString(idx);
                Toast.makeText(this, nameStr, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
        }
    }

    private void update() {
        ContentValues dataku = new ContentValues();

        dataku.put("nrp", nrp.getText().toString());
        dataku.put("nama", nama.getText().toString());

        dbku.update("mhs", dataku, "nrp='" + nrp.getText().toString() + "'", null);

        Toast.makeText(this, "Data Terupdate", Toast.LENGTH_LONG).show();
    }

    private void delete() {
        dbku.delete("mhs", "nrp='" + nrp.getText().toString() + "'", null);
        Toast.makeText(this, "Data Terhapus", Toast.LENGTH_LONG).show();
    }

}