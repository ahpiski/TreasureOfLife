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
        if(outputPath == "") outputPath = "./output.png";
        ClassLoader classLoader = imageEdit.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("template.png");
        BufferedImage image = ImageIO.read(inputStream);
        Graphics2D barDraw = image.createGraphics();
        Graphics2D percentWrite = image.createGraphics();
        barDraw.setColor(Color.GRAY);
        percentWrite.setColor(Color.GRAY);
        percentWrite.setFont(Font.decode(null).deriveFont(Font.PLAIN, 20));

        calculateDate cd = new calculateDate();
        if(cd.checkDate(dateStr) != ""){
            System.err.println(cd.checkDate(dateStr));
            System.exit(1);
        }
        double[] statusBars = cd.barsStatus(cd.SeperatedDate(dateStr), cd.yearRemaining(dateStr));
        //Drawing bars
        for(int i = 0 ; i < 10 ; i++){
            if(statusBars[i] != 0) {
                double barLengh = 217 * (statusBars[i] / 100);
                barDraw.fillRect(50, 161 + i * 42, (int) barLengh, 5);
                percentWrite.drawString(String.format("%.2f", statusBars[i]) , 50 + (int) barLengh ,169 + i * 42 );
            }
        }
        ImageIO.write(image , "png" , new File(outputPath));
    }
}
