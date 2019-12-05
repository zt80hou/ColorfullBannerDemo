package com.example.administrator.colorfulbanner;

/**
 * 作者：Zhout
 * 时间：2019/3/12 13:33
 * 描述：banner图片颜色渐变Bean
 * <p>
 * Vibrant （有活力）
 * Vibrant dark（有活力 暗色）
 * Vibrant light（有活力 亮色）
 * Muted （柔和）
 * Muted dark（柔和 暗色）
 * Muted light（柔和 亮色）
 */
public class ColorInfo {
    private String imgUrl;
    private int vibrantColor = 0xFF999999;
    private int vibrantDarkColor = 0xFF999999;
    private int vibrantLightColor = 0xFF999999;
    private int mutedColor = 0xFF999999;
    private int mutedDarkColor = 0xFF999999;
    private int mutedLightColor = 0xFF999999;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public int getVibrantColor() {
        return vibrantColor;
    }

    public void setVibrantColor(int vibrantColor) {
        this.vibrantColor = vibrantColor;
    }

    public int getVibrantDarkColor() {
        return vibrantDarkColor;
    }

    public void setVibrantDarkColor(int vibrantDarkColor) {
        this.vibrantDarkColor = vibrantDarkColor;
    }

    public int getVibrantLightColor() {
        return vibrantLightColor;
    }

    public void setVibrantLightColor(int vibrantLightColor) {
        this.vibrantLightColor = vibrantLightColor;
    }

    public int getMutedColor() {
        return mutedColor;
    }

    public void setMutedColor(int mutedColor) {
        this.mutedColor = mutedColor;
    }

    public int getMutedDarkColor() {
        return mutedDarkColor;
    }

    public void setMutedDarkColor(int mutedDarkColor) {
        this.mutedDarkColor = mutedDarkColor;
    }

    public int getMutedLightColor() {
        return mutedLightColor;
    }

    public void setMutedLightColor(int mutedLightColor) {
        this.mutedLightColor = mutedLightColor;
    }
}
