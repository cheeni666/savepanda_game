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
import android.widget.EditText;
import android.widget.TextView;

//import com.myprey.game.R;


public class Score extends ActionBarActivity {
    int finishflag=0;
    EditText me;
    int scores;
    TextView Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(finishflag==1){

            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Score=(TextView)findViewById(R.id.scoretext);
        me=(EditText)findViewById(R.id.editText);
        scores=getIntent().getIntExtra("score",0);
        Score.setText(""+scores);

    }
    public void set(View view){
        if(me.getText()==null||me.getText().length()==0)return;
        Name name=new Name(me.getText().toString());
        name.set_score(scores);
        MyDBHandler db=new MyDBHandler(this,null,null,1);
        if(name.get_name().equals("Upgrade Table")){
            db.Upgrade();
            me.setText("");
            return;
        }
        db.addName(name);
        db.close();
        Intent i=new Intent(this,MenuStart.class);
        startActivity(i);
        finishflag=1;
    }
    public void menu(View view){
        Intent i=new Intent(this,MenuStart.class);
        startActivity(i);
        finishflag=1;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
