package com.example.manol_000.contactsinformation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Info2Db extends AppCompatActivity {
    MyDbHelper mDbHelper;
    SQLiteDatabase mDb;
    String studentName;
    String[] COLUMNS = new String[] 
           {MyDbHelper.COL_NAME,MyDbHelper.COL_INFO};
    Boolean inDb;
    String originalData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info2_db);
        mDbHelper = new MyDbHelper(this);
        mDb = mDbHelper.getWritableDatabase();

        Intent intent = getIntent();
      studentName = intent.getStringExtra("ContactName");
        TextView tv = (TextView)findViewById(R.id.intro);
        tv.setText("Additional Info for "+ studentName);
        EditText et = (EditText)findViewById(R.id.newinfo);

        // Add query here to set up the edit field
        String selection = MyDbHelper.COL_NAME+" = ?";
        String[] args = new String[]{studentName};
        Cursor result = mDb.query(MyDbHelper.TABLE_NAME, 
                COLUMNS,
                selection,args,null,null,null,null);

        if (result.moveToFirst()) {
            originalData = result.getString(1);
            et.setText(originalData);
            inDb = true;
        }
        else {
            et.setHint("Information to store");
            inDb = false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mDb.close();
    }

    public void save2Db(View v) {
      TextView e=(TextView)findViewById(R.id.newinfo);
        if(inDb)
        {

            mDb.delete("people", "Name" + "=?",
                    new String[] { studentName });
        }
            synchronized (mDb){
                ContentValues newCV= new ContentValues();
                newCV.put("Name",studentName);
                newCV.put("Info",e.getText().toString());
                mDb.insert("people",null,newCV);
            }






        finish();
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
                Intent i = new Intent(Info2Db.this,ShowDb.class);
                startActivity(i);

                break;
            case R.id.clearDB:
                Intent ij = new Intent(Info2Db.this,ShowDb.class);
                startActivity(ij);

                break;

        }

        return super.onOptionsItemSelected(item);

        //noinspection SimplifiableIfStatement



    }
}
