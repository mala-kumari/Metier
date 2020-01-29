package tc.testcase;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button buttn;
    EditText name,email,mob,dob,pass,pass2;
    String nm,mail,dobb,no,pas,pas2;
    SharedPreferences prefs ;
    SharedPreferences.Editor editor;
    sqldtb myDbHandler;
    //   DbHelp dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   dbh=new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.name1);
        email = (EditText) findViewById(R.id.email1);
        mob = (EditText) findViewById(R.id.mob1);
        dob = (EditText) findViewById(R.id.dob1);
        pass = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass21);
       prefs = getSharedPreferences("login", MODE_PRIVATE);
        editor = prefs.edit();
        editor.putBoolean("loggedin",false);
        editor.commit();
        myDbHandler=new sqldtb(this);
        buttn = (Button) findViewById(R.id.btn);
        buttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // db = openHelper.getWritableDatabase();
                nm = name.getText().toString();
                mail = email.getText().toString();
                no = mob.getText().toString();
                dobb = dob.getText().toString();
                pas = pass.getText().toString();
                pas2 = pass2.getText().toString();
                //dbHelp.insertInputs(nm,mail,no,dobb,pas);
                dataModel su=new dataModel(nm,mail,no,dobb,pas);
                myDbHandler.new_acc(su);
                register(nm, mail, no, dobb, pas, pas2);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //TODO if (id == R.id.action_home) {

            //Intent i= new Intent(CreateAcc.this , Navigation.class);
           // startActivity(i);
        //}

        return super.onOptionsItemSelected(item);
    }

    public void register( String nm ,String mail ,String no ,String dobb ,String pas ,String pas2 )
    {
       /* ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,nm);
        contentValues.put(DatabaseHelper.COL_2,mail);
        contentValues.put(DatabaseHelper.COL_3,no);
        contentValues.put(DatabaseHelper.COL_4,dobb);
        contentValues.put(DatabaseHelper.COL_5,pas);
        contentValues.put(DatabaseHelper.COL_6,pas2);*/
        int x = no.length();
        int y = pas.length();

        if(nm.isEmpty())
        {
            name.setError("field is empty");
        }
        else if(mail.isEmpty())
        {
            email.setError("field is empty");
        }
        else if(no.isEmpty())
        {
            mob.setError("field is empty");
        }
        else if (x!=10)
        {
            mob.setError("Invalid no.");
        }
        else if(dobb.isEmpty())
        {
            dob.setError("field is empty");
        }
        else if(pas.isEmpty())
        {
            pass.setError("field is empty");
        }
        else if (y<6)
        {
            pass.setError("min 6 characters are required");
        }
        else if (pas.equals(pas2))
        {
            //long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

            editor.putBoolean("loggedin",true);
            editor.putString("username",nm);
            editor.commit();
            Intent i = new Intent(MainActivity.this , Login.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(MainActivity.this,"Passwords do not match",Toast.LENGTH_LONG).show();
            pass2.clearFocus();
        }
    }

}
