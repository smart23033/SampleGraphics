package example.tacademy.com.samplegraphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Tacademy on 2016-08-03.
 */
public class PaintView extends View {

    public PaintView(Context context) {
        super(context,null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mRealPaint = new Paint();
    }

    Paint mPaint, mRealPaint;
    Canvas offScreenCanvas;
    Bitmap offScreenBitmap;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width = right - left - (getPaddingLeft() + getPaddingRight());
        int height = bottom - top - (getPaddingTop() + getPaddingBottom());
        if(width != 0 && height != 0){
            if(offScreenBitmap == null){
                offScreenBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
                offScreenCanvas = new Canvas(offScreenBitmap);
            }
            if(width != offScreenBitmap.getWidth() || height != offScreenBitmap.getHeight()){
                Bitmap bm = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
                Canvas cn = new Canvas(bm);
                cn.drawBitmap(offScreenBitmap,0,0,mPaint);
                offScreenBitmap.recycle();
                offScreenBitmap = bm;
                offScreenCanvas = cn;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(offScreenBitmap != null){
            canvas.drawBitmap(offScreenBitmap,0,0,mRealPaint);
        }
    }

    float oldX, oldY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                offScreenCanvas.drawLine(oldX,oldY,event.getX(),event.getY(),mPaint);
                oldX = event.getX();
                oldY = event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                oldX = 0;
                oldY = 0;
                return true;
        }
        return super.onTouchEvent(event);
    }
}
