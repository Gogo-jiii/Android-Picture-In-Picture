package com.example.pictureinpicture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnPictureInPicture;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPictureInPicture = findViewById(R.id.btnPictureInPicture);
        imageView = findViewById(R.id.imageView);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_android));
        btnPictureInPicture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O) @Override public void onClick(View v) {
                startPictureInPicture();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O) private void startPictureInPicture() {
        Display d = getWindowManager()
                .getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        int width = p.x;
        int height = p.y;

        Rational ratio = new Rational(width, height);

        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        builder.setAspectRatio(ratio);
        enterPictureInPictureMode(builder.build());
    }

    @Override public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        if(isInPictureInPictureMode){
            // Hide the full-screen UI (controls, etc.) while in picture-in-picture mode.
            Log.d("TAG","isInPictureInPictureMode");
        }else {
            // Restore the full-screen UI.
            Log.d("TAG","is NOT InPictureInPictureMode");
        }
    }
}