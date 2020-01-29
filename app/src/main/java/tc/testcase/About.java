package tc.testcase;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class About extends AppCompatActivity {
String url="https://api.myjson.com/bins/h105p";
TextView textView,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView=findViewById(R.id.t1);
        t5=findViewById(R.id.t5);
        TextView under=findViewById(R.id.under);
        under.setText("Developed under:\nMr.Kapil Manchandani,NIIT");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String t1=jsonObject.getString("About");
                    JSONArray jsonArray=jsonObject.getJSONArray("Features");
                    String txt[]=new String[jsonArray.length()];
                    String txt1="";
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        txt[i]=jsonArray.getString(i);
                        txt1+=(i+1)+" "+txt[i]+"\n";
                    }
                    textView.setSingleLine(false);
                    textView.setText(t1);
                    t5.setSingleLine(false);
                    t5.setText(txt1);
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
}
