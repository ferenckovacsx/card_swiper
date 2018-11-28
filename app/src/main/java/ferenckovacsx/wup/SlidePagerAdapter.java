package ferenckovacsx.wup;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static ferenckovacsx.wup.MainActivity.balanceChart;

public class SlidePagerAdapter extends PagerAdapter {

    final String TAG = "SlidePagerAdapter";

    private Context context;
    private List<Card> cardList;
    private LayoutInflater layoutInflater;
    private ObjectAnimator progressAnimator;
    ProgressBarAnimation progressBarAnimation;

    int availableBalance, currentBalance, totalBalance;

    SlidePagerAdapter(Context context, List<Card> cardList) {
        Log.d(TAG, "2");
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "1");

        return cardList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = this.layoutInflater.inflate(R.layout.fragment_card, container, false);

        Log.d(TAG, String.valueOf(getCount()));

        ImageView displayImage = view.findViewById(R.id.cardImageView);
        TextView cardName = view.findViewById(R.id.cardNameTextView);
//        TabLayout tabLayout = view.findViewById(R.id.tabDots);
//        ProgressBar balanceChart = view.findViewById(R.id.balanceChart);

        switch (this.cardList.get(position).getCardImage()) {
            case ("1"):
                Log.d(TAG, "Card image 1");
                displayImage.setImageResource(R.drawable.cccard);
                break;
            case ("2"):
                Log.d(TAG, "Card image 2");
                displayImage.setImageResource(R.drawable.cccard2);
                break;
            case ("3"):
                Log.d(TAG, "Card image 3");
                displayImage.setImageResource(R.drawable.cccard3);
                break;
        }

        cardName.setText(this.cardList.get(position).getFriendlyName());

//        progressAnimator = ObjectAnimator.ofInt(balanceChart, "progress", 0, this.cardList.get(position).getAvailableBalance());
//        progressAnimator.setDuration(1500);
//        progressAnimator.start();

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    void animateChart(int position){

        availableBalance = this.cardList.get(position).getAvailableBalance();
        currentBalance = this.cardList.get(position).getCurrentBalance();
        totalBalance = availableBalance + currentBalance;

        balanceChart.setRotation(180);
        balanceChart.setMax(totalBalance);
        progressBarAnimation= new ProgressBarAnimation(balanceChart, 0, availableBalance);
        progressBarAnimation.setDuration(1000);
        balanceChart.startAnimation(progressBarAnimation);
    }

}