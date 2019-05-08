package com.heruoz.galiansumur.ud.uigaliansumur;


import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;


public class InputDataViewHolder extends RecyclerView.ViewHolder {
    public TextView tgl_datacatet, wkt_datacatet, ozone, air, bakteri,hasil;

    public InputDataViewHolder(View view) {
        super(view);
        tgl_datacatet = (TextView) itemView.findViewById(R.id.tanggaldata);
        wkt_datacatet = (TextView)itemView.findViewById(R.id.waktudata);
        ozone = (TextView) itemView.findViewById(R.id.ozone);
        air = (TextView) itemView.findViewById(R.id.air);
        bakteri = (TextView) itemView.findViewById(R.id.bakteri);
        hasil = (TextView)itemView.findViewById(R.id.hasil);
    }

}

