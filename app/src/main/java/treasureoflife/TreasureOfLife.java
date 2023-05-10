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
    private String dateOfBirth = "";

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
          imageedit.createImg(treasureOfLife.dateOfBirth, treasureOfLife.CreateFileIfPathIsDir(treasureOfLife.savePath));
          System.exit(0);
        }

        else if(treasureOfLife.savePath == "" && treasureOfLife.dateOfBirth == ""){
          System.out.println("use -h or --help to get help :)");
          System.exit(1);
        }
        else{
          if(!treasureOfLife.checkPathCorrect(treasureOfLife.savePath)){
            System.err.println("output path format incorrect or path is not acceptable !");
          }
          if(!treasureOfLife.checkDateCorrect(treasureOfLife.dateOfBirth)){
            System.err.println("birth date format incorrect !\nIt must have eight digits and be in the form of ddmmyy\n Example : 31032003");
          }
          System.exit(1);
        }
        
    }

    private void printhelp(){
        System.out.println("This software shows your age as a graph.\n" +
                "The output must be a common image format.(png , jpeg , ...)\n" +
                "Switches : \n-o output path Ex: /home/age.png\n-d date of birh\n date of birh should be ddmmyy formated!");
    }

    private boolean checkDateCorrect(String Date){
        if(Date.length() != 8) return false;
        return true;
    }
    private boolean checkPathCorrect(String pathString) throws IOException{
      Path path = Paths.get(pathString);
      File file = new File(pathString);
      return Files.isWritable(path) || file.createNewFile();
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