package com.myprey.savepanda;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

//import com.myprey.game.R;

import java.util.Calendar;


public class Game extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Display display = getWindowManager().getDefaultDisplay();
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);
        ImageView day = (ImageView) findViewById(R.id.imageView);
        if (time <= 6 && time >= 5) day.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.dawn)
        );
        else if (time > 6 && time < 17) day.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.sun)
        );
        else if (time >= 17 && time <= 18) day.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.dusk)
        );
        else day.setImageBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.night)
            );
        ImageView grass1 = (ImageView) findViewById(R.id.imageView2), grass2 = (ImageView) findViewById(R.id.imageView3);
        grass1.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.land1)
        );
        grass2.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.land)
        );

        Engine draw = (Engine) findViewById(R.id.game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
