package ferenckovacsx.wup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";

    APIInterface apiInterface;
    ArrayList<Card> listOfCards = new ArrayList<>();

    private BottomNavigationView bottomNavigationView;
    public static ViewPager viewPager;
    public TabLayout tabLayout;
    private SlidePagerAdapter pagerAdapter;
    public static ProgressBar balanceChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrofit
        APIClient apiClient = new APIClient(this);
        apiInterface = apiClient.getClient().create(APIInterface.class);

        bottomNavigationView = findViewById(R.id.navigationView);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabDots);
        balanceChart = findViewById(R.id.progressBar);

        getListOfCards();

        viewPager.setOffscreenPageLimit(0);
        bottomNavigationView.setLabelVisibilityMode(1);
        tabLayout.setupWithViewPager(viewPager);

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
        public void onPageScrollStateChanged(int arg0) { }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }

        @Override
        public void onPageSelected(int position) {pagerAdapter.animateChart(position);
        }
    };
}
