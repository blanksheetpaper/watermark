package com.brocade.types;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

/**
 * 〈水印类型基类〉
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public abstract class BaseWatermarkType implements Serializable {
    private static final long serialVersionUID = 8788401601771980077L;
    /**
     * 透明度[0-->1]之间
     */
    protected Float transparency;
    /**
     * 文字水印|图片水印的尺寸大小
     * <p>如果为文字水印这个大小就是每个文字的字体大小</p>
     * <p>如果为图片水印这个大小在计算的时候会当做百分比进行等比裁剪计算</p>
     */
    protected Integer size;

    /**
     * 水印摆放的宽度百分比
     */
    protected Integer width;
    /**
     * 水印摆放的高度百分比
     */
    protected Integer height;

    private void checkParams() throws IllegalArgumentException {
        if (Objects.isNull(width)) {
            throw new IllegalArgumentException("水印摆放位置---->[width]不可为空");
        }
        if (Objects.isNull(height)) {
            throw new IllegalArgumentException("水印摆放位置---->[height]不可为空");
        }
    }

    /**
     * 处理水印图片[调用方法]
     *
     * @param originalImage         需要添加水印的图片
     * @param originalImageGraphics 画笔
     */
    public void dealWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics) throws Exception {
        checkParams();
        apply(originalImage, originalImageGraphics);
    }

    /**
     * 处理添加水印
     *
     * @param image    需要添加水印的图片
     * @param graphics 画笔
     * @throws Exception 处理异常
     */
    protected abstract void apply(BufferedImage image, Graphics2D graphics) throws Exception;


    public Float getTransparency() {
        return transparency;
    }

    public void setTransparency(Float transparency) {
        this.transparency = transparency;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
