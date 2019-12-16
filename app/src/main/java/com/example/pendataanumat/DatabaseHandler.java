package com.example.pendataanumat;

import android.app.Service;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**

 * Created by Dimas Maulana on 2/27/17.

 * Email : araymaulana66@gmail.com

 */



public class DatabaseHandler extends SQLiteOpenHelper {


    Context con;

    // static variable

    private static final int DATABASE_VERSION = 3;



    // Database name

    private static final String DATABASE_NAME = "DatabaseUmat";



    // table name

    private static final String TABLE_TALL = "TabelUmat";



    // column tables

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "user";

    private static final String KEY_TALL = "tall";



    public DatabaseHandler(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        con = context;
    }



    //Create table

    @Override

    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL + "("

                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"  //0
                + "Nama" + " TEXT," //1
                + "Stasi" + " TEXT," //2
                + "Desa" + " TEXT,"
                + "KondisiEkonomi" + " TEXT,"
                + "JenisKelamin" + " TEXT,"
                + "TempatLahir" + " TEXT,"
                + "TanggalLahir" + " TEXT,"
                + "Pendidikan" + " TEXT,"
                + "JenisPekerjaan" + " TEXT,"
                + "Agama" + " TEXT,"
                + "TempatBaptis" + " TEXT,"
                + "TanggalBaptis" + " TEXT,"
                + "PastorBaptis" + " TEXT,"
                + "WaliBaptis1" + " TEXT,"
                + "WaliBaptis2" + " TEXT,"
                + "NomorLB" + " TEXT,"
                + "StatusPernikahan" + " TEXT,"
                + "TempatMenikah" + " TEXT,"
                + "TanggalMenikah" + " TEXT,"
                + "PastorNikah" + " TEXT,"
                + "SaksiNikah1" + " TEXT,"
                + "SaksiNikah2" + " TEXT,"
                + "NomorLM" + " TEXT,"
                + "JenisPerkawinan" + " TEXT,"
                + "HubunganDlmKeluarga" + " TEXT,"
                + "Domisili" + " TEXT,"
                + "TempatKrisma" + " TEXT,"
                + "TanggalKrisma" + " TEXT,"
                + "NomorKrisma" + " TEXT,"
                + "NamaAyah" + " TEXT,"
                + "NamaIbu" + " TEXT,"
                + "Keterangan" + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    public void addRecord(PendudukModel pendudukModel){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Nama", pendudukModel.getNama());
        values.put("Stasi", pendudukModel.getStasi());
        values.put("Desa", pendudukModel.getDesa());
        values.put("KondisiEkonomi", pendudukModel.getKondisiEkonomi());
        values.put("JenisKelamin", pendudukModel.getJenisKelamin());
        values.put("TempatLahir", pendudukModel.getTempatLahir());
        values.put("TanggalLahir", pendudukModel.getTglLahir());
        values.put("Pendidikan", pendudukModel.getPendidikan());
        values.put("JenisPekerjaan", pendudukModel.getJenisPekerjaan());
        values.put("Agama", pendudukModel.getAgama());
        values.put("TempatBaptis", pendudukModel.getTempatBaptis());
        values.put("TanggalBaptis", pendudukModel.getTanggalBaptis());
        values.put("PastorBaptis", pendudukModel.getPastorBaptis());
        values.put("WaliBaptis1", pendudukModel.getWaliBaptis1());
        values.put("WaliBaptis2", pendudukModel.getWaliBaptis2());
        values.put("NomorLB", pendudukModel.getNoLB());
        values.put("StatusPernikahan", pendudukModel.getStatusPernikahan());
        values.put("TempatMenikah", pendudukModel.getTempatMenikah());
        values.put("TanggalMenikah", pendudukModel.getTglMenikah());
        values.put("PastorNikah", pendudukModel.getPastorNikah());
        values.put("SaksiNikah1", pendudukModel.getSaksiNikah1());
        values.put("SaksiNikah2", pendudukModel.getSaksiNikah2());
        values.put("NomorLM", pendudukModel.getNoLM());
        values.put("JenisPerkawinan", pendudukModel.getJenisPerkawinan());
        values.put("HubunganDlmKeluarga", pendudukModel.getHubDalamKeluarga());
        values.put("Domisili", pendudukModel.getDomisili());
        values.put("TempatKrisma", pendudukModel.getTempatKrisma());
        values.put("TanggalKrisma", pendudukModel.getTglKrisma());
        values.put("NomorKrisma", pendudukModel.getNomorKrisma());
        values.put("NamaAyah", pendudukModel.getNamaAyah());
        values.put("NamaIbu", pendudukModel.getNamaIbu());
        values.put("Keterangan", pendudukModel.getKeterangan());
        db.insert(TABLE_TALL, null, values);
        db.close();
    }

    public void deletePenduduk(String id)
    {
        SQLiteDatabase db  = getWritableDatabase();
        db.delete(TABLE_TALL, "id="+id, null);
        db.close();
    }


