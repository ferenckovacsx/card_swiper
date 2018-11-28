package ferenckovacsx.wup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDetails {

    @SerializedName("accountLimit")
    @Expose
    private int accountLimit;

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
}
