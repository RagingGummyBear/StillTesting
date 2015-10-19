package com.milanco.firstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Darko on 19.10.2015.
 */
public class Tile {
    boolean movable;
    Bitmap img;

    public Tile(boolean b)
    {
        movable=b;
    }

    public void set_img(Bitmap m,Bitmap n)
    {
        if(movable)
        {
            img = m;
        }
        else
            img = n;
    }
    public  void render(Canvas c,int x,int y,int size)
    {

        int width = size;
        int height = size;
        Rect src = new Rect(0,0,img.getWidth(), img.getHeight());
        Rect dest = new Rect(x,y,x+size,y+size);
        c.drawBitmap(img, src, dest, null);
        //c.drawBitmap(img,x,y,null);

    }

}
