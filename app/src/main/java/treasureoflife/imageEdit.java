package treasureoflife;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.imageio.ImageIO;
import treasureoflife.calculateDate;

public class imageEdit {
    public void createImg(String templatePath ,String dateStr) throws IOException , Exception {

        BufferedImage image = ImageIO.read(new File(templatePath));
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
        
    }
}
