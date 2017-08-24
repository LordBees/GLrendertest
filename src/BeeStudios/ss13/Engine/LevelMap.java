package BeeStudios.ss13.Engine;

//import javax.swing.text.Document;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
//import java.io.File;

/**
 * Created by Spartan 2 on 2017-08-10.
 */
public class LevelMap {
    //class for holding data on map data
    int[][] Table_TurfTiles;
    int[][] Table_StaticTiles;
    int[][] Table_CollisionMap;
    //## data for loading assets - texture sheets,snd etc..
    String[] sheet_table;
    //


    public LevelMap(String MapPath,Boolean INJAR) {
        if(INJAR){
            //assume present
            this.loaddatajar(MapPath);
        }
        else{
            //load externally
        }

    }

    //
    private void loadmapdata(){

    }

    private void loaddatajar(String Lpath){
        //get resources for loading map data
        this.XMLload();//init metadata
        InputStream Path_tileshetts_internal = getClass().getResourceAsStream(Lpath+"ResTiles.txt");//texture paths of tiles(assume all internal RestilesExt for external
        //get data for actual map
        InputStream Path_Mapturf = getClass().getResourceAsStream(Lpath+"map.txt");
        InputStream Path_collision = getClass().getResourceAsStream(Lpath+"collision.txt");//could do xml tree for textures





    }
    private void XMLload(){
        int[] whmap = new int[2];
        try{
        //File fXmlFile = new File("/Users/mkyong/staff.xml");
        InputStream fXmlFile  = getClass().getResourceAsStream("meta.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();
        //parsing data test
            NodeList nList = doc.getElementsByTagName("staff");
            for (int i=0;i< nList.getLength();i++){
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                }

            }


    } catch (Exception e) {
        e.printStackTrace();
    }
    //actually load data
        this.init_tiledata(whmap[0],whmap[1]);


    }
    private void init_tiledata(int MapWidthDim,int MapHeightDim){
        this.Table_TurfTiles    = new int[MapWidthDim][MapHeightDim];//[x][y](width,height)
        this.Table_StaticTiles  = new int[MapWidthDim][MapHeightDim];
        this.Table_CollisionMap = new int[MapWidthDim][MapHeightDim];
    }
}
