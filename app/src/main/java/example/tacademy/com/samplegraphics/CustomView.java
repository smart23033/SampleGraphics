package example.tacademy.com.samplegraphics;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Tacademy on 2016-08-03.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context, null);
    }

    Paint mPaint;
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();

        initPoint();
        initPath();
        initBitmap();
    }

    Bitmap mBitmap;
    Matrix matrix;

    float[] meshVertices;

    private void initBitmap(){
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sample_0);
        matrix = new Matrix();
        matrix.reset();
        float dx = mBitmap.getWidth() / 4.0f;
        float height = mBitmap.getHeight();
        meshVertices = new float[]{
                0, 0,  dx , 50,  dx * 2 , 0 ,  dx * 3 , 50 , dx * 4 , 0,
                0, height, dx, height - 50, dx * 2, height, dx * 3, height - 50, dx * 4, height
        };
    }

    Path path, textPath;
    private void initPath() {
        path = new Path();
        path.moveTo(50, 50);
        path.lineTo(0, 0);
        path.lineTo(100, 0);
        path.lineTo(150, 50);
        path.lineTo(100, 100);
        path.lineTo(0, 100);

        path.addCircle( 300, 300, 100, Path.Direction.CCW);

        textPath = new Path();
        textPath.addCircle(300, 300, 150, Path.Direction.CW);
    }

    float[] points;
    private static final float START = 0, END = 600, INTERVAL = 20;
    private void initPoint(){
        int count = (int)((END - START) / INTERVAL) + 1;
        points = new float[count * 2 * 2];

        for(int i = 0;i < count;i++){
            points[i * 4 + 0] = START;
            points[i * 4 + 1] = START + i * INTERVAL;
            points[i * 4 + 2] = END - i * INTERVAL;
            points[i * 4 + 3] = START;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.LTGRAY);

         canvas.save();
        canvas.translate(x,y);
//        drawLineAndPoint(canvas);
//        drawRect(canvas);
//        drawCircle(canvas);
//        drawPath(canvas);
//        drawText(canvas);
//        drawBitmap(canvas);
        drawBitmapMesh(canvas);

        canvas.restore();
    }
    private void drawBitmapMesh(Canvas canvas) {
        canvas.drawBitmapMesh(mBitmap, 4, 1, meshVertices, 0, null, 0, mPaint);
    }
    private void drawBitmap(Canvas canvas) {
//        matrix.setTranslate(100, 100);
//        matrix.postRotate(45, 100, 100);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        matrix.setScale(1, -1, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        matrix.postSkew(1, 0);
        matrix.postTranslate(0, mBitmap.getHeight());
        canvas.drawBitmap(mBitmap, matrix, mPaint);
    }
    String text = "Hello! Android g";
    float hOffset = 0;
    private void drawText(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);
//        canvas.drawText(text, 0, 30, mPaint);
        canvas.drawTextOnPath(text, textPath, hOffset, 0, mPaint);

        hOffset += 5;
        invalidate();
    }

    private void drawPath(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawPath(path, mPaint);
    }

    private void drawRect(Canvas canvas) {
        RectF rect = new RectF(0, 0, 300, 300);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect, mPaint);

        RectF roundRect = new RectF(0, 400, 300, 700);
        mPaint.setColor(Color.GREEN);
        canvas.drawRoundRect(roundRect, 30, 50, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(150, 150, 150, mPaint);

        RectF arcRect = new RectF(400, 0, 700, 300);
        mPaint.setColor(Color.RED);
        canvas.drawArc(arcRect, 45, 90, false, mPaint);

        mPaint.setColor(Color.CYAN);
        RectF rect = new RectF(0, 400, 600, 700);
        canvas.drawOval(rect, mPaint);
    }

    private void drawLineAndPoint(Canvas canvas) {

        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        canvas.drawLines(points, mPaint);
        mPaint.setAntiAlias(false);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        canvas.drawPoints(points, mPaint);

    }

    float x = 0, y = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN :
                return true;
            case MotionEvent.ACTION_UP :
                x = event.getX();
                y = event.getY();
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }
}
