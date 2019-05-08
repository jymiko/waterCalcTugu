package com.heruoz.galiansumur.ud.uigaliansumur;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class KalkulatorActivity extends AppCompatActivity {

    Calendar calender_catet;
    DatePickerDialog.OnDateSetListener date_catet;

    EditText ozone,air,bakteri,tgl_catat,jam_catat;
    TextView hasil;
    Button hitunghasil,savetodb;

    View rootView;

    DatabaseHelper dbHelper;
    SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        rootView = (View)findViewById(R.id.rootView);
        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();

        calender_catet = Calendar.getInstance();
        date_catet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calender_catet.set(Calendar.YEAR,year);
                calender_catet.set(Calendar.MONTH,month);
                calender_catet.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        tgl_catat = (EditText)findViewById(R.id.tgl_catatdata);
        tgl_catat.setTextIsSelectable(true);
        tgl_catat.setFocusable(false);
        tgl_catat.setKeyListener(null);
        tgl_catat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(KalkulatorActivity.this,
                        date_catet,calender_catet.get(Calendar.YEAR),calender_catet.get(Calendar.MONTH),
                        calender_catet.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jam_catat = (EditText)findViewById(R.id.wkt_catat);
        jam_catat.setTextIsSelectable(true);
        jam_catat.setFocusable(false);
        jam_catat.setKeyListener(null);
        jam_catat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minutes = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(KalkulatorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String AM_PM ;
                        if(hourOfDay < 12) {
                            AM_PM = "am";
                        } else {
                            AM_PM = "pm";
                        }
                        jam_catat.setText(hourOfDay + ":" + minute + AM_PM);
                    }
                },hour,minutes,true);
            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
            }
        });

        ozone = (EditText) findViewById(R.id.ozon);
        air = (EditText) findViewById(R.id.literan);
        bakteri = (EditText) findViewById(R.id.bakteri);
        hasil = (TextView)findViewById(R.id.hasildata);

        hitunghasil = (Button) findViewById(R.id.btnhitung);
        hitunghasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungtotal();
            }
        });
        savetodb = (Button)findViewById(R.id.btnsavetodatabase);
        savetodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungtotal();
                savetoDatabase();
            }
        });
    }

    private void hitungtotal(){
        if (ozone.getText() != null || air.getText() != null || bakteri.getText() != null)
            hasil.setText(String.valueOf((Double.parseDouble(ozone.getText().toString()) / 100) * Double.parseDouble(air.getText().toString()) * Double.parseDouble(bakteri.getText().toString())));
    }

    private void savetoDatabase(){
        String catetan = tgl_catat.getText().toString();
        String waktu = jam_catat.getText().toString();
        String data_ozon = ozone.getText().toString();
        String data_air = air.getText().toString();
        String data_bakteri = bakteri.getText().toString();
        String data_hasil = hasil.getText().toString();

        if(!TextUtils.isEmpty(data_ozon)
                && !TextUtils.isEmpty(catetan)
                && !TextUtils.isEmpty(waktu)
                && !TextUtils.isEmpty(data_air)
                && !TextUtils.isEmpty(data_bakteri)
                && !TextUtils.isEmpty(data_hasil)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_TGL,catetan);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_JAM,waktu);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_AIR,data_air);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_OZONE,data_ozon);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_BAKTERI,data_bakteri);
            contentValues.put(DatabaseContact.TransaksiEntry.COLOUMN_HASIL,data_hasil);

           long result = mDb.insert(
                   DatabaseContact.TransaksiEntry.TABLE_NAME,
                   null,
                   contentValues
           );

            if(result > 0){
                Snackbar snackbar = Snackbar.make(
                        rootView,
                        "Order Berhasil",
                        Snackbar.LENGTH_LONG
                );
                snackbar.show();
                finish();
            }else {
                Snackbar snackbar = Snackbar.make(
                        rootView,
                        "GAGAL",
                        Snackbar.LENGTH_LONG
                );
                snackbar.show();
            }
        } else {
            Snackbar snackbar = Snackbar.make(
                    rootView,
                    "Silahkan isi form terlebih dahulu",
                    Snackbar.LENGTH_LONG
            );
            snackbar.show();
        }


    }

    private void updateLabel(){
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tgl_catat.setText(sdf.format(calender_catet.getTime()));
    }

}

