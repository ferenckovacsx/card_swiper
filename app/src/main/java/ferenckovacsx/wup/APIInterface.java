package ferenckovacsx.wup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {

    @GET("card")
    Call<ArrayList<Card>> list_cards();

}
