package id.mobile.ilhamr;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import id.mobile.ilhamr.Model.MovieVolleyModel.FilmsItem;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.Model.UserModel;

public class DBManager extends SQLiteOpenHelper {

    Context context;
    static final String DATABASE_NAME = "Ilham's Database";
    static final int DATABASE_VERSION = 1;
    String TABLE_NAME = "user_table";
    String TABLE_FILM_NAME = "film_table";
    String TABLE_TRANSACTION_NAME = "transaction_table";
    String ID_COL = "user_id";
    String NAME_COl = "name";
    String PASS_COL = "password";
    String PHONE_NUMBER_COL = "phone_number";
    String EMAIL_COL = "email";
    String MOVIE_ID_COL = "movie_id";
    String MOVIE_NAME_COL = "movie_name";
    String MOVIE_PRICE_COL = "movie_price";
    String MOVIE_RATING_COL = "movie_rating";
    String MOVIE_COUNTRY_COL = "movie_country";
    String MOVIE_DESCRIPTION_COL = "movie_description";
    String MOVIE_IMAGE_COL = "movie_image";
    String TRANSACTION_ID_COL = "transaction_id";
    String QUANTITY_COL = "quantity";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE " + TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                PASS_COL + " TEXT," +
                EMAIL_COL + " TEXT," +
                PHONE_NUMBER_COL + " TEXT" + ")");
        String queryFilms = ("CREATE TABLE " + TABLE_FILM_NAME + " (" +
                MOVIE_ID_COL + " INTEGER PRIMARY KEY, " +
                MOVIE_NAME_COL + " TEXT," +
                MOVIE_PRICE_COL + " TEXT," +
                MOVIE_RATING_COL + " TEXT," +
                MOVIE_COUNTRY_COL + " TEXT," +
                MOVIE_DESCRIPTION_COL + " TEXT," +
                MOVIE_IMAGE_COL + " TEXT" + ")");
        String queryTransactions = ("CREATE TABLE " + TABLE_TRANSACTION_NAME + " (" +
                TRANSACTION_ID_COL + " INTEGER PRIMARY KEY, " +
                ID_COL + " TEXT," +
                MOVIE_ID_COL + " TEXT," +
                QUANTITY_COL + " TEXT" + ")");
        db.execSQL(query);
        db.execSQL(queryFilms);
        db.execSQL(queryTransactions);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void saveTransaction(String quantity, String userId, String movieId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUANTITY_COL, quantity);
        values.put(ID_COL, userId);
        values.put(MOVIE_ID_COL, movieId);
        db.insert(TABLE_TRANSACTION_NAME, null, values);
        db.close();
    }

    public void saveMovie(String movieName, String moviePrice, String movieRating, String movieCountry, String movieDescription, String movieImage) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_NAME_COL, movieName);
        values.put(MOVIE_PRICE_COL, moviePrice);
        values.put(MOVIE_RATING_COL, movieRating);
        values.put(MOVIE_COUNTRY_COL, movieCountry);
        values.put(MOVIE_DESCRIPTION_COL, movieDescription);
        values.put(MOVIE_IMAGE_COL, movieImage);
        db.insert(TABLE_FILM_NAME, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public ArrayList<FilmsItem> retrieveMovieList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FILM_NAME,null);
        ArrayList<FilmsItem> filmsItemList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                FilmsItem filmsItem = new FilmsItem();
                filmsItem.setId(cursor.getString(cursor.getColumnIndex(MOVIE_ID_COL)));
                filmsItem.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_NAME_COL)));
                filmsItem.setRating(cursor.getString(cursor.getColumnIndex(MOVIE_RATING_COL)));
                filmsItem.setPrice(cursor.getString(cursor.getColumnIndex(MOVIE_PRICE_COL)));
                filmsItem.setCountry(cursor.getString(cursor.getColumnIndex(MOVIE_COUNTRY_COL)));
                filmsItem.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION_COL)));
                filmsItem.setImage(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_COL)));
                filmsItemList.add(filmsItem);
            } while (cursor.moveToNext());
        }
        return filmsItemList;
    }

    @SuppressLint("Range")
    public ArrayList<TransactionModel> retrieveTransactionDatabyID(int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRANSACTION_NAME + " WHERE " + ID_COL + " = '" + userId +"' ",null);
        ArrayList<TransactionModel> transactionModelArrayList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    TransactionModel transactionModel = new TransactionModel();
                    transactionModel.setMovieQty(cursor.getString(cursor.getColumnIndex(QUANTITY_COL)));
                    transactionModel.setTransactionId(cursor.getString(cursor.getColumnIndex(TRANSACTION_ID_COL)));
                    transactionModel.setMovieID(cursor.getString(cursor.getColumnIndex(MOVIE_ID_COL)));
                    transactionModel.setUserID(cursor.getString(cursor.getColumnIndex(ID_COL)));
                    transactionModelArrayList.add(transactionModel);
                }while(cursor.moveToNext());
            }
        return transactionModelArrayList;
    }

    @SuppressLint("Range")
    public FilmsItem getFilmsDataById(String filmId){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + MOVIE_ID_COL + " = '" + filmId +"' ", null);
        FilmsItem filmsItem = new FilmsItem();
        if(cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                filmsItem.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_NAME_COL)));
                filmsItem.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION_COL)));
                filmsItem.setImage(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_COL)));
                filmsItem.setRating(cursor.getString(cursor.getColumnIndex(MOVIE_RATING_COL)));
                filmsItem.setCountry(cursor.getString(cursor.getColumnIndex(MOVIE_COUNTRY_COL)));
                filmsItem.setPrice(cursor.getString(cursor.getColumnIndex(MOVIE_PRICE_COL)));
                filmsItem.setId(cursor.getString(cursor.getColumnIndex(MOVIE_ID_COL)));
                cursor.moveToNext();
            }
        }
        return filmsItem;
    }

    public boolean checkedValueList(String movieName){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + MOVIE_NAME_COL + " = '" + movieName + "' ", null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }


    public void registerUser(String name, String pass, String email, String phoneNumber){
        ContentValues values = new ContentValues();
        values.put(NAME_COl, name);
        values.put(PASS_COL, pass);
        values.put(EMAIL_COL, email);
        values.put(PHONE_NUMBER_COL, phoneNumber);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor fetchData(){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = new String[] { ID_COL, NAME_COl, PASS_COL };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @SuppressLint("Range")
    public UserModel gettingUserDataByID(int id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL + " = '" + id +"' ", null);
        UserModel userModel = new UserModel();
        if(cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                userModel.setUserEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                userModel.setUserName(cursor.getString(cursor.getColumnIndex(NAME_COl)));
                userModel.setId(cursor.getInt(cursor.getColumnIndex(ID_COL)));
                userModel.setUserPhoneNumber(cursor.getString(cursor.getColumnIndex(PHONE_NUMBER_COL)));
                cursor.moveToNext();
            }
        }
        return userModel;
    }

    @SuppressLint("Range")
    public UserModel gettinguserID(String userName, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COl + " = '" + userName + "' AND " + PASS_COL + " = '" + password+"'", null);
        UserModel userModel = new UserModel();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
            userModel.setId(cursor.getInt(cursor.getColumnIndex(ID_COL)));
            cursor.moveToNext();
            }
        }
        cursor.close();
        return userModel;
    }

    public Boolean checkUsername(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COl + " = '" + username + "' AND " + PASS_COL + " = '" + password+"'", null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public void updateQtyTicket(String qty, String transactionID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(QUANTITY_COL, qty);
        db.update(TABLE_TRANSACTION_NAME, updateValues,"transaction_id = ?", new String[]{transactionID});
        db.close();
    }

    public void deleteQtyTicketByID(String transactionID){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_TRANSACTION_NAME,"transaction_id = ?", new String[]{transactionID});
        db.close();
    }

}
