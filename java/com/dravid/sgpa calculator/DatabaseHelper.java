package com.dravid.sgpacalci2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 07-06-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "details.db";
    private static final String TABLE_NAME1 = "contacts";
    private static final String TABLE_NAME2 = "SGP";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID2 = "id2";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USN = "usn";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_SEM = "sem";
    private static final String COLUMN_USN2 = "usn2";
    private static final String COLUMN_SUBJECTS = "subjects";
    private static final String COLUMN_CREDITS = "credits";
    private static final String COLUMN_SGPA = "sgpa";
    static int j =0;
    // SQLiteDatabase db;

    private static final String TABLE_CREATE1 = "create table contacts (id integer primary key not null" +
            ",name text not null,email text not null,usn text not null,pass text not null);";

    private static final String TABLE_CREATE2 = "create table SGP (id2 integer primary key not null,usn2 text not null,sem text not null,subjects text not null,credits text not null,sgpa text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        j++;

        //  Log.e("MyActivity","onCreate method called "+j );
        db.execSQL(TABLE_CREATE1);
        db.execSQL(TABLE_CREATE2);
        //  this.db=db;

    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String getName(String usn)
    {
        String u;
        String name="Not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select name,usn from "+TABLE_NAME1;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                u=cursor.getString(1);
                if(u.equals(usn)){
                    name = cursor.getString(0);
                    break;
                }

            }while (cursor.moveToNext());

        }

        return name;
    }

    public int checkSem(String sem,String usn)
    {
        int i=0;
        String u,s;
        SQLiteDatabase db = this.getWritableDatabase();
        //  Log.e("created");
        String query = "select usn2,sem from "+TABLE_NAME2;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);
                if(u.equals(usn)){
                    s = cursor.getString(1);
                    // semi = Integer.parseInt(s);
                    if(s.equals(sem))
                    {
                        i=1;
                        break;
                    }
                }

            }while(cursor.moveToNext());

        }
        return i;
    }

    public String getSGPA(String sem,String usn)
    {
        String sgpa = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String s,u,query="select usn2,sem,sgpa from "+TABLE_NAME2;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                u=cursor.getString(0);
                if(u.equals(usn)){
                    s=cursor.getString(1);
                    if(s.equals(sem)){
                        sgpa = cursor.getString(2);
                        break;
                    }
                }
            } while (cursor.moveToNext());
        }
        return  sgpa;

    }

    public float getCGPA(String usn)
    {
        float cgpa=0;
        SQLiteDatabase db = this.getWritableDatabase();
        String u,query = "select usn2,sgpa,credits from "+TABLE_NAME2;
        float prod=0,c=0;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);
                if(u.equals(usn)){
                    float s = Float.parseFloat(cursor.getString(1));
                    float cr = Float.parseFloat(cursor.getString(2));
                    prod = prod + (s*cr);
                    c = c + Float.parseFloat(cursor.getString(2));
                }
            }while(cursor.moveToNext());

        }
        cgpa = prod / c;
        cgpa = Math.round(cgpa*100.0f)/100.0f;
        return cgpa;
    }

    public String searchPass(String usn)
    {
        String a,b;
        b="not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select usn,pass from "+TABLE_NAME1;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(0);
                if(a.equals(usn)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    public int insertContact(Contact c)
    {
        int i=1;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        String query2 = "select usn from contacts";
        Cursor cr =db.rawQuery(query2,null);
        String u;
        int flag=1;

        if(cr.moveToFirst())
        {
            do{
                u=cr.getString(0);
                if(u.equals(c.getUSN())){
                    flag=0;
                    break;
                }
            }
            while(cr.moveToNext());
        }

        if(flag!=0) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, count);
            values.put(COLUMN_NAME, c.getName());
            values.put(COLUMN_EMAIL, c.getEmail());
            values.put(COLUMN_USN, c.getUSN());
            values.put(COLUMN_PASS, c.getPass());

            db.insert(TABLE_NAME1, null, values);
            db.close();
        }
        else{
            i=0;

            db.close();
        }
        return i;

    }

    public int insertSGPA(Contact c)
    {
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "select * from SGP";
        Cursor cursor = db.rawQuery(query1,null);
        int count = cursor.getCount();
        int id=0;
        String query = "select usn2,sem,id2 from "+TABLE_NAME2;
        Cursor cr = db.rawQuery(query,null);
        String u,s;
        int flag=1;

        if(cr.moveToFirst())
        {
            do{
                u=cr.getString(0);
                if(u.equals(c.getUSN())){
                    s = cr.getString(1);
                    if(s.equals(c.getSem())) {
                        id = cr.getInt(2);
                        flag = 0;
                        break;
                    }
                }

            }
            while(cr.moveToNext());
        }
        if(flag!=0){
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID2,count);
            values.put(COLUMN_SEM,c.getSem());
            values.put(COLUMN_SUBJECTS,c.getSub());
            values.put(COLUMN_CREDITS,c.getCredits());
            values.put(COLUMN_USN2,c.getUSN());
            values.put(COLUMN_SGPA,c.getSGPA());
            i=1;


            db.insert(TABLE_NAME2, null, values);
            db.close();
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID2,id);
            values.put(COLUMN_SEM,c.getSem());
            values.put(COLUMN_SUBJECTS,c.getSub());
            values.put(COLUMN_CREDITS,c.getCredits());
            values.put(COLUMN_USN2,c.getUSN());
            values.put(COLUMN_SGPA,c.getSGPA());

            int k= db.update(TABLE_NAME2,values,COLUMN_ID2 + "=" + id,null);
            if(k>0){
                i=1;
            }
            db.close();

        }

        return i;
    }

    public String getSems(String usn)
    {
        String s = "\n",u;
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "select usn2,sem from "+TABLE_NAME2;
        Cursor cr = db.rawQuery(query,null);
        if(cr.moveToFirst())
        {
            do {
                u = cr.getString(0);
                if(u.equals(usn))
                {

                    s+= cr.getString(1);
                    s+="\n\n";

                }
            }while(cr.moveToNext());
        }
        db.close();
        return s;
    }
    public String getGPAs(String usn)
    {
        String g = "\n",u;
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "select usn2,sgpa from "+TABLE_NAME2;
        Cursor cr = db.rawQuery(query,null);
        if(cr.moveToFirst())
        {
            do {
                u = cr.getString(0);
                if(u.equals(usn))
                {

                    g+= cr.getString(1);
                    g+="\n\n";

                }
            }while(cr.moveToNext());
        }
        db.close();
        return g;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        String query1 = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        String query2 = "DROP TABLE IF EXISTS "+TABLE_NAME2;
        db.execSQL(query1);
        db.execSQL(query2);
        this.onCreate(db);
    }
}
