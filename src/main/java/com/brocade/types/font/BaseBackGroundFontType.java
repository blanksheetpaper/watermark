package com.brocade.types.font;

import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * 〈带有文本背景的字体水印基类〉
 * <p>想生成透明的文字背景请使用SimpleFontWatermarkType,否则使用此类会生成白色底</p>
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public abstract class BaseBackGroundFontType extends BaseFontWatermarkType {
    private static final long serialVersionUID = -2314025131593190517L;

    /**
     * 背景的底色透明度[0-->1]之间
     */
    protected Float backgroundTransparency;

    /**
     * 背景颜色
     * <p>RGB---->16进制</p>
     */
    protected String backGroundColor;

    @Override
    protected void dealDetails(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics) {
        checkBackGroundParams();
        dealShapeBackground(originalImage, originalImageGraphics, metrics);
    }

    private void checkBackGroundParams() {
        if (Objects.isNull(backgroundTransparency)) {
            throw new IllegalArgumentException("文本水印添加背景颜色需要有背景颜色的透明度");
        }
        if (StringUtils.isBlank(backGroundColor)) {
            throw new InputMismatchException("文本水印添加背景颜色需要有背景颜色RGB");
        }
    }

    /**
     * 最终处理生成什么形状的文本背景形状
     *
     * @param originalImage         原始图片
     * @param originalImageGraphics 画笔
     * @param metrics               字体度量
     */
    protected abstract void dealShapeBackground(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics);

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
