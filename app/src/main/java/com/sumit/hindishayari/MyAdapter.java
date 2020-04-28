package com.sumit.hindishayari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

// adapter class for view pager for all
class MyAdapter extends PagerAdapter {
    private String[] msg;
    private Context context;


    MyAdapter(String[] msg, Context context) {
        this.msg = msg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return msg.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.text_view_layout,container,false);
        container.addView(v);
        TextView textview=v.findViewById(R.id.textView);
        textview.setText(msg[position]);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup)object);
    }
}
