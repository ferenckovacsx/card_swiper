package ferenckovacsx.wup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";
    SharedPreferences sharedpreferences;

    APIInterface apiInterface;
    ArrayList<Card> listOfCards = new ArrayList<>();

    private BottomNavigationView bottomNavigationView;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    private SlidePagerAdapter pagerAdapter;
    public ProgressBar balanceChart;
    TextView availableBalanceTextView, availableBalanceLabel;
    ConstraintLayout staticElements;

    ProgressBarAnimation progressBarAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrofit
        APIClient apiClient = new APIClient(this);
        apiInterface = apiClient.getClient().create(APIInterface.class);

        sharedpreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //UI
        bottomNavigationView = findViewById(R.id.navigationView);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabDots);
        balanceChart = findViewById(R.id.balanceChart);
        availableBalanceTextView = findViewById(R.id.availableBalanceTextView);
        availableBalanceLabel = findViewById(R.id.availableLabel);
        staticElements = findViewById(R.id.staticViewGroup);

        staticElements.setVisibility(View.INVISIBLE);

        getListOfCards();

        //viewpager, tablayout (for the dots) and bottom navigation
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);
        bottomNavigationView.setLabelVisibilityMode(1);

    }

    void getListOfCards() {

        Call<ArrayList<Card>> call = apiInterface.list_cards();

        call.enqueue(new Callback<ArrayList<Card>>() {

            @Override
            public void onResponse(@NonNull Call<ArrayList<Card>> call, @NonNull retrofit2.Response<ArrayList<Card>> response) {

                int responseCode = response.code();

                Log.d(TAG, "response code: " + responseCode);

                switch (responseCode) {
                    case 200:
                        Log.i(TAG, "Fetching data was successful. Number of cards: " + response.body().size());

                        listOfCards = response.body();
                        pagerAdapter = new SlidePagerAdapter(MainActivity.this, listOfCards);
                        viewPager.addOnPageChangeListener(pageChangeListener);
                        pageChangeListener.onPageSelected(0);
                        viewPager.setAdapter(pagerAdapter);

                        break;

                    default:
                        Log.i(TAG, "Unkwonwn error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Card>> call, @NonNull Throwable t) {
                Log.e(TAG, "Get data error: " + t.toString());
                Toast.makeText(MainActivity.this, "Failed to retrieve data. Please check your connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            final int availableBalance, currentBalance, totalBalance;

            availableBalance = listOfCards.get(position).getAvailableBalance();
            currentBalance = listOfCards.get(position).getCurrentBalance();
            totalBalance = availableBalance + currentBalance;

            //place static elements (dots, chart) below card image. Value comes from PageAdapter
            staticElements.setVisibility(View.VISIBLE);
            staticElements.setY(424 + getStatusBarHeight());
            availableBalanceTextView.setText(String.valueOf(availableBalance));

            //reverse chart and setMax
            balanceChart.setRotation(180);
            balanceChart.setMax(totalBalance);

            //animate chart from previous balance to the current one. Animation starts from 0 as default
            int previousCardBalance = totalBalance * sharedpreferences.getInt("previousChartState", 0) / 100;
            progressBarAnimation = new ProgressBarAnimation(balanceChart, previousCardBalance, availableBalance);
            progressBarAnimation.setDuration(1000);
            balanceChart.startAnimation(progressBarAnimation);

            //save balance of previous card (for chart animation purposes)
            double fraction = (double)availableBalance/(double)totalBalance * 100;
            Log.d(TAG, "fraction: " + (int)fraction);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("previousChartState", (int)fraction);
            editor.apply();

            pagerAdapter.setOnDrawingReadyListener(new SlidePagerAdapter.OnDrawingReadyListener() {
                @Override
                public void onDrawingReady(int absolutePosition) {

                }
            });
        }
    };

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
