package com.brocade.types.images;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * 〈等比例裁剪图片水印〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public class ProportionalCropImageType extends BaseCropImageType {
    private static final long serialVersionUID = -6195091661219631183L;

    @Override
    protected void dealImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        if (Objects.isNull(size)) {
            throw new IllegalArgumentException("请输入需要等比裁剪的图片裁剪大小百分比");
        }
        // 设置水印旋转度
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(0));
        //水印是否为透明图片   TODO:暂时这样判断是否为透明色;
        if (isTransparentImageWatermark(watermarkImage)) {
            dealTransparentImageWatermark(originalImage, originalImageGraphics, watermarkImage);
        } else {
            dealOpaqueImageWatermark(originalImage, originalImageGraphics, watermarkImage);
        }
    }

    @Override
    protected boolean isTransparentImageWatermark(Image watermarkImage) {
        return isTransparentCheck((BufferedImage) watermarkImage, watermarkImage.getWidth(null), watermarkImage.getHeight(null));
    }

    @Override
    protected BufferedImage dealTransparentImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        BufferedImage afterDealWatermark = new BufferedImage(watermarkImage.getWidth(null) * size / 100,
                watermarkImage.getHeight(null) * size / 100, Transparency.TRANSLUCENT);
        applyWatermarkInImage(originalImage, originalImageGraphics, watermarkImage, afterDealWatermark);
        return afterDealWatermark;
    }

    @Override
    protected BufferedImage dealOpaqueImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        //处理之后的水印
        BufferedImage afterDealWatermark = new BufferedImage(watermarkImage.getWidth(null) * size / 100,
                watermarkImage.getHeight(null) * size / 100, BufferedImage.TYPE_INT_RGB);
        applyWatermarkInImage(originalImage, originalImageGraphics, watermarkImage, afterDealWatermark);
        return afterDealWatermark;
    }

    @Override
    protected void dealRotate(Float degree, Graphics2D originalGraphics) {
    }

    /**
     * 检查是不是透明底色
     *
     * @param watermarkImage 水印图片对象
     * @param width          水印图片宽度
     * @param height         水印图片高度
     * @return true|false
     */
    private boolean isTransparentCheck(BufferedImage watermarkImage, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (watermarkImage.getRGB(j, i) >> 24 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将裁剪后的水印应用到图片上
     *
     * @param originalImage         需要添加水印的图片
     * @param originalImageGraphics 画板
     * @param watermarkImage        水印图片
     * @param afterDealWatermark    处理之后的水印
     */
    private void applyWatermarkInImage(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage, BufferedImage afterDealWatermark) {
        Graphics2D waterGraphics;
        ImageIcon imageIcon;
        waterGraphics = afterDealWatermark.createGraphics();
        //取消锯齿
        waterGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //绘制等比裁剪后的水印
        waterGraphics.drawImage(watermarkImage.getScaledInstance(watermarkImage.getWidth(null) * size / 100,
                watermarkImage.getHeight(null) * size / 100, Image.SCALE_SMOOTH), 0, 0, null);
        //释放资源
        waterGraphics.dispose();
        //将水印图片转化为图标
        imageIcon = new ImageIcon(afterDealWatermark);
        //获取裁剪后的水印对象
        Image watermarkIconImage = imageIcon.getImage();
        //设置水印图片的透明度
        originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
        //绘制水印
        originalImageGraphics.drawImage(watermarkIconImage, originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, null);
    }
}
