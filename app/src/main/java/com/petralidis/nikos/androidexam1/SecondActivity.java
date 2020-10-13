package com.petralidis.nikos.androidexam1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //-------Αρχικοποιηση αντικειμενων γραφικου περιβαλλοντος------------------

        //edittext
        final EditText searchuser = findViewById(R.id.editText);
        //spinner
        final Spinner spinner = findViewById(R.id.spinner);
        //buttons
        Button buttonsearch = findViewById(R.id.buttonSearch);
        Button backbutton2 = findViewById(R.id.back2);



        //------Δημιουργια του Spinner απο τα timestamps της βασης κατα την εναρξη του Activity----------

        //παιρνω ολα τα timestamps της βασης μεσω της μεθοδου TIMESTAMPS
        final DatabaseHelper databaseHelper = new DatabaseHelper(SecondActivity.this);
        List<String> loclist = databaseHelper.Timestamps();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loclist);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);



        //----------------------Listener για το κουμπι Search--------------------------------------------

        buttonsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String UserInput = searchuser.getText().toString();
                String TimestampInput = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                //χρησιμοποιω την SELECTGEOLOCATIONS με ορισματα το input του χρηστη για να κανω την επιλογη
                DatabaseHelper databaseHelper1 = new DatabaseHelper(SecondActivity.this);
                List<Geolocation> selectlist = databaseHelper1.SelectGeolocations(UserInput,TimestampInput);

                //----χρησιμοποιω την Stringbuilder για να παρω τα γνωρισματα των αντικειμενων και να τα εκτυπωσω σε String----
                StringBuilder sb = new StringBuilder();
                int size = selectlist.size();
                boolean appendSeparator = false;

                for(int y=0; y < size; y++) {
                    if (appendSeparator)
                       // sb.append(' ');
                    appendSeparator = true;
                    Object obj = new Object();
                    obj = selectlist.get(y);

                    //μπαινω στα αντικειμενα και παιρνω τις τιμες τους
                    sb.append("id="+((Geolocation) obj).getid() + " - ");
                    sb.append("user="+((Geolocation) obj).getUserid()+ " - ");
                    sb.append("Lon="+((Geolocation) obj).getLongitude()+ " - ");
                    sb.append("Lat="+((Geolocation) obj).getLatitude()+ " - ");
                    sb.append("TS="+((Geolocation) obj).getdt()+ " \n");
                }

                    //προσθετω τα αποτελεσματα σε String
                    String Selectview = sb.toString();

                    //Στελνω τα αποτελεσματα στο τριτο Activity για να τα εμφανισω
                    Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                    i.putExtra("RESULTS", Selectview);
                    startActivity(i);
            }
        });

        //-------------------Listener για το κουμπι Back---------------------
        backbutton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, FirstActivity.class));
            }
        });
    }
}
