package example.tacademy.com.samplegraphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScrollActivity extends AppCompatActivity {

    CustomScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        scrollView = (CustomScrollView)findViewById(R.id.custom_view);
        Button btn = (Button)findViewById(R.id.btn_scroll);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.moveLeft();
            }
        });
    }
}
