package projectname.companyname.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mainmenu extends Activity implements OnClickListener {

    //define buttons
    private Button btn_settings;
    private Button btn_others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(this); //set listener to this button

       // btn_others = findViewById(R.id.btnothers);
       // btn_others.setOnClickListener(this); //set listener to this button
    }

    @Override
    //invoke callback event in view
    public void onClick(View v) {
        // intent = action to be performed
        // intent is an object that provides runtime binding
        // new instance of this object intent

        Intent intent = new Intent();
        // if user touches object, remove object
        if (TouchManager.Instance.IsDown())
        {
            intent.setClass(this, GamePage.class);
        }

        if (v == btn_settings)
        {
            //intent is to set to another class which another page or screen that we are launching
            intent.setClass(this, GamePage.class);
        }
        //else if (v == something else){
        //}
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

