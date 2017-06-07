package br.com.mj.dataminer.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    
    public static byte[] imageToByte(Image image) {

        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        try {
            ImageIO.write( imageToBufferedImage(image, image.getWidth(null), image.getHeight(null) ), "JPG", buff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toByteArray();
    }

    public static BufferedImage imageToBufferedImage(Image im, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
       
        return bi;
    }
    
}
