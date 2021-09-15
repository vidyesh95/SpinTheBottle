package com.vidyesh.spinthebottle;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private final Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // For edge to edge display
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        bottle = findViewById(R.id.bottle);
        bottle.setOnClickListener(v -> {
            if (!spinning) {
                // final direction of bottle
                int newDir = random.nextInt(1800);
                // duration
                int dur = random.nextInt(1000) + 1000;
                // half the width of bottle image i.e x-axis
                float pivotX = bottle.getWidth() / 2;
                // half the height of bottle image i.e. y-axis
                float pivotY = bottle.getHeight() / 2;
                Animation animation = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
                animation.setDuration(dur);
                // we want to keep animation as it is after animation change
                animation.setFillAfter(true);
                //
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        spinning = true;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        spinning = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                lastDir = newDir;
                bottle.startAnimation(animation);
            }
        });
    }
}
