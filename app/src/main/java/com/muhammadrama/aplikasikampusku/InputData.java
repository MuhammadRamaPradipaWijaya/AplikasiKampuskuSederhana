package com.muhammadrama.aplikasikampusku;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3, text4, text5, text6;
    String edit;
    TextView textV1,textV2,textV3,textV4,textV5, textV6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Input Data");

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);

        textV1=(TextView)findViewById(R.id.textView1);
        textV2=(TextView)findViewById(R.id.textView2);
        textV3=(TextView)findViewById(R.id.textView3);
        textV4=(TextView)findViewById(R.id.textView4);
        textV5=(TextView)findViewById(R.id.textView5);
        textV6=(TextView)findViewById(R.id.textView6);
        ton1 = (Button) findViewById(R.id.button1);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                edit = text1.getText().toString();
                edit=text2.getText().toString();
                edit=text3.getText().toString();
                edit=text4.getText().toString();
                edit=text5.getText().toString();
                edit=text6.getText().toString();
                if(edit.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Kolom tidak boleh kosong...",Toast.LENGTH_SHORT).show();
                } else{
                    db.execSQL("insert into datamhs(no, nama, prodi, tgl_lahir, jenis_kel, alamat) values('" +text1.getText().toString() + "','" +
                            text2.getText().toString() + "','" +
                            text3.getText().toString() + "','" +
                            text4.getText().toString() + "','" +
                            text5.getText().toString() + "','" +
                            text6.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_LONG).show();
                    finish();
                }
                DataMahasiswa.da.RefreshList();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
