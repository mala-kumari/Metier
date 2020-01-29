package tc.testcase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {

    Context mcontext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String KeyName = "name";


    public SessionManager(Context context)
    {
        this.mcontext = context;
        sharedPreferences =
                mcontext.getSharedPreferences("Login" ,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void SetData(String name) {
        editor.putString(KeyName , name);
        Log.d("keyname " ,">>>>>>>>"+ editor );
        editor.commit();
        //commit==binding

    }

    public HashMap<String,String> GetData() {

        HashMap<String , String> user = new HashMap<>();
        user.put(KeyName, sharedPreferences.getString(KeyName  , null));
        return user;

    }
}
