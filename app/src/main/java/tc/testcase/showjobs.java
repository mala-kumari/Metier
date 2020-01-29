package tc.testcase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;

public class showjobs extends AppCompatActivity {
String email,exp,name,compname,address,salary;
TextView t1,t2,t3,t4,t5;
//remember to change url in jsondatalist..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showjobs);
        name=getIntent().getExtras().getString("name");
        compname=getIntent().getExtras().getString("companyname");
        address=getIntent().getExtras().getString("address");
        salary=getIntent().getExtras().getString("salary");
        email=getIntent().getExtras().getString("email");
        exp=getIntent().getExtras().getString("experience");
     /*   getIntent().removeExtra("name");
        getIntent().removeExtra("companyname");
        getIntent().removeExtra("address");
        getIntent().removeExtra("salary");
        getIntent().removeExtra("email");*/
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
      /*  t1.invalidate();
        t2.invalidate();
        t3.invalidate();
        t4.invalidate();
        t5.invalidate();*/
        t1.setText(name);
        t2.setText(compname);
        t3.setText(salary);
        t4.setText(address);
        //Toast.makeText(showjobs.this,email,Toast.LENGTH_Long).show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("JobSummary");
                    String txt[]=new String[jsonArray.length()];
                    String txt1="";
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        txt[i]=jsonArray.getString(i);
                        txt1+=txt[i]+"\n";
                    }
                    t5.setSingleLine(false);
                    t5.setText(txt1);
                    String ski=jsonObject.getString("KeySkills");
                    //Toast.makeText(showjobs.this,ski,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void appl(View v)
    {
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);

        if(prefs.getBoolean("loggedin",false))
        { Intent i=new Intent(showjobs.this,jobApply.class);
      startActivity(i);
    }
        else
        {
            AlertDialog.Builder alertd=new AlertDialog.Builder(this);
            alertd.setTitle("You are not Logged in");
            alertd.setPositiveButton("Login or Create Account", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i=new Intent(showjobs.this,Navigation.class);
                    startActivity(i);
                }
            });
            alertd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            Dialog o=alertd.show();

        }
    }
    public void savejob(View v)
    {Toast.makeText(showjobs.this,"Saved",Toast.LENGTH_SHORT).show();
    likeddtb dbh=new likeddtb(this);
    dbh.new_acc(name,email,exp,salary,compname);
    }
}
