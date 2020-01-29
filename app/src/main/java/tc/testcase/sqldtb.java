package tc.testcase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class sqldtb extends SQLiteOpenHelper {
    public static final String dtbname="singleuser";
    public static final String tbname="accounts";
    public static final String id_="id_";
    public static final String name="name";
    public static final String email="email";
    public static final String mobile="mobile";
    public static final String dob="dob";
    public static final String pas="pas";
    public sqldtb( Context context) {
        super(context, dtbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ct="create table accounts " + "(id_ integer primary key, name text,email text,mobile text,dob text,pas text)";
        db.execSQL(ct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS accounts");
        onCreate(db);
    }
    public void new_acc(dataModel ob)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",ob.getname());
        values.put("email",ob.getEmail());
        values.put("mobile",ob.getMobile());
        values.put("dob",ob.getDob());
        values.put("pas",ob.getPas());
        db.insert(tbname,null,values);

    }
    public Cursor getData(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from accounts where _id="+id+"",null);
        return c;
    }
    public ArrayList<String> getUserDetails(String s)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from accounts",null);
        if(c.moveToFirst())
        {
            String t=c.getString(c.getColumnIndex(name));
            while(!(c.isAfterLast())&&t.equals(s)==false)
            {
                c.moveToNext();
                t=c.getString(c.getColumnIndex(name));
            }
        }
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add(c.getString(c.getColumnIndex(name)));
        arrayList.add(c.getString(c.getColumnIndex(email)));
        arrayList.add(c.getString(c.getColumnIndex(mobile)));
        arrayList.add(c.getString(c.getColumnIndex(dob)));
        arrayList.add(c.getString(c.getColumnIndex(pas)));
        return arrayList;
    }
   /* public boolean chkvalidacc(String s,String pas)
    {
        ArrayList<String> arrayList=getUserDetails(s);
        if(arrayList.get(4).equals(pas)==true)
            return true;
        return false;
    }*/
}
