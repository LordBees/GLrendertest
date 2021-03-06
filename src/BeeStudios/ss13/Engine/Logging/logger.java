package BeeStudios.ss13.Engine.Logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
    public void config_fromfile(String loggerxml){//finish this
        if (loggerxml == ""){
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            loggerxml = s+"\\"+"Res\\Defaults\\"+"LoggerDefault.xml";//change to jar location
        }

        try {

            File fXmlFile = new File(loggerxml);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("LoggerSettings");

            //System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //System.out.println("Staff id : " + eElement.getAttribute("id"));
                    //System.out.println("First Name : " + eElement.getElementsByTagName("Writeable").item(0).getTextContent());
                    //System.out.println("Last Name : " + eElement.getElementsByTagName("Enabled").item(0).getTextContent());
                    //System.out.println("Nick Name : " + eElement.getElementsByTagName("Filepath").item(0).getTextContent());
                    //System.out.println("Salary : " + eElement.getElementsByTagName("LogFileName").item(0).getTextContent());


                    //my code
                    loggerconfigger templogger = new loggerconfigger();


                    if ( eElement.getElementsByTagName("Enabled").item(0).getTextContent() == "True"){
                        templogger.setLogenabled(true);
                    }
                    else{
                        templogger.setLogenabled(false);
                    }

                    if ( eElement.getElementsByTagName("Writeable").item(0).getTextContent() == "True"){
                        templogger.setLog2file(true);
                    }
                    else{
                        templogger.setLog2file(false);
                    }

                    templogger.setLogfilepath(eElement.getElementsByTagName("Filepath").item(0).getTextContent());
                    templogger.checkpath();
                    templogger.setLogfilename(eElement.getElementsByTagName("LogFileName").item(0).getTextContent());
                    //templogger.setLogenabled();
                    //templogger.setLog2file();
                    //templogger.setLogfilepath();
                    //templogger.setLogfilename();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            //String data = " This is new content";
            String xpathname = this.log_settings.getLogfilepath()+"\\"+this.log_settings.getLogfilename();
            //System.out.print(xpathname);
            File file = new File(xpathname);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(st_line+"\n");

            //System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }



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