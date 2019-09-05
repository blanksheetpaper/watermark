package com.brocade.types.images;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 〈裁剪图片水印基类〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public abstract class BaseCropImageType extends BaseImageWatermarkType {

    private static final long serialVersionUID = -299954911411127306L;

    /**
     * 是否为透明背景的图片水印
     *
     * @param waterImage 原始的水印图
     * @return true|false
     */
    protected abstract boolean isTransparentImageWatermark(Image waterImage);

    /**
     * 透明图片处理
     *
     * @param originalImage         原始的图片水印
     * @param originalImageGraphics 画笔
     * @param watermarkImage        图片水印
     * @return 处理之后的水印
     */
    protected abstract BufferedImage dealTransparentImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage);

    /**
     * 非透明水印图片处理
     *
     * @param originalImage    原始的图片水印
     * @param originalGraphics 画笔
     * @param watermarkImage   图片水印
     * @return 处理之后的水印
     */
    protected abstract BufferedImage dealOpaqueImageWatermark(BufferedImage originalImage, Graphics2D originalGraphics, Image watermarkImage);


}
