package com.example.question3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private Button changeAnimationButton;

    private String[] animations = {
            "Animation - 1744645458838.json",
            "heart.json"
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        changeAnimationButton = findViewById(R.id.changeAnimationButton);

        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();

        changeAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % animations.length;
                lottieAnimationView.setAnimation(animations[currentIndex]);
                lottieAnimationView.playAnimation();
            }
        });
    }
}
