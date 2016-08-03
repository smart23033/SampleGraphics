package example.tacademy.com.samplegraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-03.
 */
public class PathPaintView extends View {
    public PathPaintView(Context context) {
        super(context, null);
    }

    public PathPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
    }

    PathDrawItem currentItem;
    Paint paint;

    List<PathDrawItem> items = new ArrayList<>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        for(PathDrawItem item: items){
            canvas.drawPath(item.path,item.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                currentItem = new PathDrawItem();
                currentItem.path = new Path();
                currentItem.paint = new Paint(paint);
                currentItem.path.moveTo(event.getX(),event.getY());
                items.add(currentItem);
            case MotionEvent.ACTION_MOVE:
                currentItem.path.lineTo(event.getX(),event.getY());
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                currentItem = null;
                return true;
        }
        return super.onTouchEvent(event);
    }
}
