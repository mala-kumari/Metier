package tc.testcase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Login extends AppCompatActivity {

    int id_=0;
    sqldtb dtb;
    SQLiteOpenHelper openHelper,oph;
    SQLiteDatabase db,db1;
    //DatabaseHelper dbh;
    //  DbHelp dbHelp;
    Button button;
    EditText ed3,ed4;
    Bundle bundle;
    Cursor cursor;
    boolean show;
    String user_name,password;

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // dbh = new DatabaseHelper(this);
       // openHelper = new DatabaseHelper(this);
        dtb=new sqldtb(this);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
       // btnlogin = (Button) findViewById(R.id.btnlogin);
        SharedPreferences prefs = getSharedPreferences("login",
                MODE_PRIVATE);
        if( prefs.getBoolean("loggedin",false))
        {

            Intent i=new Intent(Login.this,Search.class);
            startActivity(i);
        }

    }



    public void login(View v) {
        String name = ed3.getText().toString();
        String pass = ed4.getText().toString();
        ArrayList<String> arrayList = dtb.getUserDetails(ed3.getText().toString());
        if (arrayList.get(0).equals(name) && arrayList.get(4).equals(pass))
        {SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("loggedin",true);
            editor.putString("username",name);
            editor.commit();
            Toast.makeText(Login.this, arrayList.get(1), Toast.LENGTH_LONG).show();

        Intent i = new Intent(Login.this, Search.class);

        startActivity(i);
    }

                else
        Toast.makeText(Login.this,"NULL",Toast.LENGTH_LONG).show();
    }
    public void create(View v)
    {
      //TODO  Intent i = new Intent(Login.this , Create.class);
     //   startActivity(i);
    }
    @Override
    public void onBackPressed() {
    //TODO    DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       //TODO getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // TODO if (id == R.id.action_home) {}

        return super.onOptionsItemSelected(item);
    }
}
