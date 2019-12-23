package hu.zkubritzki.shoppinglist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ShopListData";
    private static final int DATABASE_VERSION = 0;
    private static final String DB_TAG = "Datahandler action";

    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTables(SQLiteDatabase db) {
        String createMainList = "Create Table if not exists MainList (" +
                "ListId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ListName TEXT NOT NULL UNIQUE," +
                "Completed INTEGER NOT NULL) ";
        db.execSQL(createMainList);
        String createItems = "Create Table if not exists ItemList (" +
                "ItemId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "LastPrice INTEGER) ";
        db.execSQL(createItems);
        String createItemsInList = "Create Table if not exists ItemsInList (" +
                "ListId INTEGER NOT NULL," +
                "ItemId INTEGER NOT NULL," +
                "Price INTEGER NOT NULL," +
                "Volume INTEGER NOT NULL) ";
        db.execSQL(createItemsInList);
        Log.d(DB_TAG,"Database tables created.");
    }

    public void insertShopList (ShopList list) {
        String insertCMD = "INSERT INTO MainList (ListName, Completed) " +
                "VALUES (" + list.getName() + ", 0)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertCMD);
        db.close();
    }

    public ArrayList<ShopList> getLists() {
        ArrayList<ShopList> lists = new ArrayList<>();
        String getListCMD = "SELECT * FROM MainList order by ListId DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mainListCursor = db.rawQuery(getListCMD, null);
        boolean hasElement = mainListCursor.moveToFirst();
        while (hasElement) {
            ShopList list = new ShopList();
            list.setListId(mainListCursor.getInt(0));
            list.setName(mainListCursor.getString(1));
            int completed = mainListCursor.getInt(2);
            if (completed == 0) {
                list.setCompleted(false);
            } else {
                list.setCompleted(true);
            }
            lists.add(list);
        }
        db.close();
        return lists;
    }
}
