package mobile.ideabubble.csq.ideabubblebrowser.DataManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase rwDB;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LINK( _id INTEGER PRIMARY KEY AUTOINCREMENT, linkdata TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LINK");
        onCreate(db);
    }

    protected void input(String data) {
        rwDB = getWritableDatabase();
        rwDB.execSQL("INSERT INTO TEST_LIST VALUES(null,'" + data + "');");
        rwDB.close();
    }

    protected String getResult() {
        //DB Intc -> Readable로 Open.
        rwDB= getReadableDatabase();
        String result = "";

        Cursor cursor = rwDB.rawQuery("SELECT * FROM LIST", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0) + " : " + cursor.getString(1) + " " + "\n";
        }
        rwDB.close();
        return result;
    }
}
