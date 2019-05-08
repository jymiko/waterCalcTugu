package com.heruoz.galiansumur.ud.uigaliansumur;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InputDataAdapter extends RecyclerView.Adapter<InputDataViewHolder>{

    Context mContext;
    Cursor mCursor;

    public InputDataAdapter(Context context){
        this.mContext=context;
    }

    public void swapCursor(Cursor cursor){
        this.mCursor=cursor;
    }
    @Override
    public InputDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_view,parent,false);
        return new InputDataViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final InputDataViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        int tglColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_TGL);
        int wktColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_JAM);
        int airColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_AIR);
        int ozoneColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_OZONE);
        int bakteriColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_BAKTERI);
        int hasilColoumnIndex = mCursor.getColumnIndex(DatabaseContact.TransaksiEntry.COLOUMN_HASIL);

        String tgl = mCursor.getString(tglColoumnIndex);
        String wkt = mCursor.getString(wktColoumnIndex);
        String air = mCursor.getString(airColoumnIndex);
        String ozone = mCursor.getString(ozoneColoumnIndex);
        String bakteri = mCursor.getString(bakteriColoumnIndex);
        String hasil = mCursor.getString(hasilColoumnIndex);

        holder.tgl_datacatet.setText(tgl);
        holder.wkt_datacatet.setText(wkt);
        holder.air.setText(air);
        holder.ozone.setText(ozone);
        holder.bakteri.setText(bakteri);
        holder.hasil.setText(hasil);
    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
            return 0;
        }
        return mCursor.getCount();
    }

}
