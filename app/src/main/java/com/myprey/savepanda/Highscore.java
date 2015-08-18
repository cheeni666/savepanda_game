package com.myprey.savepanda;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

//import com.myprey.game.R;


public class Highscore extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        MyDBHandler db=new MyDBHandler(this,null,null,1);
        String text=db.databaseToString();
        TextView t=(TextView)findViewById(R.id.poing);
        t.setText(text);
        db.close();
    }
    public void back(View view){
        Intent i=new Intent(this,MenuStart.class);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
