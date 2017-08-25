package BeeStudios.ss13.Engine.Logging;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Spartan 2 on 2017-08-25.
 */
public class loggerconfigger {
    private String [] loggingpriorities = {"info","warning","severe"};
    private boolean log2file = true;//false;
    private boolean logenabled = true;
    private String logfilepath = "";
    private String logfilename = "LOGGING.txt";
    private int loggingpriorities_length;

    public loggerconfigger(){
        this.loggingpriorities_length = loggingpriorities.length;
        if (this.logfilepath == ""){
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            this.logfilepath = s;
            //System.out.println("Current relative path is: " + s);
            //throw new RuntimeException(s+this.logfilename);//+"\\"+ added in filewriter


        }
    }


    public int getLoggingpriorities_length(){
        return loggingpriorities_length;
    }
    public String[] getLoggingpriorities() {
        return loggingpriorities;
    }

    public void setLoggingpriorities(String[] loggingpriorities) {
        this.loggingpriorities = loggingpriorities;
        this.loggingpriorities_length =loggingpriorities.length;
    }

    public boolean isLog2file() {
        return log2file;
    }

    public void setLog2file(boolean log2file) {
        this.log2file = log2file;
    }

    public boolean isLogenabled() {
        return logenabled;
    }

    public void setLogenabled(boolean logenabled) {
        this.logenabled = logenabled;
    }

    public String getLogfilepath() {
        return logfilepath;
    }

    public void setLogfilepath(String logfilepath) {
        this.logfilepath = logfilepath;
    }

    public String getLogfilename() {
        return logfilename;
    }

    public void setLogfilename(String logfilename) {
        this.logfilename = logfilename;
    }
}
