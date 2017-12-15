package com.example.extension;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prayxiang.recyclerview.extension.BaseAdapter;
import com.prayxiang.recyclerview.extension.LoadListener;
import com.prayxiang.recyclerview.extension.tools.Empty;
import com.prayxiang.recyclerview.extension.tools.EmptyViewBinder;
import com.prayxiang.recyclerview.extension.tools.LoaderMore;
import com.prayxiang.recyclerview.extension.tools.LoaderMoreViewBinder;
import com.prayxiang.recyclerview.extension.tools.SimpleViewBound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("xgf", "offset:" + positionOffset + "\n" + "pixels :" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("xgf2", "select:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("xgf3", "state:" + state);
            }
        });

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                final RecyclerView recyclerView = new RecyclerView(container.getContext());
                container.addView(recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
                final BaseAdapter adapter = new BaseAdapter();
                adapter.addFixedViewBinder("ss");
                adapter.addFixedViewBinder("ss2");
                adapter.addFixedViewBinder("ss3");


                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        adapter.addFixedViewBinder("ss4");
                        adapter.notifyItemChanged(4);




                    }
                }, 3000);


                adapter.register(String.class, new SimpleViewBound(R.layout.item_main, BR.data));
                adapter.register(LoaderMore.class, new LoaderMoreViewBinder());
                adapter.register(Empty.class, new EmptyViewBinder());
                recyclerView.setAdapter(adapter);
                List list = new ArrayList(20);
                int count = 0;
                for (int i = 0; i < 20; i++) {
                    list.add("item = " + "0" + i);
//           count++;
                }

//                Toast.makeText(container.getContext(), "" + ((count++) + (++count)), Toast.LENGTH_SHORT).show();


                adapter.display(Collections.emptyList());
                adapter.setLoadListener(new LoadListener() {
                    @Override
                    public void load(final int offset) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (offset >= 3) {
                                    adapter.insert(Collections.emptyList());
                                    return;
                                }
                                List list = new ArrayList(20);
                                for (int i = 0; i < 20; i++) {
                                    list.add("item = " + offset + "" + i);
                                }
                                adapter.insert(list);
                            }
                        }, 3000);

                    }
                });

                return recyclerView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });


//        viewPager.setCurrentItem(0);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        final BaseAdapter adapter = new BaseAdapter();
//        adapter.register(String.class,new SimpleViewBound(R.layout.item_main,BR.data));
//        adapter.register(LoaderMore.class,new LoaderMoreViewBinder());
//        recyclerView.setAdapter(adapter);
//        List list = new ArrayList(20);
//      int count = 0;
//        for (int i = 0; i < 20; i++) {
//            list.add("item = " + "0" + i);
////           count++;
//        }
//
//        Toast.makeText(this,""+ ((count++)+(++count)),Toast.LENGTH_SHORT).show();
//
//
//
//        adapter.display(list);
//        adapter.setLoadListener(new LoadListener() {
//            @Override
//            public void load(final int offset) {
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(offset>=3){
//                            adapter.insert(Collections.emptyList());
//                            return;
//                        }
//                        List list = new ArrayList(20);
//                        for (int i = 0; i < 20; i++) {
//                            list.add("item = " + offset + "" + i);
//                        }
//                        adapter.insert(list);
//                    }
//                },3000);
//
//            }
//        });


    }
}
