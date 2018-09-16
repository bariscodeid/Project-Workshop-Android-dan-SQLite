package dev.id.bariscode.android_sqlite_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dev.id.bariscode.android_sqlite_1.R;
import dev.id.bariscode.android_sqlite_1.model.Mahasiswa;

public class AdapterDataMahasiswa extends BaseAdapter {

    private Activity initActivity;
    private LayoutInflater inflaterClass;
    private List<Mahasiswa> modelItemMahasiswa;

    public AdapterDataMahasiswa(Activity initActivity, List<Mahasiswa> modelItemMahasiswa) {
        this.initActivity = initActivity;
        this.modelItemMahasiswa = modelItemMahasiswa;
    }

    @Override
    public int getCount() {
        return modelItemMahasiswa.size();
    }

    @Override
    public Object getItem(int location) {
        return modelItemMahasiswa.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflaterClass == null) inflaterClass = (LayoutInflater)
                initActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflaterClass.inflate(R.layout.item_view, null);

        TextView id_mahasiswa = (TextView)convertView.findViewById(R.id.tvId);
        TextView nis_mahasiswa = (TextView)convertView.findViewById(R.id.tvNIS);
        TextView nama_mahasiswa = (TextView)convertView.findViewById(R.id.tvNamaLengkap);
        TextView alamat_mahasiswa = (TextView)convertView.findViewById(R.id.tvAlamat);

        Mahasiswa modelItemDataMahasiswa = modelItemMahasiswa.get(position);

        id_mahasiswa.setText(modelItemDataMahasiswa.getId());
        nis_mahasiswa.setText(modelItemDataMahasiswa.getNim());
        nama_mahasiswa.setText(modelItemDataMahasiswa.getNama());
        alamat_mahasiswa.setText(modelItemDataMahasiswa.getAlamat());

        return convertView;
    }
}
