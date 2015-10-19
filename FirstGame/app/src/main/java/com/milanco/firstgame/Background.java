package com.milanco.firstgame;

/**
 * Created by Milanco on 11.10.2015.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
    private Bitmap image;
    private boolean once = true;

    public Background(Bitmap res) {


        image = res;

    }
    public void update(){


    }
    public void draw(Canvas canvas){
/*
            double width = GamePanel.WIDTH;
            double height = GamePanel.HEIGHT;
            Rect src = new Rect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
            Rect dest = new Rect(0, 0, (int) width - 1, (int) height - 1);
            canvas.drawBitmap(image, src, dest, null);
            */
        canvas.drawBitmap(image,0,0,null);
    }
}
