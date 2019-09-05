package com.brocade.types.font;

import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 〈矩形文本水印背景文字水印〉
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public class RectangleBackgroundFontType extends BaseBackGroundFontType {
    private static final long serialVersionUID = -9032574906836774786L;

    @Override
    protected void dealShapeBackground(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics) {
        //取消锯齿轮
        originalImageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //绘制空心得矩形
        originalImageGraphics.drawRect(originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, metrics.stringWidth(fontText), metrics.getHeight());
        //设置实心矩形的透明度
        originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, backgroundTransparency));
        //设置背景颜色
        originalImageGraphics.setColor(new Color(Integer.valueOf(backGroundColor.substring(1), 16)));
        //填充矩形
        originalImageGraphics.fillRect(originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100, metrics.stringWidth(fontText), metrics.getHeight());
        //设置字体颜色;如果有传入的颜色设置
        if (null != color) {
            originalImageGraphics.setColor(color);
        } else {
            if (StringUtils.isNotBlank(fontColor)) {
                //字体的颜色
                color = new Color(Integer.valueOf(fontColor.substring(1), 16));
                originalImageGraphics.setColor(color);
            }
        }
        //设置字体
        originalImageGraphics.setFont(font);
        //设置字体透明度
        if (null != transparency) {
            originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, this.transparency));
        }
        //绘制字体
        originalImageGraphics.drawString(fontText, originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100 + metrics.getAscent());
    }
}
