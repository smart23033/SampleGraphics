package example.tacademy.com.samplegraphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceActivity extends AppCompatActivity
implements SurfaceHolder.Callback{

    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        isRunning = true;
        new Thread(drawRunnable).start();
    }

    boolean isRunning = false;
    Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            while(isRunning){
                if(mHolder != null){
                    Canvas canvas = null;
                    try{
                        canvas = mHolder.lockCanvas();
                        draw(canvas);
                    }catch (Exception e){

                    }finally {
                        if(canvas != null){
                            mHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    Paint mPaint = new Paint();

    float startX = 0, endX = 300, startY = 300, endY = 0;
//    메인윈도우의 캔버스는 onDraw
    private void draw(Canvas canvas){
        canvas.drawColor(Color.LTGRAY);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        startY -= 5;
        if (startY < 0) {
            startY = 300;
        }
        endX -= 5;
        if (endX < 0) {
            endX = 300;
        }
    }

    SurfaceHolder mHolder;
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mHolder = null;
    }
}
