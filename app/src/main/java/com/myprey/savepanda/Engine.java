package com.myprey.savepanda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Ramanan on 7/2/2015.
 */
public class Engine extends View {
    MediaPlayer pcol, col, back, crocy;
    Paint brush = new Paint();
    Context cont;
    static Canvas ref;
    Rect bounds = new Rect();
    int c = 0;
    int viewcounter = 0;
    Random random = new Random();
    int fpanda = 0;
    float width, height, xpanda, pandamove = 0, crocmove = 0;
    static Bitmap water;
    static Bitmap log;
    static Bitmap panda1;
    static Bitmap panda2, leaf, croc, power;
    int defeat = 0;
    float riverspeed = 4;
    int wood = 0;
    int crocstate = 1;
    int pleaf = 0;
    static float[][] logs = new float[3][2];
    static float[] point = new float[3];
    static float[] jaw = new float[2];
    float pandapower = 0;
    Intent i;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (defeat == 1) {
                    break;
                }
                if (logs[0][1] < 8 * height / 12 - height / 48 && logs[0][1] > 6 * height / 12 + height / 48) {
                    if (logs[0][0] == width / 2) {
                        if (xpanda <= width / 2 && xpanda > width / 2 - panda1.getWidth()) {

                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    } else {
                        if (xpanda >= 0 && xpanda < panda1.getWidth()) {
                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    }
                }
                if (logs[1][1] < 8 * height / 12 - height / 48 && logs[1][1] > 6 * height / 12 + height / 48) {
                    if (logs[1][0] == width / 2) {
                        if (xpanda <= width / 2 && xpanda > width / 2 - panda1.getWidth()) {
                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    } else {
                        if (xpanda >= 0 && xpanda < panda1.getWidth()) {
                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    }
                }
                if (logs[2][1] < 8 * height / 12 - height / 48 && logs[2][1] > 6 * height / 12 + height / 48) {
                    if (logs[2][0] == width / 2) {
                        if (xpanda <= width / 2 && xpanda > width / 2 - panda1.getWidth()) {
                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    } else {
                        if (xpanda >= 0 && xpanda < panda1.getWidth()) {
                            defeat = 1;
                            back.stop();
                            col.start();
                        }
                    }
                }
                if (point[1] < 8 * height / 12 && point[1] > 6 * height / 12) {
                    if (point[0] == width / 2) {
                        if (xpanda <= width / 2 && xpanda > width / 2 - panda1.getWidth()) {
                            point[2] = 0;
                            pcol.start();
                        }
                    } else {
                        if (xpanda >= 0 && xpanda < panda1.getWidth()) {
                            point[2] = 0;
                            pcol.start();
                        }
                    }
                }
                if (jaw[1] < 8 * height / 12 - height / 48 && jaw[1] > 6 * height / 12 + height / 48 && pandapower == 0) {
                    if (xpanda < jaw[0] + croc.getWidth() && xpanda > jaw[0] - panda1.getWidth()) {
                        defeat = 1;
                        back.stop();
                        crocy.start();
                        crocstate = 0;
                    }
                }
            }
        }
    };

    Thread collision = new Thread(r);

    public Engine(Context context) {
        super(context);
        cont = context;
        i = new Intent(cont, Score.class);


    }

    public Engine(Context context, AttributeSet attrs) {
        super(context, attrs);
        cont = context;
        i = new Intent(cont, Score.class);

    }

    public Engine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cont = context;
        i = new Intent(cont, Score.class);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (defeat == 2) return;
        if (viewcounter == 0) {
            back = MediaPlayer.create(cont, R.raw.mario);
            pcol = MediaPlayer.create(cont, R.raw.coin);
            col = MediaPlayer.create(cont, R.raw.coll);
            crocy = MediaPlayer.create(cont, R.raw.hambu);
            ref = canvas;
            width = canvas.getWidth();
            height = canvas.getHeight();
            water = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.water1),
                    (int) width, (int) height, true
            );
            log = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.log),
                    (int) width / 4, (int) height / 12, true
            );
            panda1 = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.gamepanda1),
                    (int) width / 4, (int) height / 12, true
            );
            panda2 = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.gamepanda2),
                    (int) width / 4, (int) height / 12, true
            );
            leaf = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.point),
                    (int) width / 16 * 3, (int) height / 16, true
            );
            croc = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.croc_burned),
                    (int) width / 16 * 3, (int) height / 16, true
            );
            power = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getResources(), R.drawable.powerpanda),
                    (int) width / 4, (int) height / 12, true
            );
            brush.setStyle(Paint.Style.FILL_AND_STROKE);
            brush.setColor(Color.BLACK);
            brush.setTextSize(width / 8);
            brush.getTextBounds("P", 0, 1, bounds);
            xpanda = 0;
            logs[0][0] = random.nextInt(2) * width / 2;
            logs[1][0] = random.nextInt(2) * width / 2;
            logs[2][0] = random.nextInt(2) * width / 2;
            logs[0][1] = 0;
            logs[1][1] = -5 * height / 12;
            logs[2][1] = -10 * height / 12;
            point[0] = random.nextInt(2) * width / 2;
            point[1] = 1 + random.nextInt(24);
            point[1] *= -height / 12;
            jaw[0] = random.nextInt(2) * width / 2;
            if (jaw[0] == 0) crocmove = 1;
            else crocmove = -1;
            jaw[1] = 24 + random.nextInt(25);
            jaw[1] *= -height / 12;
            point[2] = 1;
            collision.start();
            back.start();
        }

        if (pandamove == 1) {
            if (xpanda != width / 2) xpanda += 10;
            else pandamove = 0;
        }
        if (pandamove == -1) {
            if (xpanda != 0) xpanda -= 10;
            else pandamove = 0;
        }
        canvas.drawBitmap(water, 0, 0, null);

        brush.setStyle(Paint.Style.FILL_AND_STROKE);
        brush.setColor(Color.BLACK);
        canvas.drawCircle(3 * width / 4 + width / 8, height / 2, width / 8, brush);
        brush.setColor(Color.WHITE);
        brush.setTextSize(width / 8);
        canvas.drawText("P", 3 * width / 4 + width / 8 - bounds.width() / 2, height / 2 + bounds.height() / 2, brush);
        if (crocmove == 1) {
            jaw[0] += 2;
            if (jaw[0] > 3 * width / 4 - croc.getWidth()) crocmove = -1;
        } else if (crocmove == -1) {
            jaw[0] -= 2;
            if (jaw[0] < 0) crocmove = 1;
        }

        if (jaw[1] >= -croc.getHeight()) canvas.drawBitmap(croc, jaw[0], jaw[1], null);
        jaw[1] += riverspeed;
        if (jaw[1] > height) {
            jaw[0] = random.nextInt(2) * width / 2;
            if (jaw[0] == 0) crocmove = 1;
            else crocmove = -1;
            jaw[1] = 24 + random.nextInt(25);
            jaw[1] *= -height / 12;
            if (crocstate == 1) pleaf += 10;
            crocstate = 1;
        }

        if (logs[0][1] >= -log.getHeight()) canvas.drawBitmap(log, logs[0][0], logs[0][1], null);
        logs[0][1] += riverspeed;
        if (logs[0][1] > height) {
            logs[0][1] = -3 * height / 12;
            wood++;
            if (wood % 6 == 0) riverspeed++;
            logs[0][0] = random.nextInt(2) * width / 2;
        }

        if (logs[1][1] >= -log.getHeight()) canvas.drawBitmap(log, logs[1][0], logs[1][1], null);
        logs[1][1] += riverspeed;
        if (logs[1][1] > height) {
            logs[1][1] = -3 * height / 12;
            wood++;
            if (wood % 6 == 0) riverspeed++;
            logs[1][0] = random.nextInt(2) * width / 2;
        }

        if (logs[2][1] >= -log.getHeight()) canvas.drawBitmap(log, logs[2][0], logs[2][1], null);
        logs[2][1] += riverspeed;
        if (logs[2][1] > height) {
            logs[2][1] = -3 * height / 12;
            wood++;
            if (wood % 6 == 0) riverspeed++;
            logs[2][0] = random.nextInt(2) * width / 2;
        }
        if (point[2] == 1 && point[1] >= -leaf.getHeight())
            canvas.drawBitmap(leaf, point[0], point[1], null);
        point[1] += riverspeed;
        if (point[1] > height) {
            point[0] = random.nextInt(2) * width / 2;
            point[1] = 1 + random.nextInt(24);
            point[1] *= -height / 12;
            if (point[2] == 0) pleaf++;
            point[2] = 1;
        }
        if (c % 20 == 0) {
            if (fpanda == 0) fpanda = 1;
            else fpanda = 0;
        }
        if (pandapower == 0) {
            if (fpanda == 0) canvas.drawBitmap(panda1, xpanda, 7 * height / 12, null);
            else canvas.drawBitmap(panda2, xpanda, 7 * height / 12, null);
        } else if (pandapower != 0) canvas.drawBitmap(power, xpanda, 7 * height / 12, null);
        c++;
        if (c > 10000) c = 0;
        if (viewcounter == 0) viewcounter++;

        if (defeat == 1) {
            i.putExtra("score", 5 * pleaf + wood);
            cont.startActivity(i);
            defeat = 2;
            Toast.makeText(cont, "Too bad, you Drowned", Toast.LENGTH_SHORT).show();
        }
        if (!back.isPlaying()) back.start();
        if (pandapower > 0) {
            pandapower -= riverspeed;
            if (pandapower < 0) pandapower = 0;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pandamove *= -1;
                if (x > 3 * width / 4 && y < height / 2 + width / 8 && y > height / 2 - width / 8 && pandapower == 0) {
                    pandapower = height / 3;
                } else if (x >= 0 && x < width && y > height / 4) {
                    if (pandamove == 0) {
                        if (xpanda >= 0 && xpanda < width / 2) pandamove = 1;
                        else pandamove = -1;
                    }
                } else if (x > 3 * width / 4 && y < height / 4) {
                    back.stop();
                    Intent in = new Intent(cont, MenuStart.class);
                    cont.startActivity(in);
                }
                break;
        }
        return true;
    }

}
