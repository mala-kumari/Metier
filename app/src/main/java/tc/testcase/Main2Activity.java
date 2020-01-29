package tc.testcase;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4,ed5;
sqldtb dtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        ed5=(EditText)findViewById(R.id.ed5);
        dtb=new sqldtb(this);
    }
    public void kr(){
        String q1=ed1.getText().toString();
        String q2=ed2.getText().toString();
        String q3=ed3.getText().toString();
        String q4=ed4.getText().toString();
        String q5=ed5.getText().toString();
        dataModel ob=new dataModel(q1,q2,q3,q4,q5);
        dtb.new_acc(ob);
        Intent i=new Intent(Main2Activity.this,Login.class);
        startActivity(i);
    }
}
