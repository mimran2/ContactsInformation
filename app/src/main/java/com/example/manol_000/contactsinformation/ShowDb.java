package com.example.manol_000.contactsinformation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ShowDb extends AppCompatActivity {
    MyDbHelper mDbHelper;
    SQLiteDatabase mDb;
    Cursor dbCursor;
    SimpleCursorAdapter dbAdapter;

    String[] COLUMNS = new String[] {MyDbHelper.COL_NAME,MyDbHelper.COL_INFO};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db);
        mDbHelper = new MyDbHelper(this);
        mDb = mDbHelper.getWritableDatabase();
        String[] allColumns = new String[] {"_id", MyDbHelper.COL_NAME, MyDbHelper.COL_INFO};
        dbCursor = mDb.query(MyDbHelper.TABLE_NAME, allColumns, null, null, null, null, null);
        if (dbCursor!= null) dbCursor.moveToFirst();
        dbAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.line,
                dbCursor,
                COLUMNS,
                new int[]{R.id.tv_name, R.id.tv_details}, 0);
        ListView listView = (ListView) findViewById(R.id.dblist);
        // Assign adapter to ListView
        listView.setAdapter(dbAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.show:
//                Intent g= getIntent();
//                finish();
//                startActivity(g);
                break;
            case R.id.clearDB:
                mDb.delete("people", null, null);
                Intent gg= new Intent(ShowDb.this, ShowDb.class);
                finish();
                startActivity(gg);
                break;

        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
