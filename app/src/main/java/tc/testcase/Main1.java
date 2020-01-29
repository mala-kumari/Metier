package tc.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class Main1 extends AppCompatActivity {
    String url="https://api.myjson.com/bins/i7egn";
    String email="email";
    String jobname="name";
    String companyname="company";
    String sal="salary";
    String exp="experience";
    String address="address";
    StringRequest stringRequest;
    RadioGroup r_salary,r_area;
    ListView listView;
    RadioButton r1,r2,r3,r4,f1,f2,f3;
    CharSequence f_salary = "none",f_area = "none";
    String name,company,add,salary,em,expe;
    String feild="Android";
    ArrayList<HashMap<String,String>> arrayList=new ArrayList<HashMap<String, String>>();
    int id1_=0;String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       feild=getIntent().getExtras().getString("Field");
       // Toast.makeText(Main1.this,s,Toast.LENGTH_SHORT).show();
        listView =(ListView)findViewById(R.id.l1);
        r_salary = (RadioGroup) findViewById(R.id.j_salary);
        r1 = (RadioButton) findViewById(R.id.s1);
        r2 = (RadioButton) findViewById(R.id.s2);
        r3 = (RadioButton) findViewById(R.id.s3);
        r4 = (RadioButton) findViewById(R.id.none);

        r_area = (RadioGroup) findViewById(R.id.j_area);
        f1 = (RadioButton) findViewById(R.id.f1);
        f2 = (RadioButton) findViewById(R.id.f2);
        f3 = (RadioButton) findViewById(R.id.f3);

        r_salary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (r1.isChecked()) {
                    f_salary = r1.getText();
                } else if (r2.isChecked()) {
                    f_salary = r2.getText();
                } else if (r3.isChecked()) {
                    f_salary = r3.getText();
                } else {
                    f_salary = r4.getText();
                }
            }
        });

        r_area.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (f1.isChecked()) {
                    f_area = f1.getText();
                } else if (f2.isChecked()) {
                    f_area = f2.getText();
                } else {
                    f_area = f3.getText();
                }
            }
        });
        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray(feild);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jobj=jsonArray.getJSONObject(i);
                        name=jobj.getString("name");
                        company=jobj.getString("company_name");
                        add=jobj.getString("address");
                        salary=jobj.getString("salary");
                        em=jobj.getString("email");
                        expe=jobj.getString("experience");
                        HashMap<String, String> hm=new HashMap<String, String>();
                        hm.put(jobname,name);
                        hm.put(companyname,company);
                        hm.put(sal,salary);
                        hm.put(address,add);
                        hm.put(email,em);
                        hm.put(exp,expe);
                        arrayList.add(hm);
                    }
                    SimpleAdapter simpleAdapter=new SimpleAdapter(Main1.this,arrayList,R.layout.customlist,
                            new String[]{jobname,companyname,sal,address},
                            new int[]{R.id.t1,R.id.t2,R.id.t3,R.id.t4});
                    listView.setAdapter(simpleAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                            name=arrayList.get(position).get(jobname);
                                                            company=arrayList.get(position).get(companyname);
                                                            salary=arrayList.get(position).get(sal);
                                                            add=arrayList.get(position).get(address);
                                                            em=arrayList.get(position).get(email);
                                                            expe=arrayList.get(position).get(exp);
                                                            Intent i=new Intent(Main1.this,showjobs.class);
                                                            i.putExtra("name",name);
                                                            i.putExtra("companyname",company);
                                                            i.putExtra("salary",salary);
                                                            i.putExtra("address",add);
                                                            i.putExtra("email",em);
                                                            i.putExtra("experience",expe);
                   //  dbh.insertInputs("deepika","deepikanitesh1998@gmail.com","1234567890","12-01-1998","queen");
                                                            //  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(i);
                                                        }
                                                    }
                    );
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

    }   public void apply1(View v) {
        arrayList.clear();


        Toast.makeText(this, f_salary, Toast.LENGTH_LONG).show();
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(feild);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        name = jobj.getString("name");
                        company = jobj.getString("company_name");
                        add = jobj.getString("address");
                        salary = jobj.getString("salary");
                        em = jobj.getString("email");
                        expe = jobj.getString("experience");
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put(jobname, name);
                        hm.put(companyname, company);
                        hm.put(sal, salary);
                        hm.put(address, add);
                        hm.put(email, em);
                        hm.put(exp, expe);
                        if (f_salary.equals("none") && f_area.equals("none")) {
                            arrayList.add(hm);
                        }
                        if (f_salary.equals(salary) && f_area.equals(name)) {

                            arrayList.add(hm);
                        }
                    }
                    SimpleAdapter simpleAdapter = new SimpleAdapter(Main1.this, arrayList, R.layout.customlist,
                            new String[]{jobname, companyname, sal, address},
                            new int[]{R.id.t1, R.id.t2, R.id.t3, R.id.t4});
                    listView.setAdapter(simpleAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                            name=arrayList.get(position).get(jobname);
                                                            company=arrayList.get(position).get(companyname);
                                                            salary=arrayList.get(position).get(sal);
                                                            add=arrayList.get(position).get(address);
                                                            em=arrayList.get(position).get(email);
                                                            expe=arrayList.get(position).get(exp);
                                                            Intent i=new Intent(Main1.this,showjobs.class);
                                                            i.putExtra("name",name);
                                                            i.putExtra("companyname",company);
                                                            i.putExtra("salary",salary);
                                                            i.putExtra("address",add);
                                                            i.putExtra("email",em);
                                                            i.putExtra("experience",expe);
                                                            //dbh.insertInputs("deepika","deepikanitesh1998@gmail.com","1234567890","12-01-1998","queen");

                                                            //  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(i);
                                                        }
                                                    }
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


        View vfilter = findViewById(R.id.filter);
        vfilter.setVisibility(View.GONE);
        View vlist = findViewById(R.id.list);
        vlist.setVisibility(View.VISIBLE);

    }
    public void change1(View v) {
        View vlist = findViewById(R.id.list);
        vlist.setVisibility(View.GONE);

        View vfilter = findViewById(R.id.filter);
        vfilter.setVisibility(View.VISIBLE);
    }

}