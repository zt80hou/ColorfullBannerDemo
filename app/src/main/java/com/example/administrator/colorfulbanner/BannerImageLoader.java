package com.example.administrator.colorfulbanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


/**
 * 作者：Zhout
 * 时间：2017/12/18 15:01
 * 描述：统一的加载banner图片(各种类型的， 热门banner，视频banner，直播间广告banner,商城banner.....)
 */

public class BannerImageLoader extends ImageLoader {
    private Context context;
    private List<ColorInfo> colorList;
    private Palette palette;

    public BannerImageLoader(List<ColorInfo> colorList) {
        this.colorList = colorList;
    }

    //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
    @Override
    public void displayImage(Context context, final Object imgObj, ImageView imageView) {
        this.context = context;
        if (imgObj != null) {
            imageView.setPadding(30, 0, 30, 0);
            Glide.with(context).asBitmap().load(imgObj.toString()).listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    setColorList(resource, imgObj.toString());
                    return false;
                }
            }).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(imageView);
        }
    }


    private void setColorList(Bitmap bitmap, String imgUrl) {
        if (colorList == null) {
            return;
        }
        palette = Palette.from(bitmap).generate();
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getImgUrl().equals(imgUrl)) {// imgUrl作为识别标志
                if (palette.getVibrantSwatch() != null) {
                    colorList.get(i).setVibrantColor(palette.getVibrantSwatch().getRgb());
                }
                if (palette.getDarkVibrantSwatch() != null) {
                    colorList.get(i).setVibrantDarkColor(palette.getDarkVibrantSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setVibrantLightColor(palette.getLightVibrantSwatch().getRgb());
                }
                if (palette.getMutedSwatch() != null) {
                    colorList.get(i).setMutedColor(palette.getMutedSwatch().getRgb());
                }
                if (palette.getDarkMutedSwatch() != null) {
                    colorList.get(i).setMutedDarkColor(palette.getDarkMutedSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setMutedLightColor(palette.getLightVibrantSwatch().getRgb());
                }
            }
        }
    }


    /**
     * Vibrant （有活力）
     * Vibrant dark（有活力 暗色）
     * Vibrant light（有活力 亮色）
     * Muted （柔和）
     * Muted dark（柔和 暗色）
     * Muted light（柔和 亮色）
     */

    public int getVibrantColor(int position) {
        return colorList.get(position).getVibrantColor();
    }

    public int getVibrantDarkColor(int position) {
        return colorList.get(position).getVibrantDarkColor();
    }

    public int getVibrantLightColor(int position) {
        return colorList.get(position).getVibrantLightColor();
    }

    public int getMutedColor(int position) {
        return colorList.get(position).getMutedColor();
    }

    public int getMutedDarkColor(int position) {
        return colorList.get(position).getMutedDarkColor();
    }

    public int getMutedLightColor(int position) {
        return colorList.get(position).getMutedLightColor();
    }
}
