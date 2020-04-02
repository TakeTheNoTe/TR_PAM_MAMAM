package edu.ftiuksw.tr_pam_mamam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicators;
    private MaterialButton buttonOnBoardAction;
    private long BackPressedTime;
    private Toast BackToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnBoardingIndicators = findViewById(R.id.LayoutOnBoardingIndicator);
        buttonOnBoardAction = findViewById(R.id.BtnOnBoardingAction);

        setOnBoardingItem();

        final ViewPager2 onboardingViewPager = findViewById(R.id.OnBoardingViewPager);
        onboardingViewPager.setAdapter(onBoardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnBoardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

        buttonOnBoardAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem()+1<onBoardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    overridePendingTransition(R.anim.jumpin,R.anim.jumpout);
                    finish();
                }
            }
        });
    }

    private void setOnBoardingItem(){
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem food1 = new OnBoardingItem();
        food1.setTittle("NASI KACANG GAK JELAS!");
        food1.setDescription("Dijamin enak banget!");
        food1.setImage(R.drawable.mam1);

        OnBoardingItem food2 = new OnBoardingItem();
        food2.setTittle("MAKANAN HITS BRO BRO!");
        food2.setDescription("Sesuai selera!");
        food2.setImage(R.drawable.mam2);

        OnBoardingItem food3 = new OnBoardingItem();
        food3.setTittle("BUKAN INDOMIE!");
        food3.setDescription("Selera ku~!");
        food3.setImage(R.drawable.mam3);

        onBoardingItems.add(food1);
        onBoardingItems.add(food2);
        onBoardingItems.add(food3);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),R.drawable.onboarding_indicator_active
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnBoardingIndicator(int index){
        int childout = layoutOnBoardingIndicators.getChildCount();
        for (int i = 0; i< childout;i++){
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onBoardingAdapter.getItemCount()-1){
            buttonOnBoardAction.setText("Login");
        }else {
            buttonOnBoardAction.setText("Next");
        }
    }
    @Override
    public void onBackPressed() {
        minimizeApp();
    }
    
    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
