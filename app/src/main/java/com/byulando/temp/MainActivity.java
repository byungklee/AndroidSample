package com.byulando.temp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ImageView imageView;
        ImageView imageView2;
        ViewSwitcher viewSwitcher;
        TextView textView;

        String[] textData = {"abcd","efgh"};

        String[] sampleImageUrl = {"https://api.learn2crack.com/android/images/donut.png",
                "https://www.gstatic.com/webp/gallery3/1.png",
                "http://www.fnordware.com/superpng/pnggrad16rgb.png"
        };
        int index = 0;
        Timer timer;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            imageView = (ImageView) rootView.findViewById(R.id.first_image);
            imageView2 = (ImageView) rootView.findViewById(R.id.second_image);
            viewSwitcher = (ViewSwitcher) rootView.findViewById(R.id.switcher);

//            textView = (TextView) rootView.findViewById(R.id.img_desc);

            rootView.findViewById(R.id.df).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), SecondActivity.class);
                    getActivity().startActivity(i);
                }
            });


//            alphaAnimation.setRepeatCount(1);
//            alphaAnimation.setRepeatMode(Animation.REVERSE);
//            imageView.startAnimation(alphaAnimation);



            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Timer");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("ashdlkfha;skdhf;lksdf");
                            if (viewSwitcher.getDisplayedChild() == 0) {
                                System.out.println("1");
                                Glide.with(PlaceholderFragment.this).load(sampleImageUrl[index])
                                        .listener(new RequestListener<String, GlideDrawable>() {
                                            @Override
                                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                                e.printStackTrace();
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                                System.out.println("hello!");
                                                index = (index + 1) % sampleImageUrl.length;
                                                return false;
                                            }
                                        })
                                        .into(imageView2);
                                viewSwitcher.showNext();


                            } else {
                                System.out.println("2");
                                Glide.with(PlaceholderFragment.this).load(sampleImageUrl[index])
                                        .listener(new RequestListener<String, GlideDrawable>() {
                                            @Override
                                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                                e.printStackTrace();
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                                System.out.println("hello2!");
                                                index = (index + 1) % sampleImageUrl.length;
                                                return false;
                                            }
                                        })
                                        .into(imageView);
                                viewSwitcher.showPrevious();


                            }
                        }
                    });

                }
            };
            timer.schedule(timerTask, 1000,5000);


        }

        @Override
        public void onPause() {
            super.onPause();
            timer.cancel();
            timer = null;
        }

    }
}
