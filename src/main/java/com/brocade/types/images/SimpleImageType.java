package com.brocade.types.images;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 〈普通图片水印处理〉
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public class SimpleImageType extends BaseImageWatermarkType {
    private static final long serialVersionUID = 8384372066247409094L;

    @Override
    protected void dealImageWatermark(BufferedImage image, Graphics2D graphics, Image watermarkImage) {
        //将水印图片转化为图标
        ImageIcon imageIcon = new ImageIcon(watermarkImage);
        Image watermarkIconImage = imageIcon.getImage();
        //设置透明度
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
        //处理旋转角度
        graphics.setTransform(AffineTransform.getRotateInstance(0));
        //绘制水印
        graphics.drawImage(watermarkIconImage, image.getWidth() * width / 100, image.getHeight() * height / 100, null);
    }

    @Override
    protected void dealRotate(Float degree, Graphics2D graphics) {
    }


}
