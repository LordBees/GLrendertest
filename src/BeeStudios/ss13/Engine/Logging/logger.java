package BeeStudios.ss13.Engine.Logging;

/**
 * Created by Spartan 2 on 2017-08-24.
 */
public class logger {

    /*
    //String [] loggingpriorities = {"warning","severe","informational"};
    String [] loggingpriorities = {"info","warning","severe"};
    boolean log2file = false;
    boolean logenabled = true;
    String logfilepath = "";
    String logfilename = "LOGGING.txt";
    */
    // data for all setup
    private loggerconfigger log_settings;

    public logger(){
        log_settings = new loggerconfigger();
    }

    public loggerconfigger loggerconfigger_xport(){//returns all settings as a settings object
        return log_settings;
    }


    public void config(loggerconfigger loggersettings) {
        this.log_settings = loggersettings;
    }

    public void config(){


    }

    public void log_error(String severetype,String message) {//logs for events&errors
        log_error_internal(severetype,message);
    }
    public void log(String xtype,String message){//logs for events-currently same as log_error
        log_error_internal(xtype,message);
    }
    public void logsub(String message){
        log_error_internal("",message);
    }//logs woih blank box
    public void logdone(String message){
        //String logmsg = "[" + xtype + "]  " + message;
        //String logmsg = "|" + message + " Done";
        String logmsg = "[" + "DONE" + "]  " + message;
        System.out.println(logmsg);
        if (this.log_settings.isLog2file()) {
            tofile(logmsg);
        }
        logmsg = "";

    }

    public void log_error(int severity,String message){
        String ltype = "INVALIDTYPE";
        if (!(severity<0)||!(severity>log_settings.getLoggingpriorities_length())){//if not less than 0 or larger than array then lookup type
            ltype = log_settings.getLoggingpriorities()[severity];
        }
        log_error_internal(ltype,message);

        //try {
        //    log_error_internal(loggingpriorities[severity],message);
        //}
        //catch (Exception e){
        //    throw new Exception("[FATAL]    invalid log type")
        //}

    }

    private void log_error_internal(String xtype,String message) {
        if (this.log_settings.isLogenabled()) {
            String logmsg = "[" + xtype + "]  " + message;
            System.out.println(logmsg);
            if (this.log_settings.isLog2file()) {
                tofile(logmsg);
            }
            logmsg = "";
        }
    }
    private void tofile(String st_line){


    }

    public void log_error_consoleonly(String xtype,String message) {
        boolean temp = this.log_settings.isLog2file();
        this.log_settings.setLog2file(false);
        log_error_internal(xtype,message);
        this.log_settings.setLog2file(temp);
    }

}
/*
public loggerconfigger process_x(){
        loggerconfigger l = new loggerconfigger();
        l.setLoggingpriorities();
        l.setLog2file();
        l.setLogenabled();
        l.getLogfilepath();
        l.getLogfilename();
    }

    public void x(){
        loggerconfigger l = new loggerconfigger();
        l.setLoggingpriorities();
        l.setLog2file();
        l.setLogenabled();
        l.getLogfilepath();
        l.getLogfilename();

        loggingpriorities = loggersettings.loggingpriorities;
        log2file =          loggersettings.log2file;
        logenabled =        loggersettings.logenabled;
        logfilepath =       loggersettings.logfilepath;
        logfilename =       loggersettings.logfilename;

    }
 */