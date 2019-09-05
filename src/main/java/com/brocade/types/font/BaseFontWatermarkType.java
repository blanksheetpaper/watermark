package com.brocade.types.font;

import com.brocade.types.BaseWatermarkType;
import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * 〈基础字体水印父类〉
 * <p>如果在测试过程中发现生成的中文文字水印有乱码,需要查看一下系统是否支持<对应的文字类型[fontName]/p>
 *
 * @author GJJ
 * @date 2019/9/4
 * @since JDK1.8
 */
public abstract class BaseFontWatermarkType extends BaseWatermarkType {
    private static final long serialVersionUID = -5574398902883426055L;

    /**
     * 字体
     * <p>当此属性拥有的时候会使用此属性忽略其他设置的属性</p>
     */
    protected Font font;
    /**
     * 字体颜色
     * <p>当此属性和FontColor同时拥有的时候会使用此属性忽略FontColor设置的属性</p>
     */
    protected Color color;
    /**
     * 字体内容
     */
    protected String fontText;
    /**
     * 字体类型[微软雅黑|宋体...]
     */
    protected String fontName;
    /**
     * 字体风格[Font.PLAIN...]
     */
    protected int fontStyle = Font.PLAIN;
    /**
     * 字体的颜色
     * <p>RGB---->16进制</p>
     * <p>当此属性和FontColor同时拥有的时候会使用此属性忽略FontColor设置的属性</p>
     */
    protected String fontColor;

    private void checkFontParams() throws IllegalArgumentException {
        if (StringUtils.isBlank(fontName)) {
            throw new IllegalArgumentException("字体水印,字体名称不可为空");
        }
        if (StringUtils.isBlank(fontText)) {
            throw new IllegalArgumentException("字体水印,字体文本不可为空");
        }
        if (Objects.isNull(size)) {
            throw new IllegalArgumentException("字体水印,字体文本大小不可为空");
        }
    }

    @Override
    public void apply(BufferedImage originalImage, Graphics2D originalImageGraphics) throws Exception {
        checkFontParams();
        if (Objects.isNull(font)) {
            font = new Font(fontName, fontStyle, size);
        }
        //得到字体的高度
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        //处理旋转角度
        originalImageGraphics.setTransform(AffineTransform.getRotateInstance(0));
        //不一样的处理逻辑
        dealDetails(originalImage, originalImageGraphics, metrics);
    }

    /**
     * 处理文字水印的细节
     *
     * @param originalImage         需要添加水印的图片
     * @param originalImageGraphics 画笔
     * @param metrics               字体度量
     */
    protected abstract void dealDetails(BufferedImage originalImage, Graphics2D originalImageGraphics, FontDesignMetrics metrics);

    public String getFontText() {
        return fontText;
    }

    public void setFontText(String fontText) {
        this.fontText = fontText;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
