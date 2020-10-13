package com.petralidis.nikos.androidexam1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.valueOf;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //-----------δηλωση μεταβλητων γραφικων στοιχειων-------------------

        //-----------------------Buttons------------------------------------
        Button buttonS = findViewById(R.id.buttonSubmit);
        Button buttonN = findViewById(R.id.buttonNext);
        Button buttonV = findViewById(R.id.ViewButton);

        //-------------------------EditTexts--------------------------------
        final EditText UserID = findViewById(R.id.editTextUserid);
        final EditText Longitude = findViewById(R.id.editTextLongitude);
        final EditText Latitude = findViewById(R.id.editTextLatitude);
        final EditText dt = findViewById(R.id.editTextTimestamp);

        //-------------------------DatePicker-------------------------------
        final DatePicker DP = findViewById(R.id.datePicker);


        //--------------------Listener για το κουμπι Submit-----------------
        buttonS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                    //Δημιουργω αντικειμενο και το γεμιζω με τις τιμες των EditTexts
                    Geolocation geolocation = new Geolocation();
                    geolocation.setUserid(UserID.getText().toString());
                    geolocation.setLongitude(Float.valueOf(Longitude.getText().toString()));
                    geolocation.setLatitude(Float.valueOf(Latitude.getText().toString()));
                    geolocation.setdt(dt.getText().toString());




                    //σωστη μορφη ωρας
                String checkdt = geolocation.getdt().toString();
                if (checkdt.toString().matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))
                {

                    //δημιουργω την ημερομηνια και ωρα και την προσθετω στο dt
                    StringBuilder strb = new StringBuilder();
                    strb.append(String.valueOf(DP.getDayOfMonth()));
                    strb.append("/");

                    //κατι συμβαινει και μου βγαζει τους μηνες -1 οποτε το διορθωνω προσθετοντας +1 στην τιμη του μηνα
                    int year = valueOf(DP.getMonth()+1);
                    strb.append(year);
                    strb.append("/");
                    strb.append(String.valueOf(DP.getYear()));
                    strb.append("  (");
                    strb.append(dt.getText().toString());
                    strb.append(")");
                    String timestamp = strb.toString();

                    geolocation.setdt(timestamp);

                    //στελνω το αντικειμενο στη βαση δεδομενων μεσω της addgeolocation
                    Context context = getApplicationContext();
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    databaseHelper.addGeolocation(geolocation);

                    //μηνυμα ορθης καταχωρησης (TOAST)
                    CharSequence text = "Succesful Submit!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    //λαθος μορφη ωρας
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong Time Format, try again";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        //-------------------Listener για το κουμπι Next---------------------
        buttonN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
    });

        //-------------------Listener για το κουμπι View---------------------
        buttonV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, ExamActivity.class));
            }
        });
    }
}
