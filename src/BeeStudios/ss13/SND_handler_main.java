//package Assignment.game;
package BeeStudios.ss13;

import sun.security.pkcs11.wrapper.Constants;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Created by Spartan 2 on 2017-05-25.
 */
//sound code
public class SND_handler_main {
    //public class Sound {
    //clip
    private Clip clipsnd;

    private String sndpath = "\\res\\snd\\";//filepath to sound so only have to specify filename
    private boolean isplaying = false;//is playing currently?
    private boolean isbgm = false;//is background
    private String filen = "";//current filename
    private String lfilen = "";//last filename

    //SND_handler_main(String Xfile,boolean xisbgm) {
    public void load(String Xfile,boolean xisbgm){

        if (this.lfilen.equals(Xfile)&&(this.isbgm = xisbgm)){//no point in reloading if state same
            System.out.println("skipped loading as args same");
        }
        else {
            //if(clipsnd.isRunning()) {
            //    this.clipsnd.stop();
            //    this.clipsnd.flush();
            //}

            this.isbgm = xisbgm;
            this.lfilen = this.filen;
            this.filen = Xfile;


            try {
                /*
                //getClass().getResourceAsStream(loc)
                String current = new java.io.File(".").getCanonicalPath();
                //current = "\\\\KFILESTUDENT01\\StudentHome15\\1\\10740071\\downloads\\gamecode\\gamecodegit-01\\U15A2-Gamecode-master";
                //current = "\\\\KFILESTUDENT01\\StudentHome15\\1\\10740071\\downloads\\gamecode-master-TEMP\\U15A2-Gamecode-master";
                System.out.println(current + "\\res\\" + Xfile);
                File file = new File(current + "\\res\\" + Xfile);
                */

                //File file = new File(getClass().getResource(Xfile).toString());
                //new Media(MyApplicationClass.class.getResource("/resources/test.wav").toURI().toString())
                //file ClassLoader.getResourceAsStream ("some/pkg/resource.properties");
                if (true) {
                    InputStream in = getClass().getResourceAsStream(Xfile);
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                    //InputStream ISFiledata = getClass().getResourceAsStream(reader);
                    //URL url = Constants.class.getResource("/sounds/file.wav");
                    //URL url = Constants.class.getResource(Xfile);

                    //ClassLoader classLoader = getClass().getClassLoader();
                    //File file = new File(classLoader.getResource(Xfile).getFile());


                    Clip clipsnd = AudioSystem.getClip();
                    //AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(Xfile));
                    AudioInputStream ais = AudioSystem.getAudioInputStream(in);
                    clipsnd.open(ais);
                    this.clipsnd = clipsnd;
                } else {
                    throw new RuntimeException("Sound: file not found: " + Xfile);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Sound: Malformed URL: " + e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            } catch (IOException e) {
                throw new RuntimeException("Sound: Input/Output Error: " + e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException("Sound: Line Unavailable: " + e);
            }
        }
    }
    public String resolvename(){
        return this.sndpath+this.filen;//test
    }

    public void play() {
    //}
    //public void s(){
        //this.isplaying = true
        clipsnd.setFramePosition(0);  // Must always rewind!
        if (this.isbgm) {
            clipsnd.loop(-1);
        }
        clipsnd.start();
    }
    public void looptimes(int xtimes){
        clipsnd.loop(xtimes);
    }
    public void stop(){
        clipsnd.stop();
    }
    public boolean issameas(String newtrk){
        if (filen.equals(newtrk)){
            return true;
        }
        else {
            return false;
        }
    }
}

