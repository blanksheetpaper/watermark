package com.brocade.types.font;

import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * 〈旋转文字水印基类〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public abstract class BaseRotateFontType extends BaseFontWatermarkType {
    private static final long serialVersionUID = -7016526809465406189L;
    /**
     * 旋转角度
     */
    protected Float degree;

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

    @Override
    protected void dealDetails(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics) {
        checkRotateFontParams();
        dealShapeBackground(originalImage, originalImageGraphics, metrics);
    }


    /**
     * 根据不同的背景形状进行旋转
     *
     * @param originalImage         需要加水印的图片
     * @param originalImageGraphics 画笔
     * @param metrics               字体量度
     */
    protected abstract void dealShapeBackground(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics);

    private void checkRotateFontParams() {
        if (Objects.isNull(x)) {
            throw new IllegalArgumentException("旋转文字水印需要输入旋转点的坐标[X]");
        }
        if (Objects.isNull(y)) {
            throw new IllegalArgumentException("旋转文字水印需要输入旋转点的坐标[Y]");
        }
        if (Objects.isNull(degree)) {
            throw new IllegalArgumentException("旋转文字水印需要输入旋转角度");
        }
    }

    /**
     * 旋转角度需要每次重置,否则会造成旋转无效或导致旋转跟随
     * <p>如果没有重置旋转度,如果是两张图片水印,会造成后面执行的水印旋转失效,即只有第一个执行的图片水印有效果,后面的没有效果</p>
     * <p>如果没有重置旋转度,如果是一张图片水印,一张是文字水印,可能会造成文字水印或图片水印按照第一个执行的水印旋转角度旋转</p>
     *
     * @param degree                旋转角度
     * @param originalImageGraphics 画笔
     */
    @Deprecated
    protected void dealRotate(Float degree, Graphics2D originalImageGraphics) {
        if (null != degree) {
            originalImageGraphics.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degree), x, y));
        } else {
            originalImageGraphics.setTransform(AffineTransform.getRotateInstance(0));
        }
    }

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
