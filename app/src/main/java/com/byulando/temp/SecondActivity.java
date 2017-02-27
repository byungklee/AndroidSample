package com.byulando.temp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

/**
 * Created by byung on 2/14/17.
 */

public class SecondActivity extends AppCompatActivity {
    RecyclerView r1;
    LinearLayoutManager l1;
    RecyclerView r2;
    LinearLayoutManager l2;
    RecyclerView r3;
    LinearLayoutManager l3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        r1 = (RecyclerView) findViewById(R.id.r1);
        r2 = (RecyclerView) findViewById(R.id.r2);
        r3 = (RecyclerView) findViewById(R.id.r3);
//        r1.setNestedScrollingEnabled(true);
//        r2.setNestedScrollingEnabled(false);
//        r3.setNestedScrollingEnabled(false);
        l1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        l2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        l3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        r1.setLayoutManager(l1);
        r2.setLayoutManager(l2);
        r3.setLayoutManager(l3);

        r1.setAdapter(new RA());
        r2.setAdapter(new RA());
        r3.setAdapter(new RA());
    }

    class RA extends RecyclerView.Adapter<RA.ViewHolder>
    {
        String[] colors = {"#333333","#ee0000","#00ff00" ,"#0000ff", "#ff00ff"};
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.sample_item,parent, false);
            ViewHolder vh = new ViewHolder(v);


            return vh;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            System.out.println(position);
            holder.ll.setBackgroundColor(Color.parseColor(colors[position]));
        }

        @Override
        public int getItemCount() {
            return colors.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            LinearLayout ll;
            public ViewHolder(View v)
            {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.ll);
            }
        }
    }
}
