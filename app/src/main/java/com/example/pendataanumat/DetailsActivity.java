package com.example.pendataanumat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tampil_nama)
    TextView tampilNama;
    @BindView(R.id.tampil_stasi)
    TextView tampilStasi;
    @BindView(R.id.tampil_desa)
    TextView tampilDesa;
    @BindView(R.id.tampil_kondisi_ekonomi)
    TextView tampilKondisiEkonomi;
    @BindView(R.id.tampil_jenis_kelamin)
    TextView tampilJenisKelamin;
    @BindView(R.id.tampil_tempat_lahir)
    TextView tampilTempatLahir;
    @BindView(R.id.tampil_tgl_lahir)
    TextView tampilTglLahir;
    @BindView(R.id.tampil_pendidikan)
    TextView tampilPendidikan;
    @BindView(R.id.tampil_jenis_pekerjaan)
    TextView tampilJenisPekerjaan;
    @BindView(R.id.tampil_agama)
    TextView tampilAgama;
    @BindView(R.id.tampil_tempat_baptis)
    TextView tampilTempatBaptis;
    @BindView(R.id.tampil_pastor_baptis)
    TextView tampilPastorBaptis;
    @BindView(R.id.tampil_wali_baptis1)
    TextView tampilWaliBaptis1;
    @BindView(R.id.tampil_wali_baptis2)
    TextView tampilWaliBaptis2;
    @BindView(R.id.tampil_no_LB)
    TextView tampilNoLB;
    @BindView(R.id.tampil_status_pernikahan)
    TextView tampilStatusPernikahan;
    @BindView(R.id.tampil_tempat_menikah)
    TextView tampilTempatMenikah;
    @BindView(R.id.tampil_tgl_menikah)
    TextView tampilTglMenikah;
    @BindView(R.id.tampil_pastor_nikah)
    TextView tampilPastorNikah;
    @BindView(R.id.tampil_saksi_nikah1)
    TextView tampilSaksiNikah1;
    @BindView(R.id.tampil_saksi_nikah2)
    TextView tampilSaksiNikah2;
    @BindView(R.id.tampil_no_LM)
    TextView tampilNoLM;
    @BindView(R.id.tampil_jenis_perkawinan)
    TextView tampilJenisPerkawinan;
    @BindView(R.id.tampil_hub_dalam_keluarga)
    TextView tampilHubDalamKeluarga;
    @BindView(R.id.tampil_domisili)
    TextView tampilDomisili;
    @BindView(R.id.tampil_tempat_krisma)
    TextView tampilTempatKrisma;
    @BindView(R.id.tampil_tgl_krisma)
    TextView tampilTglKrisma;
    @BindView(R.id.tampil_nomor_krisma)
    TextView tampilNomorKrisma;
    @BindView(R.id.tampil_nama_ayah)
    TextView tampilNamaAyah;
    @BindView(R.id.tampil_nama_ibu)
    TextView tampilNamaIbu;
    @BindView(R.id.tampil_keterangan)
    TextView tampilKeterangan;

    PendudukModel p;
    @BindView(R.id.tampil_tanggal_baptis)
    TextView tampilTanggalBaptis;

    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        p = TampilActivity.selectedPenduduk;
        db = new DatabaseHandler(this);
        tampilNama.setText("Nama : " + p.getNama());
        tampilStasi.setText("Stasi : " + p.getStasi());
        tampilDesa.setText("Desa : " + p.getDesa());
        tampilKondisiEkonomi.setText("KondisiEkonomi : " + p.getKondisiEkonomi());
        tampilJenisKelamin.setText("JenisKelamin : " + p.getJenisKelamin());
        tampilTempatLahir.setText("TempatLahir : " + p.getTempatLahir());
        tampilTglLahir.setText("TanggalLahir : " + p.getTglLahir());
        tampilPendidikan.setText("Pendidikan : " + p.getPendidikan());
        tampilJenisPekerjaan.setText("JenisPekerjaan : " + p.getJenisPekerjaan());
        tampilAgama.setText("Agama : " + p.getAgama());
        tampilTempatBaptis.setText("TempatBaptis : " + p.getTempatBaptis());
        tampilTanggalBaptis.setText("TanggalBaptis : " + p.getTanggalBaptis());
        tampilPastorBaptis.setText("PastorBaptis : " + p.getPastorBaptis());
        tampilWaliBaptis1.setText("WaliBaptis1 : " + p.getWaliBaptis1());
        tampilWaliBaptis2.setText("WaliBaptis2 : " + p.getWaliBaptis2());
        tampilNoLB.setText("NomorLB : " + p.getNoLB());
        tampilStatusPernikahan.setText("StatusPernikahan : " + p.getStatusPernikahan());
        tampilTempatMenikah.setText("TempatMenikah : " + p.getTempatMenikah());
        tampilTglMenikah.setText("TanggalMenikah : " + p.getTglMenikah());
        tampilPastorNikah.setText("PastorNikah : " + p.getPastorNikah());
        tampilSaksiNikah1.setText("SaksiNikah1 : " + p.getSaksiNikah1());
        tampilSaksiNikah2.setText("SaksiNikah2 : " + p.getSaksiNikah2());
        tampilNoLM.setText("NomorLM : " + p.getNoLM());
        tampilJenisPerkawinan.setText("JenisPerkawinan : " + p.getJenisPerkawinan());
        tampilHubDalamKeluarga.setText("HubunganDlmKeluarga : " + p.getHubDalamKeluarga());
        tampilDomisili.setText("Domisili : " + p.getDomisili());
        tampilTempatKrisma.setText("TempatKrisma : " + p.getTempatKrisma());
        tampilTglKrisma.setText("TanggalKrisma : " + p.getTglKrisma());
        tampilNomorKrisma.setText("NomorKrisma : " + p.getNomorKrisma());
        tampilNamaAyah.setText("NamaAyah : " + p.getNamaAyah());
        tampilNamaIbu.setText("NamaIbu : " + p.getNamaIbu());
        tampilKeterangan.setText("Keterangan : " + p.getKeterangan());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.penduduk_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.hapus_penduduk:
                db.deletePenduduk(p.getPendudukID());
                TampilActivity.showDeleteToast = true;
                Intent intent = new Intent(DetailsActivity.this, TampilActivity.class);
                startActivity(intent);
                finish();
                break;
            default:  return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailsActivity.this, TampilActivity.class);
        startActivity(intent);
        finish();
    }
}
