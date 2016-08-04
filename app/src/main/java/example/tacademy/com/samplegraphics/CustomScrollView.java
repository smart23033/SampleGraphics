package example.tacademy.com.samplegraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.OverScroller;

/**
 * Created by Tacademy on 2016-08-04.
 */
public class CustomScrollView extends View{
    public CustomScrollView(Context context) {
        super(context,null);
    }

    Drawable mImage;
    OverScroller mScroller;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mImage = ContextCompat.getDrawable(context,R.drawable.sample_0);
        mImage.setBounds(0,0,mImage.getIntrinsicWidth(),mImage.getIntrinsicHeight());
        mImage.setCallback(this);
        mScroller = new OverScroller(context);
    }

    private static final int DELTA = 100;
    public void moveLeft(){
        mScroller.forceFinished(true);
        int x = getScrollX();
        int y = getScrollY();
        mScroller.startScroll(x,y,100,0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            scrollTo(x,y);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mImage.draw(canvas);
    }
}
