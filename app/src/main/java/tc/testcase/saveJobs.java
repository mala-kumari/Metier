package tc.testcase;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class saveJobs extends AppCompatActivity {
    ListView listView;
    int id_=0,i,j;
    likeddtb dbh;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_=getIntent().getIntExtra("id_",0);
        setContentView(R.layout.activity_save_jobs);
        listView=(ListView)findViewById(R.id.likedList);
        dbh=new likeddtb(this);
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        HashMap<String,String> hashMap=new HashMap<>();
        arrayList=dbh.getClicked();
        SimpleAdapter simpleAdapter=new SimpleAdapter(saveJobs.this,arrayList,R.layout.singlelike, new String[]{"1","2","3","4","5"},
                new int[]{R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4});
        listView.setAdapter(simpleAdapter);
    }
}
