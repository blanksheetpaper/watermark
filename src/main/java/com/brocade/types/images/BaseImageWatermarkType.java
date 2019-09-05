package com.brocade.types.images;

import com.brocade.types.BaseWatermarkType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 〈图片水印基类〉
 *
 * @author GJJ
 * @date 2019/9/5
 * @since JDK1.8
 */
public abstract class BaseImageWatermarkType extends BaseWatermarkType {
    private static final long serialVersionUID = 3137735211095699763L;
    /**
     * 水印图片的地址
     * <p>file|url其中有一个有值即可</p>
     * <p>如果当FILE和URL两个都存在时,使用URL</p>
     */
    protected File file;
    /**
     * 水印图片的网络地址
     * <p>file|url其中有一个有值即可</p>
     * <p>如果当FILE和URL两个都存在时,使用URL</p>
     */
    protected URL url;

    @Override
    protected void apply(BufferedImage originalImage, Graphics2D originalImageGraphics) throws Exception {
        Image watermarkImage = checkImageParam();
        dealImageWatermark(originalImage, originalImageGraphics, watermarkImage);
    }

    /**
     * 处理图片水印的逻辑
     *
     * @param originalImage         需要添加水印的图片
     * @param originalImageGraphics 画笔
     * @param watermarkImage        水印图片
     */
    protected abstract void dealImageWatermark(BufferedImage originalImage, Graphics2D originalImageGraphics, Image watermarkImage);

    private Image checkImageParam() throws IOException {
        Image watermarkImage;
        if (null != url) {
            watermarkImage = ImageIO.read(url);
            return watermarkImage;
        }
        if (null != file) {
            watermarkImage = ImageIO.read(file);
            return watermarkImage;
        }
        throw new IllegalArgumentException("添加水印图片,File或URL不可同时为空");
    }

    /**
     * 旋转角度需要每次重置,否则会造成旋转无效或导致旋转跟随
     * <p>如果没有重置旋转度,如果是两张图片水印,会造成后面执行的水印旋转失效,即只有第一个执行的图片水印有效果,后面的没有效果</p>
     * <p>如果没有重置旋转度,如果是一张图片水印,一张是文字水印,可能会造成文字水印或图片水印按照第一个执行的水印旋转角度旋转</p>
     *
     * @param degree                旋转角度
     * @param originalImageGraphics 画笔
     */
    protected abstract void dealRotate(Float degree, Graphics2D originalImageGraphics);

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
