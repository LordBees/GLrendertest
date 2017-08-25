package BeeStudios.ss13.Engine.IO;

import org.lwjgl.system.CallbackI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Spartan 2 on 2017-08-06.
 */
public class File_IO_EXT {
    //

    //

    //
    public String[] getfileext(String loc){
        return new String[2];
    }

    public static BufferedImage loadImage(String loc) {
        try {
            //return ImageIO.read(MainClass.class.getResource(loc));
            //ImageIO.read(BeeStudios.ss13.getResource(loc));
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println(current + "/Res/" + loc);
            //System.out.println(ImageIO.read(new File(current + "\\Res\\" + loc)));
            return ImageIO.read(new File(current + "/Res/" + loc));
        } catch (IOException e) {
            //Error Handling Here
            System.out.println("error! image not loaded!");
            System.out.println("/Res/" + loc);
        }
        //System.out.println("xnull");
        return null;
    }

    public String getfiledataraw(String loc){
        try(BufferedReader br = new BufferedReader(new FileReader(loc))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            return  everything;
        }
        catch (IOException e){
            System.out.print("error file not found!");
        }
        return "";
    }
}
