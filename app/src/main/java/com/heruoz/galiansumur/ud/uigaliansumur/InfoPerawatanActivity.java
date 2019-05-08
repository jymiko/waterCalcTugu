package com.heruoz.galiansumur.ud.uigaliansumur;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class InfoPerawatanActivity extends AppCompatActivity {

    RecyclerView info_status;
    InputDataAdapter adapter;
    SQLiteDatabase mDb;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_perawatan);

        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getReadableDatabase();

        info_status =(RecyclerView)findViewById(R.id.showData);
        adapter = new InputDataAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        info_status.setLayoutManager(linearLayoutManager);
        info_status.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        swapData();
    }

    private void swapData(){
        adapter.swapCursor(getAllHistory());
        adapter.notifyDataSetChanged();
    }

    private Cursor getAllHistory(){
        return mDb.query(
            DatabaseContact.TransaksiEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
