package com.brocade.types.font;

import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * 〈旋转矩形文本水印背景文字水印〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public class RotateRectangleFontType extends BaseRotateFontType {
    private static final long serialVersionUID = -2610059345666892778L;
    /**
     * 背景的底色透明度[0-->1]之间
     */
    protected Float backgroundTransparency;
    /**
     * 背景颜色[rgb]
     */
    protected String backGroundColor;

    @Override
    protected void dealShapeBackground(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics) {
        if (Objects.isNull(backgroundTransparency)) {
            throw new IllegalArgumentException("旋转矩形文本水印背景文字水印需要矩形背景的透明度");
        }
        //取消锯齿轮
        originalImageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //设置旋转角度
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), x, y));
        //绘制空心得矩形
        originalImageGraphics.drawRect(originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, metrics.stringWidth(fontText), metrics.getHeight());
        //设置背景颜色
        originalImageGraphics.setColor(new Color(Integer.valueOf(backGroundColor.substring(1), 16)));
        //设置实心矩形的透明度
        originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, backgroundTransparency));
        //设置旋转角度
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), x, y));
        //填充矩形背景
        originalImageGraphics.fillRect(originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, metrics.stringWidth(fontText), metrics.getHeight());
        //设置字体
        originalImageGraphics.setFont(font);
        //设置字体透明度
        if (null != transparency) {
            originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, this.transparency));
        }
        //如果有传入的颜色设置
        if (null != color) {
            originalImageGraphics.setColor(color);
        } else {
            if (StringUtils.isNotBlank(fontColor)) {
                //字体的颜色
                color = new Color(Integer.valueOf(fontColor.substring(1), 16));
                originalImageGraphics.setColor(color);
            }
        }
        //设置旋转角度
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), x, y));
        //绘制字体
        originalImageGraphics.drawString(fontText, originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100 + metrics.getAscent());
    }

    public Float getBackgroundTransparency() {
        return backgroundTransparency;
    }

    public void setBackgroundTransparency(Float backgroundTransparency) {
        this.backgroundTransparency = backgroundTransparency;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
