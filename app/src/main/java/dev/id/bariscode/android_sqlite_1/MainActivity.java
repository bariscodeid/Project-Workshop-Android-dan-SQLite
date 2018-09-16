package dev.id.bariscode.android_sqlite_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.id.bariscode.android_sqlite_1.adapter.AdapterDataMahasiswa;
import dev.id.bariscode.android_sqlite_1.helper.DatabaseMahasiswaConfig;
import dev.id.bariscode.android_sqlite_1.model.Mahasiswa;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lstData)
    ListView lstData;
    @BindView(R.id.fabAddData)
    FloatingActionButton fabAddData;

    AlertDialog.Builder dialogBuilder;
    List<Mahasiswa> itemDataMahasiswa = new ArrayList<Mahasiswa>();
    AdapterDataMahasiswa classAdapterMahasiswa;
    DatabaseMahasiswaConfig SQLiteProduction = new DatabaseMahasiswaConfig(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NIM = "nim";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SQLiteProduction = new DatabaseMahasiswaConfig(getApplicationContext());
        classAdapterMahasiswa = new AdapterDataMahasiswa(MainActivity.this, itemDataMahasiswa);
        lstData.setAdapter(classAdapterMahasiswa);

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posisi, long id) {
                final String idx = itemDataMahasiswa.get(posisi).getId();
                final String nimx = itemDataMahasiswa.get(posisi).getNim();
                final String namax = itemDataMahasiswa.get(posisi).getNama();
                final String alamatx = itemDataMahasiswa.get(posisi).getAlamat();

                final CharSequence[] dialogItem = {
                        "Edit Data Item",
                        "Delete Data Item"
                };

                dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Pilih Tindakan");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whice) {
                        switch (whice) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NIM, nimx);
                                intent.putExtra(TAG_NAMA, namax);
                                intent.putExtra(TAG_ALAMAT, alamatx);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLiteProduction.delete(Integer.parseInt(idx));
                                itemDataMahasiswa.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
            }
        });

        getAllData();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLiteProduction.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nim = row.get(i).get(TAG_NIM);
            String namalengkap = row.get(i).get(TAG_NAMA);
            String alamatmahasiswa = row.get(i).get(TAG_ALAMAT);

            Mahasiswa datas = new Mahasiswa();

            datas.setId(id);
            datas.setNim(nim);
            datas.setNama(namalengkap);
            datas.setAlamat(alamatmahasiswa);

            System.out.print(datas);
            itemDataMahasiswa.add(datas);
        }
        classAdapterMahasiswa.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemDataMahasiswa.clear();
        getAllData();
    }

    @OnClick(R.id.fabAddData)
    public void onViewClicked() {
        Intent gotoAddActivity = new Intent(MainActivity.this, AddActivity.class);
        startActivity(gotoAddActivity);
    }
}
