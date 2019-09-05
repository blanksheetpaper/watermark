package com.brocade.types.images;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * 〈旋转图片水印〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public abstract class BaseRotateImageType extends BaseImageWatermarkType {
    private static final long serialVersionUID = 8435793977995950359L;

    /**
     * 水印放置再原始图片上的x轴的位置
     * <p>设置图片的旋转角度[画笔在画图的时候,是以图片的左上角作为原点开始画的,不管是文字水印还是字体水印,这个里面的x|y对应的坐标应该是文字水印或图片水印左上角的点的坐标]</p>
     */
    protected Integer x;
    /**
     * 水印放置在原始图片上面的y轴的位置
     * <p>设置图片的旋转角度[画笔在画图的时候,是以图片的左上角作为原点开始画的,y轴正方向向下,x轴正方向像右;
     * 不管是文字水印还是字体水印, 这个里面的x|y对应的坐标应该是文字水印或图片水印左上角的点的坐标]</p>
     */
    protected Integer y;

    protected Float degree;

    @Override
    protected void dealImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage) {
        checkImageParams();
        dealDrawWaterImage(originalImage, originalImageGraphics, watermarkImage);
    }

    private void checkImageParams() {
        if (Objects.isNull(degree)) {
            throw new IllegalArgumentException("旋转图片水印需要有旋转角度");
        }
        if (Objects.isNull(x)) {
            throw new IllegalArgumentException("旋转图片水印需要输入旋转点的坐标[X]");
        }
        if (Objects.isNull(y)) {
            throw new IllegalArgumentException("旋转图片水印需要输入旋转点的坐标[Y]");
        }
    }

    protected abstract void dealDrawWaterImage(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage);

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }
}
