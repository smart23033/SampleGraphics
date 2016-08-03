package example.tacademy.com.samplegraphics;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = (CustomView)findViewById(R.id.custom_view);
        Button btn = (Button)findViewById(R.id.btn_change);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = ((BitmapDrawable) ContextCompat.getDrawable(MainActivity.this,R.drawable.sample_0)).getBitmap();
                customView.changeBitmap(bm);
            }
        });
    }
}
