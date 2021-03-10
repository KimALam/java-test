package test.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

class ExtensionCheckByFileHeaderTest {
    public static void main(String[] args) {
        File file = new File("image.jpg");

        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            ImageReader reader = iter.next();
            System.out.println("Format: " + reader.getFormatName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
