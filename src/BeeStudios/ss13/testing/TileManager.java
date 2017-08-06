package BeeStudios.ss13.testing;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
public class TileManager {
    //
    private int[] tdat = {};
    //

    //
    public void loadtile(String fname){


        fname = "test.txt";
        FileInputStream Filex_in = null;
        try{
            //String x = new java.io.File(".").getCanonicalPath();
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println(current + "\\Res\\" + fname);
            Filex_in = new FileInputStream(current + "\\Res\\" + fname);

            int c;
            String cBuff = "";

            while ((c = Filex_in.read()) != -1) {

                cBuff += String.valueOf((char)c);//char)c
                //System.out.println((char)c);
            }
            System.out.println(cBuff);

        }catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }  catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        finally {
            if (Filex_in!= null){
                try {
                    Filex_in.close();
                }
                catch (IOException e ){
                    throw new RuntimeException("fileioexeption"+e);
                }
            }
        }
    }
}
