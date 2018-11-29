package ferenckovacsx.wup;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    final private String TAG = "SlidePagerAdapter";

    private Context context;
    private List<Card> cardList;
    private LayoutInflater layoutInflater;
    ImageView cardImageView;
    private TextView cardNameTv, currentBalanceTv, currentBalanceCurrencyTv, minPaymentTv, minPaymentCurrencyTv, dueDateTv;

    private OnDrawingReadyListener mOnDrawingReadyListener;

    SlidePagerAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
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


        //UI
        cardImageView = view.findViewById(R.id.cardImageView);
        cardNameTv = view.findViewById(R.id.cardNameTextView);
        currentBalanceTv = view.findViewById(R.id.currentBalanceTextView);
        currentBalanceCurrencyTv = view.findViewById(R.id.currentBalanceCurrencyTextView);
        minPaymentTv = view.findViewById(R.id.minPaymentTextView);
        minPaymentCurrencyTv = view.findViewById(R.id.minPaymentCurrencyTextView);
        dueDateTv = view.findViewById(R.id.dueDateTextView);

        //set card imageview based on "cardImage" property
        switch (this.cardList.get(position).getCardImage()) {
            case ("1"):
                cardImageView.setImageResource(R.drawable.cccard);
                break;
            case ("2"):
                cardImageView.setImageResource(R.drawable.cccard2);
                break;
            case ("3"):
                cardImageView.setImageResource(R.drawable.cccard3);
                break;
        }

        //populating textviews with values
        cardNameTv.setText(this.cardList.get(position).getFriendlyName());
        currentBalanceTv.setText(String.valueOf(this.cardList.get(position).getCurrentBalance()));
        currentBalanceCurrencyTv.setText(this.cardList.get(position).getCurrency());
        minPaymentTv.setText(String.valueOf(this.cardList.get(position).getMinPayment()));
        minPaymentCurrencyTv.setText(this.cardList.get(position).getCurrency());
        dueDateTv.setText(this.cardList.get(position).getDueDate());

        container.addView(view);

        //This code detects the absolute position and size of the card ImageView on the screen (in pixels).
        //We need this information to correctly position the indicator dots and the chart (they should remain static when swiping through pages)
        cardImageView.post(new Runnable() {
            @Override
            public void run() {
                mOnDrawingReadyListener.onDrawingReady(getAbsolutePosition());
            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    //listener for
    public interface OnDrawingReadyListener {
        void onDrawingReady(int position);
    }

    public void setOnDrawingReadyListener(OnDrawingReadyListener onDrawingReadyListener) {
        this.mOnDrawingReadyListener = onDrawingReadyListener;
    }

    //This method calculates the absolute location on screen where the static/overlaying elements should be placed (dots, chart)
    //The location will be calculated based on the Y position of the imageview and its height
    int getAbsolutePosition(){
        int[] imageViewLocation = new int[2];
        cardImageView.getLocationOnScreen(imageViewLocation);
        Log.d(TAG, "ImageView x coord: " + imageViewLocation[0]);
        Log.d(TAG, "ImageView y coord: " + imageViewLocation[1]);
        Log.d(TAG, "ImageView height: " + cardImageView.getHeight());

        return cardImageView.getHeight()/2 + imageViewLocation[1];

    }
}