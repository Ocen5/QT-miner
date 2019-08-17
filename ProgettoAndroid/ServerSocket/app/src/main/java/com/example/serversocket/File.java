package com.example.serversocket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.client.myapplication.client.R;

public class File extends AppCompatActivity {
    EditText etTable, etRadius;
    TextView tvMessages;
    String Table_name;
    double Radius;
    static String testo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);
        etTable = findViewById(R.id.Table);
        etRadius = findViewById(R.id.Radius);
        tvMessages = findViewById(R.id.ShowFiles);
        final Button btnInvio = findViewById(R.id.Invio);
        tvMessages.setMovementMethod(new ScrollingMovementMethod());
        btnInvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                Table_name = etTable.getText().toString().trim();
                Radius = Double.parseDouble(etRadius.getText().toString().trim());
                btnInvio.setEnabled(true);
                testo = MainActivity.learning_from_file(Table_name, Radius);
                tvMessages.setText(testo);
            }
        });

    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(File.this);
        builder.setTitle("Exit");
        builder.setMessage("Would you choose a new Option?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                runDialog();
            }
        });
	    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
            public void onClick(DialogInterface dialogInterface, int i){
                System.exit(0);
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
