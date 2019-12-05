package com.example.administrator.colorfulbanner;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "AAA";
    private List<String> bannerList = new ArrayList<>();
    private List<ColorInfo> colorList = new ArrayList<>();
    private BannerImageLoader imageLoader;
    private ImageView ivBannerHeadBg;
    private Banner banner;
    private int count;
    private boolean isInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initBanner();
    }

    private void initView() {
        ivBannerHeadBg = (ImageView) findViewById(R.id.iv_banner_head_bg);
        banner = (Banner) findViewById(R.id.banner);

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return;
                }
                //修正position，解决两头颜色错乱，来自Banner控件源码
                if (position == 0) {
                    position = count;
                }
                if (position > count) {
                    position = 1;
                }
                int pos = (position + 1) % count;//很关键

                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantColor(pos), imageLoader.getVibrantColor(pos + 1), positionOffset);
                ivBannerHeadBg.setBackgroundColor(vibrantColor);
                setStatusBarColor(MainActivity.this, vibrantColor);
            }

            @Override
            public void onPageSelected(final int position) {
                if(isInit){// 第一次,延时加载才能拿到颜色
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int vibrantColor = imageLoader.getVibrantColor(1);
                            ivBannerHeadBg.setBackgroundColor(vibrantColor);
                            setStatusBarColor(MainActivity.this, vibrantColor);
                        }
                    }, 200);

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=3769695217e3424f18c3d23966ecd4dc&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F04%2F19%2F70e2846ebc02ae10161f25bf7f5461a1.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532665664&di=9ead9eb8a9fe2af9a01b0dd39f3e41f4&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F05%2F37%2F28%2F475a43591370453.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613934&di=0be1c6bbf0441bd19ef6d4e3ce799263&imgtype=0&src=http%3A%2F%2Fpic96.nipic.com%2Ffile%2F20160430%2F7036970_215739900000_2.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=4dd453940f49d9801826e6b820490957&imgtype=0&src=http%3A%2F%2Fpic161.nipic.com%2Ffile%2F20180410%2F26429156_154754410034_2.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613935&di=39c387012e3d8fa2eef90129eaf83c5c&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121211%2F7031681_170238437383_2.jpg");
        count = bannerList.size();
        colorList.clear();
        for (int i = 0; i <= count + 1; i++) {
            ColorInfo info = new ColorInfo();
            if (i == 0) {
                info.setImgUrl(bannerList.get(count - 1));
            } else if (i == count + 1) {
                info.setImgUrl(bannerList.get(0));
            } else {
                info.setImgUrl(bannerList.get(i - 1));
            }
            colorList.add(info);
        }

        imageLoader = new BannerImageLoader(colorList);
        banner.setImageLoader(imageLoader);
        //设置图片集合
        banner.setImages(bannerList);
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //状态栏改变颜色。
            window.setStatusBarColor(color);
        }
    }
}
