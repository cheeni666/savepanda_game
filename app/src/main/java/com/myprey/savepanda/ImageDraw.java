package com.myprey.savepanda;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

//import com.myprey.game.R;

public class ImageDraw extends View {
    float width,height;
    MediaPlayer wind;
    float xline;
    Intent i;
    Paint brush ;
    Rect screen ;
    Canvas refCanvas;
    float x=-60,y=0;
    float degree=333;
    float degree2=0,x2,y2;
    float state=-1;
    int time=0;
    int viewcounter=0;
    Matrix m=new Matrix();
    Context cont;
    Bitmap twig;
    Bitmap panda;
    Bitmap twig2;

    public ImageDraw(Context context) {
        super(context);
        cont=context;

    }

    public ImageDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        cont=context;
    }

    public ImageDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cont=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        refCanvas=canvas;
        if(viewcounter==0){
            width = canvas.getWidth();
            height = canvas.getHeight();
            twig= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.twig), (int) width / 4, (int) height / 4, false);
            panda=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.panda), (int) width / 4, (int) height / 4, false);
            twig2= Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.twig2), (int)width/8,(int)height/8, false);
            x=0;
            y=0;
            xline=width;
            wind=MediaPlayer.create(cont,R.raw.wind);
            wind.start();
        }
        if(state==-1){
            screen = new Rect();
            brush = new Paint();
            //background
            brush.setColor(Color.CYAN);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, 0, (int) width, (int) height);
            canvas.drawRect(screen, brush);
            m.setRotate(degree);
            refCanvas.drawBitmap(twig, 0, height /4, null);
            refCanvas.drawBitmap(twig2, twig.getWidth(),height/8+10 , null);
            refCanvas.drawBitmap(Bitmap.createBitmap(panda, 0, 0, panda.getWidth(), panda.getHeight(), m, false), x, y, null);
        }
        if(state==0) {

            screen = new Rect();
            brush = new Paint();
            //background
            brush.setColor(Color.CYAN);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, 0, (int) width, (int) height);
            canvas.drawRect(screen, brush);
            m.setRotate(degree);
            refCanvas.drawBitmap(twig, 0, height /4, null);
            refCanvas.drawBitmap(twig2, twig.getWidth(),height/8+10 , null);
            refCanvas.drawBitmap(Bitmap.createBitmap(panda, 0, 0, panda.getWidth(), panda.getHeight(), m, false), x, y, null);
            x2=  twig.getWidth();
            y2=height/8+10;
        }
        if(state==1||state==2) {

            screen = new Rect();
            brush = new Paint();

            m.setRotate(degree);
            //background
            brush.setColor(Color.CYAN);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, 0, (int) width, (int) height);
            canvas.drawRect(screen, brush);

            refCanvas.drawBitmap(twig, 0, height /4, null);
            refCanvas.drawBitmap(Bitmap.createBitmap(panda, 0, 0, panda.getWidth(),panda.getHeight(), m, false), x, y, null);

        }

        if(state==3) {

            screen = new Rect();
            brush = new Paint();
            //background
            brush.setColor(Color.CYAN);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, 0, (int) width, (int) height);
            canvas.drawRect(screen, brush);

            refCanvas.drawBitmap(twig, 0, height / 4 , null);

        }
        if(state==4){

            screen = new Rect();
            brush = new Paint();
            //background
            brush.setColor(Color.CYAN);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, 0, (int) width, (int) height);
            canvas.drawRect(screen, brush);

            screen = new Rect();
            brush = new Paint();
            //background
            brush.setColor(Color.BLUE);
            brush.setStyle(Paint.Style.FILL);
            screen.set(0, (int) height * 3 / 4, (int) width, (int) height);
            canvas.drawRect(screen, brush);
        }
        if(state==-1){
            wind();
            if(!wind.isPlaying())wind.start();
            invalidate();
        }

        if(state==0){
            Static();
            if(!wind.isPlaying())wind.start();
            invalidate();
        }
        if(state==1){
            Twig();
            invalidate();
        }
        if(state==2){
            Text();
            invalidate();
        }
        if(state==3||state==4){
            if(!wind.isPlaying())wind.start();
            Down();
            invalidate();
        }
        if(state==5){
            i=new Intent(cont,MenuStart.class);
            cont.startActivity(i);
            wind.stop();
        }
        if(viewcounter==0)viewcounter++;
    }
    public void Static(){
        time++;
        if(time==50){
            state=1;
            wind.stop();

        }
    }
    public void wind(){
        brush=new Paint();
        brush.setColor(Color.WHITE);
        brush.setStrokeWidth(2);
        refCanvas.drawLine(xline, height / 8, xline + width / 2, height / 8, brush);
        refCanvas.drawLine(xline, height / 4, xline + width / 2, height / 4, brush);
        refCanvas.drawLine(xline,height/8*3,xline+width/2,height/8*3,brush);
        xline-=8;
        if(xline<=-width/2){
            state=0;
            wind.stop();
            wind=new MediaPlayer();
            wind=MediaPlayer.create(cont,R.raw.twig);
            wind.start();
        }
    }
    public void Twig(){
        width=refCanvas.getWidth();
        height=refCanvas.getHeight();

        m.setRotate(degree2);

        refCanvas.drawBitmap(Bitmap.createBitmap(twig2, 0, 0, twig2.getWidth(), twig2.getHeight(), m, false), x2, y2, null);

        y2+=10;
        degree2+=10;
        if(degree2>360)degree2-=360;
        if(y2>height){
            state=2;
            wind=new MediaPlayer();
            wind=MediaPlayer.create(cont,R.raw.oops);
            wind.start();
        }

    }
    public void Text(){
        brush.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
        brush.setColor(Color.RED);
        brush.setTextSize(width / 5);
        refCanvas.drawText("WHOOPS!!",0,height*3/4,brush);
        time++;
        if(time==150){
            state=3;
            wind.stop();
            wind=new MediaPlayer();
            wind=MediaPlayer.create(cont,R.raw.fall);
            wind.start();
        }
    }
    public void Down(){
        width=refCanvas.getWidth();
        height=refCanvas.getHeight();

        m.setRotate(degree);

        refCanvas.drawBitmap(Bitmap.createBitmap(panda, 0, 0, panda.getWidth(), panda.getHeight(), m, false), x, y, null);

        y+=20;
        degree+=15;
        if(degree>360)degree-=360;
        if(y>height&&state==3){
            y=0;
            state=4;
        }
        if(y>height*3/4&&state==4){
            wind.stop();
            wind=new MediaPlayer();
            wind=MediaPlayer.create(cont, R.raw.splash);
            wind.start();
            state=5;
            long time=System.currentTimeMillis();
            while(System.currentTimeMillis()<time+1000);
        }

    }

}