    public void exportDB() {

// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TALL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        File sd = con.getExternalFilesDir(null);
        String csvFile = "myData.xls";

        Log.d("ABC", sd.getAbsolutePath());
        File directory = new File(sd.getAbsolutePath());
//create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
//Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("userList", 0);
// column and row
            sheet.addCell(new Label(0, 0, "Nama"));
            sheet.addCell(new Label(1, 0, "Stasi"));
            sheet.addCell(new Label(2, 0, "Desa"));
            sheet.addCell(new Label(3, 0, "KondisiEkonomi"));
            sheet.addCell(new Label(4, 0, "JenisKelamin"));
            sheet.addCell(new Label(5, 0, "TempatLahir"));
            sheet.addCell(new Label(6, 0, "TanggalLahir"));
            sheet.addCell(new Label(7, 0, "Pendidikan"));
            sheet.addCell(new Label(8, 0, "JenisPekerjaan"));
            sheet.addCell(new Label(9, 0, "Agama"));
            sheet.addCell(new Label(10, 0, "TempatBaptis"));
            sheet.addCell(new Label(11, 0, "TanggalBaptis"));
            sheet.addCell(new Label(12, 0, "PastorBaptis"));
            sheet.addCell(new Label(13, 0, "WaliBaptis1"));
            sheet.addCell(new Label(14, 0, "WaliBaptis2"));
            sheet.addCell(new Label(15, 0, "NomorLB"));
            sheet.addCell(new Label(16, 0, "StatusPernikahan"));
            sheet.addCell(new Label(17, 0, "TempatMenikah"));
            sheet.addCell(new Label(18, 0, "TanggalMenikah"));
            sheet.addCell(new Label(19, 0, "PastorNikah"));
            sheet.addCell(new Label(20, 0, "SaksiNikah1"));
            sheet.addCell(new Label(21, 0, "SaksiNikah2"));
            sheet.addCell(new Label(22, 0, "NomorLM"));
            sheet.addCell(new Label(23, 0, "JenisPerkawinan"));
            sheet.addCell(new Label(24, 0, "HubunganDlmKeluarga"));
            sheet.addCell(new Label(25, 0, "Domisili"));
            sheet.addCell(new Label(26, 0, "TempatKrisma"));
            sheet.addCell(new Label(27, 0, "TanggalKrisma"));
            sheet.addCell(new Label(28, 0, "NomorKrisma"));
            sheet.addCell(new Label(29, 0, "NamaAyah"));
            sheet.addCell(new Label(30, 0, "NamaIbu"));
            sheet.addCell(new Label(31, 0, "Keterangan"));

            if (cursor.moveToFirst()) {
                do {
                    PendudukModel p = new PendudukModel();

                    int i = cursor.getPosition() + 1;

                    sheet.addCell(new Label(0, i, cursor.getString(cursor.getColumnIndex("Nama"))));
                    sheet.addCell(new Label(1, i, cursor.getString(cursor.getColumnIndex("Stasi"))));
                    sheet.addCell(new Label(2, i, cursor.getString(cursor.getColumnIndex("Desa"))));
                    sheet.addCell(new Label(3, i, cursor.getString(cursor.getColumnIndex("KondisiEkonomi"))));
                    sheet.addCell(new Label(4, i, cursor.getString(cursor.getColumnIndex("JenisKelamin"))));
                    sheet.addCell(new Label(5, i, cursor.getString(cursor.getColumnIndex("TempatLahir"))));
                    sheet.addCell(new Label(6, i, cursor.getString(cursor.getColumnIndex("TanggalLahir"))));
                    sheet.addCell(new Label(7, i, cursor.getString(cursor.getColumnIndex("Pendidikan"))));
                    sheet.addCell(new Label(8, i, cursor.getString(cursor.getColumnIndex("JenisPekerjaan"))));
                    sheet.addCell(new Label(9, i, cursor.getString(cursor.getColumnIndex("Agama"))));
                    sheet.addCell(new Label(10, i, cursor.getString(cursor.getColumnIndex("TempatBaptis"))));
                    sheet.addCell(new Label(11, i, cursor.getString(cursor.getColumnIndex("TanggalBaptis"))));
                    sheet.addCell(new Label(12, i, cursor.getString(cursor.getColumnIndex("PastorBaptis"))));
                    sheet.addCell(new Label(13, i, cursor.getString(cursor.getColumnIndex("WaliBaptis1"))));
                    sheet.addCell(new Label(14, i, cursor.getString(cursor.getColumnIndex("WaliBaptis2"))));
                    sheet.addCell(new Label(15, i, cursor.getString(cursor.getColumnIndex("NomorLB"))));
                    sheet.addCell(new Label(16, i, cursor.getString(cursor.getColumnIndex("StatusPernikahan"))));
                    sheet.addCell(new Label(17, i, cursor.getString(cursor.getColumnIndex("TempatMenikah"))));
                    sheet.addCell(new Label(18, i, cursor.getString(cursor.getColumnIndex("TanggalMenikah"))));
                    sheet.addCell(new Label(19, i, cursor.getString(cursor.getColumnIndex("PastorNikah"))));
                    sheet.addCell(new Label(20, i, cursor.getString(cursor.getColumnIndex("SaksiNikah1"))));
                    sheet.addCell(new Label(21, i, cursor.getString(cursor.getColumnIndex("SaksiNikah2"))));
                    sheet.addCell(new Label(22, i, cursor.getString(cursor.getColumnIndex("NomorLM"))));
                    sheet.addCell(new Label(23, i, cursor.getString(cursor.getColumnIndex("JenisPerkawinan"))));
                    sheet.addCell(new Label(24, i, cursor.getString(cursor.getColumnIndex("HubunganDlmKeluarga"))));
                    sheet.addCell(new Label(25, i, cursor.getString(cursor.getColumnIndex("Domisili"))));
                    sheet.addCell(new Label(26, i, cursor.getString(cursor.getColumnIndex("TempatKrisma"))));
                    sheet.addCell(new Label(27, i, cursor.getString(cursor.getColumnIndex("TanggalKrisma"))));
                    sheet.addCell(new Label(28, i, cursor.getString(cursor.getColumnIndex("NomorKrisma"))));
                    sheet.addCell(new Label(29, i, cursor.getString(cursor.getColumnIndex("NamaAyah"))));
                    sheet.addCell(new Label(30, i, cursor.getString(cursor.getColumnIndex("NamaIbu"))));
                    sheet.addCell(new Label(31, i, cursor.getString(cursor.getColumnIndex("Keterangan"))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            workbook.write();
            workbook.close();
        }catch(Exception e){
            e.printStackTrace();
        }
// looping through all rows and adding to list


// close db connection
        db.close();

    }

    public List<PendudukModel> getAllPenduduk() {
        List<PendudukModel> pendudukList = new ArrayList<>();

// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PendudukModel p = new PendudukModel();
                p.setPendudukID(cursor.getString(cursor.getColumnIndex("id")));
                p.setNama(cursor.getString(cursor.getColumnIndex("Nama")));
                p.setStasi(cursor.getString(cursor.getColumnIndex("Stasi")));
                p.setDesa(cursor.getString(cursor.getColumnIndex("Desa")));
                p.setKondisiEkonomi(cursor.getString(cursor.getColumnIndex("KondisiEkonomi")));
                p.setJenisKelamin(cursor.getString(cursor.getColumnIndex("JenisKelamin")));
                p.setTempatLahir(cursor.getString(cursor.getColumnIndex("TempatLahir")));
                p.setTglLahir(cursor.getString(cursor.getColumnIndex("TanggalLahir")));
                p.setPendidikan(cursor.getString(cursor.getColumnIndex("Pendidikan")));
                p.setJenisPekerjaan(cursor.getString(cursor.getColumnIndex("JenisPekerjaan")));
                p.setAgama(cursor.getString(cursor.getColumnIndex("Agama")));
                p.setTempatBaptis(cursor.getString(cursor.getColumnIndex("TempatBaptis")));
                p.setTanggalBaptis(cursor.getString(cursor.getColumnIndex("TanggalBaptis")));
                p.setPastorBaptis(cursor.getString(cursor.getColumnIndex("PastorBaptis")));
                p.setWaliBaptis1(cursor.getString(cursor.getColumnIndex("WaliBaptis1")));
                p.setWaliBaptis2(cursor.getString(cursor.getColumnIndex("WaliBaptis2")));
                p.setNoLB(cursor.getString(cursor.getColumnIndex("NomorLB")));
                p.setStatusPernikahan(cursor.getString(cursor.getColumnIndex("StatusPernikahan")));
                p.setTempatMenikah(cursor.getString(cursor.getColumnIndex("TempatMenikah")));
                p.setTglMenikah(cursor.getString(cursor.getColumnIndex("TanggalMenikah")));
                p.setPastorNikah(cursor.getString(cursor.getColumnIndex("PastorNikah")));
                p.setSaksiNikah1(cursor.getString(cursor.getColumnIndex("SaksiNikah1")));
                p.setSaksiNikah2(cursor.getString(cursor.getColumnIndex("SaksiNikah2")));
                p.setNoLM(cursor.getString(cursor.getColumnIndex("NomorLM")));
                p.setJenisPerkawinan(cursor.getString(cursor.getColumnIndex("JenisPerkawinan")));
                p.setHubDalamKeluarga(cursor.getString(cursor.getColumnIndex("HubunganDlmKeluarga")));
                p.setDomisili(cursor.getString(cursor.getColumnIndex("Domisili")));
                p.setTempatKrisma(cursor.getString(cursor.getColumnIndex("TempatKrisma")));
                p.setTglKrisma(cursor.getString(cursor.getColumnIndex("TanggalKrisma")));
                p.setNomorKrisma(cursor.getString(cursor.getColumnIndex("NomorKrisma")));
                p.setNamaAyah(cursor.getString(cursor.getColumnIndex("NamaAyah")));
                p.setNamaIbu(cursor.getString(cursor.getColumnIndex("NamaIbu")));
                p.setKeterangan(cursor.getString(cursor.getColumnIndex("Keterangan")));

                pendudukList.add(p);
            } while (cursor.moveToNext());
        }

// close db connection
        db.close();

// return notes list
        return pendudukList;
    }





    // on Upgrade database

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);

        onCreate(db);

    }

}
