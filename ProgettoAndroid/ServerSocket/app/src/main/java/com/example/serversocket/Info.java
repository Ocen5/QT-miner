package com.example.serversocket;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.client.myapplication.client.R;

public class Info extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        TextView info=findViewById(R.id.TestoInfo);

        StringBuilder sampleString = new StringBuilder();
        sampleString.append(getString(R.string.Titolo)+"\n");
        sampleString.append(getString(R.string.Incipit)+"\n");
        sampleString.append(getString(R.string.Titolostep)+"\n");
        sampleString.append(getString(R.string.step)+"\n");
        sampleString.append(getString(R.string.Titolovantaggi)+"\n");
        sampleString.append(getString(R.string.vantaggi)+"\n");
        sampleString.append(getString(R.string.Titolosvantaggi)+"\n");
        sampleString.append(getString(R.string.svantaggi)+"\n");

        info.setMovementMethod(new ScrollingMovementMethod());
        SpannableString spannableString = new SpannableString(sampleString.toString());
        spannableString.setSpan(new RelativeSizeSpan(1.5f), 0, 43, 0);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 710, 745, 0);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 1255, 1265, 0);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 1572, 1587, 0);
        info.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        info.setText(spannableString);
    }
}
