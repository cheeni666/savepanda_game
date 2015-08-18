package com.myprey.savepanda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//import com.myprey.game.R;

public class MenuDraw extends View {
    Context cont;
    Paint brush = new Paint();
    Intent i;
    Rect screen, bounds1, bounds2, bounds3;
    float width, height;
    Canvas refCanvas;
    MediaPlayer click;
    int State = 0;
    float xl1, xr1, yt1, yb1;
    float xl2, xr2, yt2, yb2;
    float xl3, xr3, yt3, yb3;
    Bitmap background;
    int viewcounter = 0;

    public MenuDraw(Context context) {
        super(context);
        cont = context;
    }

    public MenuDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        cont = context;
    }

    public MenuDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cont = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((xl1 < x && x < xr1) && (yb1 < y && y < yt1)) {
                    click.start();
                    i = new Intent(cont, Game.class);
                    cont.startActivity(i);
                }
                if ((xl2 < x && x < xr2) && (yb2 < y && y < yt2)) {
                    click.start();
                    i = new Intent(cont, Highscore.class);
                    cont.startActivity(i);
                }
                if ((xl3 < x && x < xr3) && (yb3 < y && y < yt3)) {
                    click.start();
                    Intent i = new Intent(cont, MenuStart.class);
                    i.putExtra("EXIT", true);
                    cont.startActivity(i);
                }
                if ((width / 2 - bounds1.width() / 2 < x && x < width / 2 + bounds1.width() / 2) && (height - bounds1.height() < y && y < height)) {
                    click.start();
                    i = new Intent(cont, Dialog.class);
                    cont.startActivity(i);
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refCanvas = canvas;
        if (viewcounter == 0) {
            width = canvas.getWidth();
            height = canvas.getHeight();
            background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pandaback), (int) width, (int) height, false);
            click = MediaPlayer.create(cont, R.raw.bubble);
        }
        if (State == 0) {

            canvas.drawBitmap(background, 0, 0, null);
            brush.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
            brush.setColor(Color.BLUE);
            brush.setTextSize(height / 12);
            refCanvas.drawText("\'SAVE\'Mr.P", 0, height / 10, brush);
            brush.setTextSize(height / 10);
            brush.setColor(Color.MAGENTA);
            if (viewcounter == 0) {
                bounds1 = new Rect();
                brush.getTextBounds("PLAY", 0, 4, bounds1);
                xl1 = (int) width / 2 - bounds1.width() / 2;
                xr1 = xl1 + bounds1.width();
                yt1 = 2 * height / 5;
                yb1 = yt1 - bounds1.height();

                bounds2 = new Rect();
                brush.getTextBounds("HIGHSCORES", 0, 10, bounds2);
                xl2 = (int) width / 2 - bounds2.width() / 2;
                xr2 = xl2 + bounds2.width();
                yt2 = 3 * height / 5;
                yb2 = yt2 - bounds2.height();

                bounds3 = new Rect();
                brush.getTextBounds("EXIT", 0, 4, bounds3);
                xl3 = (int) width / 2 - bounds1.width() / 2;
                xr3 = xl3 + bounds3.width();
                yt3 = 4 * height / 5;
                yb3 = yt3 - bounds3.height();
            }
            refCanvas.drawText("PLAY", (int) width / 2 - bounds1.width() / 2, 2 * height / 5, brush);

            refCanvas.drawText("HIGHSCORES", (int) width / 2 - bounds2.width() / 2, 3 * height / 5, brush);

            refCanvas.drawText("EXIT", (int) width / 2 - bounds1.width() / 2, 4 * height / 5, brush);

            refCanvas.drawText("HELP", (int) width / 2 - bounds1.width() / 2, height, brush);

            if (viewcounter == 0) viewcounter++;
            //invalidate();
        }
    }
}
