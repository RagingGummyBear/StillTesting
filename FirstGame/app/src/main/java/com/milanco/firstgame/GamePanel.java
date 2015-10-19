package com.milanco.firstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;

/**
 * Created by Milanco on 11.10.2015.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    public static double  WIDTH = 1080;
    public static double HEIGHT = 1820;
    private int savedState=0;
    private GameLoopThread thread;
    private Background bg;
    private Bitmap background;
    private float canvasXscale=1;
    private float canvasYscale=1;
    private float canvasXpivot=1;
    private float canvasYpivot=1;
    private MAZE maze;
    public GamePanel(Context context) {


        super(context);
        // add call back to the surfaceholder for event

        getHolder().addCallback(this);
       // thread = new MainThread(getHolder(), this);

        thread = new GameLoopThread(this);
        this.setFocusable(true);
        maze = new MAZE();

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

        //double width = GamePanel.WIDTH;
        //double height = GamePanel.HEIGHT;
        //
        //System.out.println("SUrfaceRezised YOOO MA NIGGAH"+" width: "+width+" height: "+ height);
        //this.WIDTH= width;
        //this.HEIGHT = height;


        maze.set_Tiles(BitmapFactory.decodeResource(getResources(), R.drawable.movabletile), BitmapFactory.decodeResource(getResources(), R.drawable.notmovabletile));
        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Rect src = new Rect(0, 0, temp.getWidth(), temp.getHeight());
        Rect dest = new Rect(0, 0, width, height);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(width, height, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(temp,src,dest,null);
        maze.render(canvas);
        canvas.save();
        bg = new Background(bmp);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch (Exception e){
                e.printStackTrace();
                retry = false;
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        //System.out.println("SUrfaceCREATED YOOO MA NIGGAH");


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        return super.onTouchEvent(event);
    }
    public void update(){

        if(canvasXscale<4)
        {
            canvasXscale+=0.001;
        }
        else
        {
            canvasXpivot+=0.5;
        }
        if(canvasYscale<4)
        {
            canvasYscale+=0.001;
        }
        else
        {
            canvasYpivot+=0.5;
        }
    }


    public void first_draw(Canvas c)
    {

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        //final float setScaleFactorX = (float)(getWidth()/WIDTH);
        //final float setScaleFactorY = (float)(getHeight()/HEIGHT);
        if(canvas != null) {
           // canvas.scale(1,1);
            canvas.scale(canvasXscale,canvasYscale,canvasXpivot,canvasYpivot);
            savedState = canvas.save();
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
