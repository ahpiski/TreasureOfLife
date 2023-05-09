package treasureoflife;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import treasureoflife.imageEdit;
class TreasureOfLife{
    @Parameter(
      names = {"--help","-h"},
      description = "need help",
      required = false
    )
    private boolean ishelp = false;

    @Parameter(
      names = {"-d"},
      description = "date of birth",
      required = false
    )
    private int dateOfBirth = 0;

    @Parameter(
      names = {"-o"},
      description = "saving path",
      required = false
    )
    private String savePath = "." ;

    public static void main(String[] argv) throws IOException , Exception {
        TreasureOfLife treasureOfLife = new TreasureOfLife();
        JCommander.newBuilder()
            .addObject(treasureOfLife)
            .build()
            .parse(argv);
        if(treasureOfLife.ishelp){
            treasureOfLife.printhelp();
            System.exit(0);
        }
        else if(treasureOfLife.checkPathCorrect(treasureOfLife.savePath) && treasureOfLife.checkDateCorrect(treasureOfLife.dateOfBirth)){
          imageEdit imageedit = new imageEdit();
          imageedit.createImg(Integer.toString(treasureOfLife.dateOfBirth) , treasureOfLife.CreateFileIfPathIsDir(treasureOfLife.savePath));
          System.exit(0);
        }

        else if(treasureOfLife.savePath == "" && treasureOfLife.dateOfBirth == 0){
          System.out.println("use -h or --help to get help :)");
          System.exit(1);
        }
        else{
          if(!treasureOfLife.checkPathCorrect(treasureOfLife.savePath)){
            System.err.println("output path format incorrect or path is not acceptable !");
          }
          if(!treasureOfLife.checkDateCorrect(treasureOfLife.dateOfBirth)){
            System.err.println("birth date format incorrect ! \n(changeme)");
          }
          System.exit(1);
        }
        
    }

    private void printhelp(){
        System.out.println("change me");
    }

    private boolean checkDateCorrect(int Date){
        if(String.valueOf(Date).length() != 8) return false;
        return true;
    }
    private boolean checkPathCorrect(String pathString){
      Path path = Paths.get(pathString);
      return Files.isWritable(path);
    }
    private String CreateFileIfPathIsDir(String pathString) throws IOException {
        File file = new File(pathString);
        if(file.isDirectory()){
            File outputFile = new File(file , "output.png");
            outputFile.createNewFile();
            return outputFile.getPath();
        }
        return pathString;
    }
}