package com.HeiLAAS.qlulibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NewAdapter extends BaseAdapter {
    private List<BookData> newList;
    private View view;
    private Context mcontext;
    private ViewHolder viewHolder;
    public NewAdapter(Context mcontext,List<BookData> obj){
        this.mcontext=mcontext;
        this.newList=obj;
    }
    @Override
    public int getCount() {
        return newList.size();
    }

    @Override
    public Object getItem(int position) {
        return newList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            view = LayoutInflater.from(mcontext).inflate(R.layout.listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.newsBookName = (TextView)view.findViewById(R.id.list_item_bookname);
            viewHolder.newsBookAuthor=(TextView)view.findViewById(R.id.list_item_bookauthor);
            view.setTag(viewHolder);
        }else {
            view =convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.newsBookName.setText(newList.get(position).getBookName());
        viewHolder.newsBookAuthor.setText(newList.get(position).getBookAuthor());
        return view;
    }
}
