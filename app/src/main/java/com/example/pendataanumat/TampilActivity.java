package com.example.pendataanumat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TampilActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;

    DatabaseHandler db;


    public static boolean showAddToast = false;
    public static boolean showDeleteToast = false;
    public static PendudukModel selectedPenduduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);
        ButterKnife.bind(this);

        int MY_READ_EXTERNAL_REQUEST = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(TampilActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TampilActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_READ_EXTERNAL_REQUEST);
            }
        }

        if(showAddToast)
        {
            Toast.makeText(TampilActivity.this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            showAddToast = false;
        }
        if(showDeleteToast)
        {




            Toast.makeText(TampilActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
            showDeleteToast = false;
        }
        db = new DatabaseHandler(this);
        db.exportDB();

        List<PendudukModel> lp = db.getAllPenduduk();
        String[] namaString = new String[lp.size()];
        if(!lp.isEmpty())
        {
            for (int i=0; i<lp.size(); i++)
            {
                namaString[i]= lp.get(i).getNama();
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, namaString);
            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPenduduk = lp.get(i);
                Intent intent = new Intent(TampilActivity.this, DetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_tambah:
                Intent intent = new Intent(TampilActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:  return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
