package com.brocade.types.images;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈简单的旋转图片水印〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public class SimpleRotateImageType extends BaseRotateImageType {
    private static final long serialVersionUID = -7214175618935934602L;

    @Override
    protected void dealDrawWaterImage(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        //将水印图片转化为图标
        ImageIcon imageIcon = new ImageIcon(watermarkImage);
        Image watermarkIconImage = imageIcon.getImage();
        //设置透明度
        originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
        //处理旋转角度
        dealRotate(degree, originalImageGraphics);
        //绘制水印
        originalImageGraphics.drawImage(watermarkIconImage, originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, null);
    }

    @Override
    protected void dealRotate(Float degree, Graphics2D originalImageGraphics) {
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), x, y));
    }
}
