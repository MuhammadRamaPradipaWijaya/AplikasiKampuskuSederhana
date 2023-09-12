package com.muhammadrama.aplikasikampusku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Register extends AppCompatActivity {

    EditText editUsername, editPassword, editRepassword; // Removed editEmail and editNamaLengkap
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        editUsername = findViewById(R.id.editUsername); // Changed from editEmail
        editPassword = findViewById(R.id.editPassword);
        editRepassword = findViewById(R.id.editRePassword);
        btnSimpan = findViewById(R.id.btnsimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()) {
                    if (editPassword.getText().toString().equals(editRepassword.getText().toString())) {
                        simpanFileData();
                    } else {
                        Toast.makeText(Register.this, "Password dan Re-Password tidak cocok", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        if (editUsername.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") ||
                editRepassword.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile =
                editUsername.getText().toString() + ";" +
                        editPassword.getText().toString();

        File file = new File(getFilesDir(), editUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
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
