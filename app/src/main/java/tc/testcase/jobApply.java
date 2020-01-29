package tc.testcase;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class jobApply extends AppCompatActivity {

   sqldtb dtb;
    EditText nm,email,contacts,dob;
    Button btn;
    TextView txt;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_apply);
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        username=prefs.getString("username","null");
       dtb=new sqldtb(this);
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList=dtb.getUserDetails(username);
        btn=(Button) findViewById(R.id.attach);
        txt=(TextView) findViewById(R.id.res_view);
        nm=(EditText)findViewById(R.id.nm);
        email=(EditText)findViewById(R.id.email);
        contacts=(EditText)findViewById(R.id.contacts);
        dob=(EditText)findViewById(R.id.dob);
        nm.setText(arrayList.get(0));
        email.setText(arrayList.get(1));
        contacts.setText(arrayList.get(2));
        dob.setText(arrayList.get(3));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
          btn.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {
        new MaterialFilePicker()
                .withActivity(jobApply.this)
                .withRequestCode(1000)
                //    .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                //    .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
          }
         });
        // password.setText(arrayList.get(1));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            btn.setVisibility(View.GONE);
            txt.setVisibility(View.VISIBLE);
txt.setText(filePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(jobApply.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(jobApply.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
    public void apply(View v)
    {
        Toast.makeText(jobApply.this,"Applied Successfully",Toast.LENGTH_LONG).show();
        Intent i=new Intent(jobApply.this,Main1.class);
        startActivity(i);
        //if user again wants to apply,stop him.
    }
    public void resview(View v)
    {
        Intent i=new Intent(jobApply.this,AndroidExplorer.class);
        startActivity(i);
    }
}
