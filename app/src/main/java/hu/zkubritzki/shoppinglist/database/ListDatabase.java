package hu.zkubritzki.shoppinglist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class ListDatabase {

    private ListDatabase() {

    }

    public class ShopListDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "ShopList.db";

        public ShopListDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_SHOPLIST);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_SHOPLIST);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    //Main list
    public static class ShopList implements BaseColumns {
        public static final String TABLE_NAME = "ShopList";
        //public static final String COLUMN_NAME_ID = "Id";
        public static final String COLUMN_NAME_NAME = "ListName";
        public static final String COLUMN_NAME_COMPLETED = "Completed";
    }

    private static final String SQL_CREATE_SHOPLIST = "CREATE TABLE " + ShopList.TABLE_NAME + "(" +
            ShopList._ID + " INTEGER PRIMARY KEY, " +
            ShopList.COLUMN_NAME_NAME + " TEXT," +
            ShopList.COLUMN_NAME_COMPLETED + " INTEGER)";

    private static final String SQL_DELETE_SHOPLIST = "DROP TABLE IF EXISTS " + ShopList.TABLE_NAME;

}
