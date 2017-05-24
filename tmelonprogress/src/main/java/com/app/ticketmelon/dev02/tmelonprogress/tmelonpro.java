package com.app.ticketmelon.dev02.tmelonprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class tmelonpro extends View {
    private Handler mHandler = new Handler();
    float r = 999;
    float center =0;
    float centeri = 0;
    long speed = 5;
    Thread thr;
    runnn m;
    public tmelonpro(Context context) {
        super(context);
        init(null, 0);
    }

    public tmelonpro(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public tmelonpro(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.tmelonpro, defStyle, 0);



        a.recycle();
        m = new runnn();
        //mHandler2.postDelayed(m,20);
        thr = new Thread(m);

    }



    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);


        if (r == 999){
            Progress.drawCanvas1(canvas,new RectF(0,0,getWidth(),getHeight()), Progress.ResizingBehavior.AspectFit,0,0);




            r = 0;
        }else{
            Progress.drawCanvas1(canvas,new RectF(0,0,getWidth(),getHeight()), Progress.ResizingBehavior.AspectFit,center,r);
        }

    }
    public void start(){
        r = 0;
        centeri = 0;
        setVisibility(VISIBLE);
        if(!m.isRunning()) {
            m.running = true;
            thr.start();
        }



        //thr.start();
    }
    public void stop(){
        if(m.isRunning()) {
            m.running = false;
        }
        setVisibility(GONE);
    }
    public void setSpeed(long speed){

    }
    public class runnn implements Runnable {


        public volatile boolean running = false;

        public boolean isRunning() {
            return running;
        }

        @Override
        public void run() {


            while (running) {
                mHandler.post(new Runnable() {
                    public void run() {
                        r = ((r+1) % 360);
                        if (r % 7 == 0){
                            center = Math.abs(((10 - (centeri+10)%(10*2))));
                            centeri++;
                        }
                        invalidate();
                    }
                });
                try {
                    Thread.sleep(speed);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
