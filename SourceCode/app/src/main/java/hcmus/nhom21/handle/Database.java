package hcmus.nhom21.handle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Truy vấn tạo thêm cập nhật xóa
    public void queryData(String commandSql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(commandSql);
    }

    //Truy vấn select...from
    public Cursor getData(String commandSql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(commandSql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
