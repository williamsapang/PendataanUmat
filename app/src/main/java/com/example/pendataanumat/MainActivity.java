package com.example.pendataanumat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.input_nama)
    MaterialEditText inputNama;
    @BindView(R.id.input_stasi)
    MaterialEditText inputStasi;
    @BindView(R.id.input_desa)
    MaterialEditText inputDesa;
    @BindView(R.id.input_kondisiEkonomi)
    MaterialEditText inputKondisiEkonomi;
    @BindView(R.id.input_jenisKelamin)
    MaterialEditText inputJenisKelamin;
    @BindView(R.id.input_tempatLahir)
    MaterialEditText inputTempatLahir;
    @BindView(R.id.input_tglLahir)
    MaterialEditText inputTglLahir;
    @BindView(R.id.input_pendidikan)
    MaterialEditText inputPendidikan;
    @BindView(R.id.input_jenisPekerjaan)
    MaterialEditText inputJenisPekerjaan;
    @BindView(R.id.input_agama)
    MaterialEditText inputAgama;
    @BindView(R.id.input_tempatBaptis)
    MaterialEditText inputTempatBaptis;
    @BindView(R.id.input_pastorBaptis)
    MaterialEditText inputPastorBaptis;
    @BindView(R.id.input_waliBaptis1)
    MaterialEditText inputWaliBaptis1;
    @BindView(R.id.input_waliBaptis2)
    MaterialEditText inputWaliBaptis2;
    @BindView(R.id.input_noLB)
    MaterialEditText inputNoLB;
    @BindView(R.id.input_statusPernikahan)
    MaterialEditText inputStatusPernikahan;
    @BindView(R.id.input_tempatMenikah)
    MaterialEditText inputTempatMenikah;
    @BindView(R.id.input_tglMenikah)
    MaterialEditText inputTglMenikah;
    @BindView(R.id.input_pastorNikah)
    MaterialEditText inputPastorNikah;
    @BindView(R.id.input_saksiNikah1)
    MaterialEditText inputSaksiNikah1;
    @BindView(R.id.input_saksiNikah2)
    MaterialEditText inputSaksiNikah2;
    @BindView(R.id.input_noLM)
    MaterialEditText inputNoLM;
    @BindView(R.id.input_jenisPerkawinan)
    MaterialEditText inputJenisPerkawinan;
    @BindView(R.id.input_hubDalamKeluarga)
    MaterialEditText inputHubDalamKeluarga;
    @BindView(R.id.input_domisili)
    MaterialEditText inputDomisili;
    @BindView(R.id.input_tempatKrisma)
    MaterialEditText inputTempatKrisma;
    @BindView(R.id.input_tglKrisma)
    MaterialEditText inputTglKrisma;
    @BindView(R.id.input_nomorKrisma)
    MaterialEditText inputNomorKrisma;
    @BindView(R.id.input_namaAyah)
    MaterialEditText inputNamaAyah;
    @BindView(R.id.input_namaIbu)
    MaterialEditText inputNamaIbu;
    @BindView(R.id.input_keterangan)
    MaterialEditText inputKeterangan;
    @BindView(R.id.simpan_button)
    Button simpanButton;

    DatabaseHandler db;
    @BindView(R.id.input_tanggalBaptis)
    MaterialEditText inputTanggalBaptis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = new DatabaseHandler(this);
    }


    @OnClick(R.id.simpan_button)
    public void onViewClicked() {
        PendudukModel p = new PendudukModel();
        p.setNama(inputNama.getText().toString());
        p.setStasi(inputStasi.getText().toString());
        p.setDesa(inputDesa.getText().toString());
        p.setKondisiEkonomi(inputKondisiEkonomi.getText().toString());
        p.setJenisKelamin(inputJenisKelamin.getText().toString());
        p.setTempatLahir(inputTempatLahir.getText().toString());
        p.setTglLahir(inputTglLahir.getText().toString());
        p.setPendidikan(inputPendidikan.getText().toString());
        p.setJenisPekerjaan(inputJenisPekerjaan.getText().toString());
        p.setAgama(inputAgama.getText().toString());
        p.setTempatBaptis(inputTempatBaptis.getText().toString());
        p.setTanggalBaptis(inputTanggalBaptis.getText().toString());
        p.setPastorBaptis(inputPastorBaptis.getText().toString());
        p.setWaliBaptis1(inputWaliBaptis1.getText().toString());
        p.setWaliBaptis2(inputWaliBaptis2.getText().toString());
        p.setNoLB(inputNoLB.getText().toString());
        p.setStatusPernikahan(inputStatusPernikahan.getText().toString());
        p.setTempatMenikah(inputTempatMenikah.getText().toString());
        p.setTglMenikah(inputTglMenikah.getText().toString());
        p.setPastorNikah(inputPastorNikah.getText().toString());
        p.setSaksiNikah1(inputSaksiNikah1.getText().toString());
        p.setSaksiNikah2(inputSaksiNikah2.getText().toString());
        p.setNoLM(inputNoLM.getText().toString());
        p.setJenisPerkawinan(inputJenisPerkawinan.getText().toString());
        p.setHubDalamKeluarga(inputHubDalamKeluarga.getText().toString());
        p.setDomisili(inputDomisili.getText().toString());
        p.setTempatKrisma(inputTempatKrisma.getText().toString());
        p.setTglKrisma(inputTglKrisma.getText().toString());
        p.setNomorKrisma(inputNomorKrisma.getText().toString());
        p.setNamaAyah(inputNamaAyah.getText().toString());
        p.setNamaIbu(inputNamaIbu.getText().toString());
        p.setKeterangan(inputKeterangan.getText().toString());

        db.addRecord(p);

        TampilActivity.showAddToast = true;
        Intent intent = new Intent(MainActivity.this, TampilActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, TampilActivity.class);
        startActivity(intent);
        finish();
    }
}