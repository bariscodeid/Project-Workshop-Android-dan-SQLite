package dev.id.bariscode.android_sqlite_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.id.bariscode.android_sqlite_1.helper.DatabaseMahasiswaConfig;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_nim)
    EditText etNim;
    @BindView(R.id.et_nama)
    EditText etNama;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    DatabaseMahasiswaConfig SQLiteProduction = new DatabaseMahasiswaConfig(this);
    String id, nim, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        nim = getIntent().getStringExtra(MainActivity.TAG_NIM);
        nama = getIntent().getStringExtra(MainActivity.TAG_NAMA);
        alamat = getIntent().getStringExtra(MainActivity.TAG_ALAMAT);

        if (id == null || id == "") {
            setTitle("Tambah Data");
        } else {
            setTitle("Hapus Data");
            etId.setText(id);
            etNim.setText(nim);
            etNama.setText(nama);
            etAlamat.setText(alamat);
        }

        if (etNim.getText().toString().equals("") && etNama.getText().toString().equals("") && etAlamat.getText().toString().equals("")) {
            btnSubmit.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.GONE);
        } else {
            btnUpdate.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.btn_update, R.id.btn_submit, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                AksiUpdate();
                break;
            case R.id.btn_submit:
                AksiSimpanData();
                break;
            case R.id.btn_cancel:
                AksiCancelAddData();
                break;
        }
    }

    private void AksiUpdate() {
        if (String.valueOf(etNim.getText()).equals(null) || String.valueOf(etNim.getText()).equals("") ||
            String.valueOf(etNama.getText()).equals(null) || String.valueOf(etNama.getText()).equals("") ||
            String.valueOf(etAlamat.getText()).equals(null) || String.valueOf(etAlamat.getText()).equals("")) {
            Toast.makeText(this, "Inputan masih kosong, isi dulu!", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteProduction.update(
                    Integer.parseInt(etId.getText().toString().trim()),
                    etNim.getText().toString().trim(),
                    etNama.getText().toString().trim(),
                    etAlamat.getText().toString().trim());
            BlankEditText();
            finish();
        }
    }

    private void AksiSimpanData() {
        if (String.valueOf(etNim.getText()).equals(null) || String.valueOf(etNim.getText()).equals("") ||
            String.valueOf(etNama.getText()).equals(null) || String.valueOf(etNama.getText()).equals("") ||
            String.valueOf(etAlamat.getText()).equals(null) || String.valueOf(etAlamat.getText()).equals("")) {
            Toast.makeText(this, "Inputan masih kosong, isi dulu!", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteProduction.insert(etNim.getText().toString().trim(), etNama.getText().toString().trim(), etAlamat.getText().toString().trim());
            System.out.println("NIM " + etNim.getText().toString());
            System.out.println("Nama " + etNama.getText().toString());
            System.out.println("Alamat " + etAlamat.getText().toString());
            BlankEditText();
            finish();
        }
    }

    private void AksiCancelAddData() {
        BlankEditText();
        finish();
    }

    public void BlankEditText() {
        etId.setText(null);
        etNim.setText(null);
        etNama.setText(null);
        etAlamat.setText(null);
        etNama.requestFocus();
    }
}
