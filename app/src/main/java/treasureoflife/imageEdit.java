package treasureoflife;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.io.InputStream;
import treasureoflife.calculateDate;

public class imageEdit {
    public void createImg(String dateStr ,String outputPath) throws IOException , Exception {

        ClassLoader classLoader = imageEdit.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("template.png");
        BufferedImage image = ImageIO.read(inputStream);
        Graphics2D barDraw = image.createGraphics();
        Graphics2D percentWrite = image.createGraphics();
        barDraw.setColor(Color.GRAY);
        percentWrite.setColor(Color.GRAY);
        percentWrite.setFont(Font.decode(null).deriveFont(Font.PLAIN, 20));

        calculateDate calculatedate = new calculateDate();
        if(calculatedate.checkDate(dateStr) != ""){
            System.err.println(calculatedate.checkDate(dateStr));
            System.exit(1);
        }
        double[] statusBars = calculatedate.barsStatus(calculatedate.timeHasPassed(dateStr), calculatedate.yearRemaining(dateStr));
        ImageIO.write(image , "png" , new File(outputPath));
    }
}
