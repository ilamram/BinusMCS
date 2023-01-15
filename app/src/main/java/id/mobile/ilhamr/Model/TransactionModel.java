package id.mobile.ilhamr.Model;

public class TransactionModel {
    
    String transactionQty, transactionID, movieID, userID;

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMovieQty() {
        return transactionQty;
    }

    public void setMovieQty(String transactionQty) {
        this.transactionQty = transactionQty;
    }

    public String getTransactionId() {
        return transactionID;
    }

    public void setTransactionId(String transactionID) {
        this.transactionID = transactionID;
    }


}
