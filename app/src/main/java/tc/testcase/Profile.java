package tc.testcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
ImageButton btn;
    private static final int PICK_IMAGE = 100;
    private static final int RESULT_LOAD_IMAGE = 1;
    TextView txt1,txt2,txt3,txt4;
    ImageView image;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.image_view);
        btn = findViewById(R.id.choose);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        txt3 = findViewById(R.id.text3);
        txt4 = findViewById(R.id.text4);
        txt1.invalidate();
        txt2.invalidate();
        txt3.invalidate();
        txt4.invalidate();
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        String username = prefs.getString("username", "null");
        sqldtb dtb = new sqldtb(this);
        ArrayList<String> arrayList = dtb.getUserDetails(username);
        txt1.setText(arrayList.get(0));
        txt2.setText(arrayList.get(1));
        txt3.setText(arrayList.get(2));
        txt4.setText(arrayList.get(3));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }

        });

    }
    private void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void  onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode , resultCode , data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {
            imageUri = data.getData();
            image.setImageURI(imageUri);
            Intent i = new Intent(this , Navigation.class);
            i.putExtra("imageUri" , imageUri);
        }
    }

}
