package com.brocade;

import com.brocade.types.BaseWatermarkType;
import com.brocade.types.font.RectangleBackgroundFontType;
import com.brocade.types.font.RotateRectangleFontType;
import com.brocade.types.font.SimpleFontType;
import com.brocade.types.images.DesignationCropImageType;
import com.brocade.types.images.ProportionalCropImageType;
import com.brocade.types.images.SimpleImageType;
import com.brocade.types.images.SimpleRotateImageType;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static Graphics2D graphics;
    private static BufferedImage read;
    private static URL resource;
    private static String waterPath;

    static {
        try {
            resource = AppTest.class.getClassLoader().getResource("originalImage.png");
            waterPath = AppTest.class.getClassLoader().getResource("watermarkImage.png").getFile();
            read = ImageIO.read(resource);
            graphics = read.createGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleFontType() throws Exception {
        SimpleFontType simpleFontType = new SimpleFontType();
        simpleFontType.setHeight(20);
        simpleFontType.setWidth(20);
        simpleFontType.setSize(48);
        simpleFontType.setTransparency(0.5f);
        simpleFontType.setFontName("宋体");
        simpleFontType.setFontStyle(Font.PLAIN);
        simpleFontType.setFontText("HELLO WORLD");
        simpleFontType.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void rotateRectangleFontWatermarkTypeTest() throws Exception {
        RotateRectangleFontType type = new RotateRectangleFontType();
        type.setBackgroundTransparency(0.7F);
        type.setBackGroundColor("#FF0000");
        type.setX(200);
        type.setY(200);
        type.setDegree(50F);
        type.setFontText("HELLO WORLD");
        type.setFontName("幼圆");
        type.setFontStyle(2);
        type.setFontColor("#80FF00");
        type.setTransparency(1F);
        type.setSize(70);
        type.setWidth(20);
        type.setHeight(20);
        //如果设置了会按照下面设置的进行显示
        // type.setColor(new Color(0,255,39));
        // type.setFont(new Font("宋体", 0, 65));
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void rectangleBackgroundFontTypeTest() throws Exception {
        RectangleBackgroundFontType type = new RectangleBackgroundFontType();
        type.setBackgroundTransparency(0.9F);
        type.setFontText("HELLO WORLD");
        type.setFontName("宋体");
        type.setFontStyle(0);
        type.setFontColor("#00FF27");
        type.setTransparency(0.7F);
        type.setSize(60);
        type.setWidth(20);
        type.setHeight(20);
        type.setBackGroundColor("#FF0000");
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void simpleImageTypeTest() throws Exception {
        SimpleImageType type = new SimpleImageType();
        URL waterUrl = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type.setUrl(waterUrl);
        type.setTransparency(1F);
        type.setSize(20);
        type.setWidth(50);
        type.setHeight(50);
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void designationCropImageTypeTypeTest() throws Exception {
        DesignationCropImageType type = new DesignationCropImageType();
        type.setNewWatermarkWidth(100);
        type.setNewWatermarkHeight(100);
        URL waterUrl = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type.setUrl(waterUrl);
        type.setTransparency(0.8F);
        //无用属性
        type.setSize(0);
        type.setWidth(20);
        type.setHeight(20);
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void proportionalCropImageTypeTypeTest() throws Exception {
        ProportionalCropImageType type = new ProportionalCropImageType();
        URL waterUrl = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type.setUrl(waterUrl);
        type.setTransparency(1F);
        type.setSize(200);
        type.setWidth(20);
        type.setHeight(20);
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    @Test
    public void simpleRotateImageTypeTypeTest() throws Exception {
        SimpleRotateImageType type = new SimpleRotateImageType();
        type.setX(200);
        type.setY(200);
        type.setDegree(50F);
        URL waterUrl = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type.setUrl(waterUrl);
        type.setTransparency(0.8F);
        type.setSize(0);
        type.setWidth(20);
        type.setHeight(20);
        type.dealWatermark(read, graphics);
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }

    /**
     * 批量添加水印
     *
     * @throws IOException
     */
    @Test
    public void batchWatermarkTest() throws Exception {
        List<BaseWatermarkType> list = new ArrayList<>();
        RectangleBackgroundFontType type = new RectangleBackgroundFontType();
        type.setBackgroundTransparency(0.9F);
        type.setFontText("HELLO WORLD");
        type.setFontName("宋体");
        type.setFontStyle(0);
        type.setFontColor("#00FF27");
        type.setTransparency(0.7F);
        type.setSize(60);
        type.setWidth(20);
        type.setHeight(20);
        type.setBackGroundColor("#FF0000");
        list.add(type);
        ProportionalCropImageType type1 = new ProportionalCropImageType();
        URL waterUrl = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type1.setUrl(waterUrl);
        type1.setTransparency(1F);
        type1.setSize(200);
        type1.setWidth(60);
        type1.setHeight(60);
        list.add(type1);
        SimpleRotateImageType type3 = new SimpleRotateImageType();
        type3.setX(200);
        type3.setY(200);
        type3.setDegree(50F);
        URL waterUrl3 = AppTest.class.getClassLoader().getResource("watermarkImage.png");
        type3.setUrl(waterUrl3);
        type3.setTransparency(0.8F);
        type3.setSize(0);
        type3.setWidth(20);
        type3.setHeight(20);
        list.add(type3);
        list.forEach(x -> {
            try {
                x.dealWatermark(read, graphics);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        graphics.dispose();
        ImageIO.write(read, waterPath.substring(waterPath.lastIndexOf(".") + 1), new FileOutputStream(new File("D:/" + UUID.randomUUID() + waterPath.substring(waterPath.lastIndexOf(".")))));
    }
}
