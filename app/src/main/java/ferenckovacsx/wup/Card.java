package ferenckovacsx.wup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("cardId")
    @Expose
    private String cardId;

    @SerializedName("issuer")
    @Expose
    private String issuer;

    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;

    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;

    @SerializedName("cardHolderName")
    @Expose
    private String cardHolderName;

    @SerializedName("friendlyName")
    @Expose
    private String friendlyName;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("cvv")
    @Expose
    private String cvv;

    @SerializedName("availableBalance")
    @Expose
    private int availableBalance;

    @SerializedName("currentBalance")
    @Expose
    private int currentBalance;

    @SerializedName("minPayment")
    @Expose
    private int minPayment;

    @SerializedName("dueDate")
    @Expose
    private String dueDate;

    @SerializedName("reservations")
    @Expose
    private int reservations;

    @SerializedName("balanceCarriedOverFromLastStatement")
    @Expose
    private int balanceCarriedOverFromLastStatement;

    @SerializedName("spendingsSinceLastStatement")
    @Expose
    private int spendingsSinceLastStatement;

    @SerializedName("yourLastRepayment")
    @Expose
    private String yourLastRepayment;

    @SerializedName("accountDetails")
    @Expose
    private AccountDetails accountDetails;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("cardImage")
    @Expose
    private String cardImage;

    public Card(String cardId, String issuer, String cardNumber, String expirationDate, String cardHolderName, String friendlyName, String currency, String cvv, int availableBalance, int currentBalance, int minPayment, String dueDate, int reservations, int balanceCarriedOverFromLastStatement, int spendingsSinceLastStatement, String yourLastRepayment, AccountDetails accountDetails, String status, String cardImage) {
        this.cardId = cardId;
        this.issuer = issuer;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.friendlyName = friendlyName;
        this.currency = currency;
        this.cvv = cvv;
        this.availableBalance = availableBalance;
        this.currentBalance = currentBalance;
        this.minPayment = minPayment;
        this.dueDate = dueDate;
        this.reservations = reservations;
        this.balanceCarriedOverFromLastStatement = balanceCarriedOverFromLastStatement;
        this.spendingsSinceLastStatement = spendingsSinceLastStatement;
        this.yourLastRepayment = yourLastRepayment;
        this.accountDetails = accountDetails;
        this.status = status;
        this.cardImage = cardImage;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(int minPayment) {
        this.minPayment = minPayment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getBalanceCarriedOverFromLastStatement() {
        return balanceCarriedOverFromLastStatement;
    }

    public void setBalanceCarriedOverFromLastStatement(int balanceCarriedOverFromLastStatement) {
        this.balanceCarriedOverFromLastStatement = balanceCarriedOverFromLastStatement;
    }

    public int getSpendingsSinceLastStatement() {
        return spendingsSinceLastStatement;
    }

    public void setSpendingsSinceLastStatement(int spendingsSinceLastStatement) {
        this.spendingsSinceLastStatement = spendingsSinceLastStatement;
    }

    public String getYourLastRepayment() {
        return yourLastRepayment;
    }

    public void setYourLastRepayment(String yourLastRepayment) {
        this.yourLastRepayment = yourLastRepayment;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }
}
