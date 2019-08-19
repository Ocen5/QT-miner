package com.example.ablminer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DataB extends AppCompatActivity {

    EditText etTable, etRadius;
    TextView tvMessages;
    String Table_name;
    double Radius;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datab);
        etTable = findViewById(R.id.TableD);
        etRadius = findViewById(R.id.RadiusD);
        tvMessages = findViewById(R.id.ShowClustersD);
        Button btnInvio = findViewById(R.id.InvioD);
        btnInvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                Table_name = etTable.getText().toString().trim();
                Radius = Double.parseDouble(etRadius.getText().toString().trim());
                String testo=MainActivity.storeTableFromDb(Table_name,Radius);
                tvMessages.setMovementMethod(new ScrollingMovementMethod());
                tvMessages.setText(testo);
                Toast.makeText(getApplicationContext()," File Saved! ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(DataB.this);
        builder.setTitle("Finish");
        builder.setMessage("Would you repeat");
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
               runOptions();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void runOptions() {
    final AlertDialog.Builder builder = new AlertDialog.Builder(DataB.this);
        builder.setTitle("Exit");
        builder.setMessage("Would you choose a new Option?");
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                Intent main = new Intent(getBaseContext(),MainActivity.class);
                startActivity(main);
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                runDialog();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void runDialog(){
        Intent dialog = new Intent(this, Dialog.class);
        startActivity(dialog);
    }
}