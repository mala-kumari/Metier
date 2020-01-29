package tc.testcase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class likeddtb extends SQLiteOpenHelper {
    public static final String dtbname="likedjobs";
    public static final String tbname="joblists";
    public static final String id_="id_";
    public static final String name="name";
    public static final String email="email";
    public static final String salary="salary";
    public static final String exp="exp";
    public static final String address="address";
    public likeddtb(Context context) {
        super(context, dtbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ct="create table joblists " + "(id_ integer primary key, name text,email text,salary text,exp text,address text)";
        db.execSQL(ct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS joblists");
        onCreate(db);
    }
    public void new_acc(String name,String email,String exp,String salary,String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("salary",salary);
        values.put("exp",exp);
        values.put("address",address);
        db.insert(tbname,null,values);

    }
    public Cursor getData(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from joblists where _id="+id+"",null);
        return c;
    }
    public ArrayList<HashMap<String,String>> getClicked()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from joblists",null);
        HashMap<String,String> hashMap=new HashMap<String, String>();
       c.moveToFirst();
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<HashMap<String, String>>();
       while(c.isAfterLast()==false){
        hashMap.put("1",c.getString(c.getColumnIndex(name)));
        hashMap.put("2",c.getString(c.getColumnIndex(email)));
        hashMap.put("3",c.getString(c.getColumnIndex(salary)));
        hashMap.put("4",c.getString(c.getColumnIndex(exp)));
        hashMap.put("5",c.getString(c.getColumnIndex(address)));
       c.moveToNext();
       arrayList.add(hashMap);
       }

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
