package com.tlh.hellochartsdemo;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends ListActivity {

    private List<ResolveInfo> mActivities;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        iniData();
    }

    private void iniData() {
        PackageManager pm=getPackageManager();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory("com.tlh.hellochartsdemo");
        mActivities = pm.queryIntentActivities(intent, 0);
        mAdapter = new MyAdapter();
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo = mActivities.get(position);
        Intent intent=new Intent();
        ComponentName component=new ComponentName(MainActivity.this, resolveInfo.activityInfo.name);
        intent.setComponent(component);
        startActivity(intent);
    }

    private final class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(mActivities!=null){
                return mActivities.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(mActivities!=null){
                return mActivities.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view=new TextView(MainActivity.this);
            view.setGravity(Gravity.CENTER);
            view.setTextSize(25);
            ActivityInfo activityInfo = mActivities.get(position).activityInfo;
            String name = activityInfo.name;
            name=name.substring(name.lastIndexOf(".")+1,name.length());
            view.setText(name);
            return view;
        }

    }

}
