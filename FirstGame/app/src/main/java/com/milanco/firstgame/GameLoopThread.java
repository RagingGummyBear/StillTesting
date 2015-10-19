package com.milanco.firstgame;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {
    static final long FPS = 60;
    private GamePanel view;
    private double averageFPS;
    private boolean running = false;
    boolean once=true;

    public GameLoopThread(GamePanel view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        long timer2=timer;
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            while(delta>=1)
            {
                tick();
                delta--;
            }
            if(running)
               if(once) {
                   //once=true;
                   render();
               }
            frames++;

            if(System.currentTimeMillis()-timer>1000)
            {

                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
            if(System.currentTimeMillis()-timer2>300)
            {
                timer+=300;
                //handler.tickSec();
            }
        }

        /*
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        long totalTime = 0;
        int frameCount =0;
        while (running) {

            Canvas c = null;
            startTime = System.nanoTime();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.draw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                System.out.println(totalTime+" total time");
                System.out.println(frameCount+ " framecount");
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }*/
    }
    void tick()
    {
        view.update();
    }
    void render()
    {
        Canvas c = null;
        try {
            c = view.getHolder().lockCanvas();
            synchronized ( view.getHolder()) {
                if( c != null )view.draw(c);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }
    }
}