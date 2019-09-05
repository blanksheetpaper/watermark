package com.brocade.types.font;

import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 〈简单的文字水印处理〉
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public class SimpleFontType extends BaseFontWatermarkType {
    private static final long serialVersionUID = 185529498065991441L;

    @Override
    protected void dealDetails(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics) {
        //取消锯齿轮
        originalImageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //设置字体
        originalImageGraphics.setFont(font);
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
        //字体透明度
        if (null != transparency) {
            originalImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, this.transparency));
        }
        //绘制字体
        originalImageGraphics.drawString(fontText, originalImage.getWidth() * width / 100, originalImage.getHeight() * height / 100 + metrics.getAscent());
    }
}
