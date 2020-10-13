package com.petralidis.nikos.androidexam1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        //-------------textViews-----------------//
       final TextView Show = findViewById(R.id.textView10);
        //-------------Buttons-----------------//
       // Button backbutton3 = findViewById(R.id.back3);

        DatabaseHelper databaseHelper2 = new DatabaseHelper(ExamActivity.this);
        List<Geolocation> selectthemall = databaseHelper2.SelectAll();

        StringBuilder sb = new StringBuilder();
        int size = selectthemall.size();
        boolean appendSeparator = false;

        for(int y=0; y < size; y++) {
            if (appendSeparator)
                // sb.append(' ');
                appendSeparator = true;
            Object obj = new Object();
            obj = selectthemall.get(y);

            //μπαινω στα αντικειμενα και παιρνω τις τιμες τους
            sb.append("id="+((Geolocation) obj).getid() + " - ");
            sb.append("user="+((Geolocation) obj).getUserid()+ " - ");
            sb.append("Lon="+((Geolocation) obj).getLongitude()+ " - ");
            sb.append("Lat="+((Geolocation) obj).getLatitude()+ " - ");
            sb.append("TS="+((Geolocation) obj).getdt()+ " \n");
        }

        //προσθετω τα αποτελεσματα σε String
        String Selectview = sb.toString();

        Show.setText(Selectview);

    }
}
