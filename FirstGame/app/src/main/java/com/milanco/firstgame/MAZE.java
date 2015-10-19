package com.milanco.firstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Darko on 19.10.2015.
 */
public class MAZE {

    Random rand=new Random();
    public static double WIDTHSTART = (GamePanel.WIDTH*0.05);
    public static double WIDTHEND = (GamePanel.WIDTH * 1);
    public static double HEIGHTSTART = (GamePanel.HEIGHT*0.05);
    public static double HEIGHTEND = (GamePanel.HEIGHT*1);
    Tile tiles[][] = new Tile[120][120];
    public double tileSize;

    public MAZE(Tile[][] tiles)
    {
        this.tiles=tiles;
    }

    public MAZE()
    {
        if(WIDTHEND>HEIGHTEND)
        {
            tileSize = HEIGHTEND-HEIGHTSTART;
        }
        else
        {
            tileSize = WIDTHEND-WIDTHSTART;
        }

        for(int i = 0; i < 120; i++)
        {
            for(int y=0; y < 120; y++)
            {
                if(i<25)
                {
                    tiles[i][y]= new Tile(false);
                }
                else if(i>93)
                {
                    tiles[i][y] = new Tile(false);
                }
                else
                if(y<25) tiles[i][y] = new Tile(false);
                else

                if(y>93)
                {
                    tiles[i][y] = new Tile(false);
                }
                else if(rand.nextInt(4-0+1)==1)
                {
                    tiles[i][y]=new Tile(false);
                }
                else
                {
                    tiles[i][y]=new Tile(true);
                }
            }
        }
    }
        public  void set_Tiles(Bitmap m,Bitmap n)
        {
            for(int i=0;i<120;i++)
            {
                for(int y=0;y<120;y++)
                {

                        tiles[i][y].set_img(m,n);
                }
            }
        }


    public void render (Canvas c)
    {
        int temp1=(int)this.HEIGHTSTART;
        int temp2=(int)this.WIDTHSTART;
        float size = (int)tileSize/120;
      //  float size = 10;
            for(int i=0;i<120;i++)
            {
                for(int y=0;y<120;y++)
                {
                    tiles[i][y].render(c,temp1,temp2,(int)size);
                    temp2+=size;
                }
                temp2=(int)this.WIDTHSTART;
                temp1+=size;
            }
    }








}
