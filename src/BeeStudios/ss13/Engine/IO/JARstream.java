package BeeStudios.ss13.Engine.IO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Spartan 2 on 2017-08-04.
 */
public class JARstream {
    //

    //

    //
    public BufferedImage getjardata_image(String fpathlocal){
        // BufferedImage image =ImageIO.read(getClass().getResourceAsStream(loc));
        //BufferedImage image = ImageIO.read(getClass().getResourceAsStream(fpathlocal));
        //return image;
        System.out.println("gJD_i");
        try {
            BufferedImage image =ImageIO.read(getClass().getResourceAsStream(fpathlocal));
            return image;
        } catch (IOException e) {
            //Error Handling Here
            System.out.println("error! image not loaded!");
            System.out.println("ERROR: " + fpathlocal);
        }
        //System.out.println("xnull");
        return null;
    }

    public BufferedReader getjardata_file(String fpathlocal){
        System.out.println("gJD_f");
        InputStream in = getClass().getResourceAsStream(fpathlocal);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader;
    }
}
