package tc.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
Boolean show;
String feild="Android";
Spinner spinner,spinner_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
       // bundle=getIntent().getExtras();
     //   Toast.makeText(Search.this,id_,Toast.LENGTH_SHORT).show();
        spinner=(Spinner)findViewById(R.id.spinner);
        List<String>list=new ArrayList<String>();
        list.add("Select a Location");
        list.add("Lucknow");
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner_1=(Spinner)findViewById(R.id.spinner_1);
        List<String>list1=new ArrayList<String>();
        list1.add("Select a Requirement");
        list1.add("Android");
        list1.add("DataAnalyst");
        final ArrayAdapter<String>arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(arrayAdapter1);
        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_1.setSelection(i);
                feild=arrayAdapter1.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent i= new Intent(Search.this , Navigation.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
    public void search(View v)
    {
        Intent i = new Intent(this,Main1.class);
        i.putExtra("Field",feild);
        startActivity(i);
    }
}
