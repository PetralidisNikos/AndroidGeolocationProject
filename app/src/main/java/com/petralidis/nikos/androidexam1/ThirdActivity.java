package com.petralidis.nikos.androidexam1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //-------------textViews-----------------//
        final TextView results = findViewById(R.id.textView3);
        //-------------Buttons-----------------//
        Button backbutton3 = findViewById(R.id.back3);

        //λαμβανω τα αποτελεσματα απο το SecondActivity
        Intent intent = getIntent();
        String resultlist = intent.getExtras().getString("RESULTS");
        //τα εμφανιζω σε ΤextView
        results.setText(resultlist);

        //μηνυμα σε περιπτωση που δεν βρεθει καποια καταχωρηση
        if (results.getText().toString().equals("")){
            results.setText("No Results Found");
        }


        //-------------------Listener για το κουμπι Back---------------------
        backbutton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
            }
        });
    }
}
