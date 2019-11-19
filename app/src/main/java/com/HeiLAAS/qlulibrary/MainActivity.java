package com.HeiLAAS.qlulibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<BookData> mdata;
    private String KEYWORD="计算机";
    private ListView listView;
//    private Handler handler;
    private String Key;
    private String nextPage;
    private EditText editText;
    private Button button;
    int K =1;
    private NewAdapter newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdata = new ArrayList<>();
        listView =(ListView)findViewById(R.id.listview_id);
        editText =(EditText)findViewById(R.id.search_book_text_id);
        button =(Button)findViewById(R.id.search_book_button_id);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KEYWORD=editText.getText().toString();
                K=1;
                mdata.clear();
                newAdapter.notifyDataSetChanged();
                myThread();
            }
        });
        listView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == view.getCount()-1){
                            K++;
                            newAdapter.notifyDataSetChanged();
                            myThread();
                            Log.v("TAG","更新下一页成功");
                            Log.v("TAG","现在URL："+Key);
                        }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        newAdapter =new NewAdapter(MainActivity.this,mdata);
        listView.setAdapter(newAdapter);
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg){
//                if(msg.what == 1){
//                    NewAdapter newAdapter =new NewAdapter(MainActivity.this,mdata);
//                    listView.setAdapter(newAdapter);
//                }
//            }
//        };
    }


    public void myThread(){
        new Thread(){
            @Override
            public void run(){
                try {
                        String page = Integer.toString(K);
                        Key = "http://121.250.34.12:8080/opac/openlink.php?location=ALL&title=" + KEYWORD + "&doctype=ALL&lang_code=ALL&match_flag=forward&displaypg=20&showmode=list&orderby=DESC&sort=CATA_DATE&onlylendable=no&count=222&with_ebook=off&page="+page;
                        Log.v("TAG",""+Key);
                        Document doc = Jsoup.connect(Key).get();
                        Elements els = doc.select("ol#search_book_list>li");
                        for (int j = 0; j < els.size(); j++) {
                            String BookName = els.select("h3>a").get(j).text();
                            String BookAuthor = els.select("p").get(j).text();
                            String Url = els.select("h3>a").get(j).attr("href");
                            BookData news = new BookData(BookName, BookAuthor, Url,nextPage);
                            mdata.add(news);
                            Log.v("TAG", "图书名字:" + BookName);
                            Log.v("TAG", "作者：" + BookAuthor);
                            Log.v("TAG", "URL:" + Url);
                            }
                        Elements next = doc.getElementsByAttributeValue("class", "blue");
                        for (Element i : next) {
                            if (i.text().contains("下一页")) {
                                nextPage = i.attr("abs:href");
                                break;
                            }
                        }
                        Log.v("TAG","下一页地址:"+nextPage);
//                        Message msg =new Message();
//                        msg.what=1;
//                        handler.sendMessage(msg);
//                    NewAdapter newAdapter =new NewAdapter(MainActivity.this,mdata);
//                    newAdapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
