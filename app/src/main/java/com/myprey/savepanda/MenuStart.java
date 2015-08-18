package com.myprey.savepanda;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

//import com.myprey.game.R;


public class MenuStart extends ActionBarActivity {
    private static int exit=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            exit=1;
            onStop();
            return;
        }
        setContentView(R.layout.activity_menu_start);
        if(exit==0)Toast.makeText(this,"PLEASE DO NOT USE HARDWARE BUTTONS",Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(exit==1)onStop();
    }
}
