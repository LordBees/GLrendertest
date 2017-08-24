package BeeStudios.ss13.testing;

/**
 * Created by Spartan 2 on 2017-08-02.
 */
public class DisplayRenderTile_manager {
    public int xtiles = 0;
    public int ytiles = 0;
    public int Tpreloadbuffer = 1;

    //public int[] xtilesdisp = new int[ytiles];
    //public int[] ytilesdisp
    public int[][] disp_ids;// = new int[this.xtiles][this.ytiles];//displayed tiles// CHECK THIS for whether the array is initialised propery
    public int[][] disp_xbuff;// = new int[this.xtiles][this.Tpreloadbuffer];//## 2 columns for preloading next tiles could merge into tiletable
    public int[][] disp_ybuff;// = new int[this.Tpreloadbuffer][this.ytiles];

    public int[][] disp_scalingdatax;
    public int[][] disp_scalingdatay;
    //
    public DisplayRenderTile_manager(int xtile,int ytile,boolean buffered ){
        this.xtiles = xtile;
        this.ytiles = ytile;
        this.init_manager();
    }
    //
    public void init_manager(){//(int[][] data){
        this.disp_ids = new int[this.xtiles][this.ytiles];
    }

    public void init_buffer(int preloadtiledepth){

    }
    public void cleanup_manager(){
        this.clear_plane();

    }


    //texture methods
    public void loadplane(int[][]data){
        this.disp_ids = data;
    }

    public void loadplane(int[]data,int bufferid,boolean xory){//loads data to plane for display //int[][] data
        int xlen = 0;

        if (xory){
            xlen = this.xtiles;
        }
        else {
            xlen = this.ytiles;
        }

        if(xory){//clould join
            for (int x = 0; x <= xlen; x++) {
                this.disp_ids[x][bufferid] = data[x];
            }
        }
        else{
            for (int x = 0; x <= xlen; x++) {
                this.disp_ids[bufferid][x] = data[x];
            }
        }
    }
    public int get_planedata(int x,int y){
        //System.out.println();
        if (x>this.xtiles){
            return -1;
        }
        if (y>this.ytiles){
            return -1;
        }
        //System.out.print("got data at |"+x+","+y+"IS >");
        //System.out.println(disp_ids[x][y]);
        return disp_ids[x][y];
    }
    public int setplanedata(int x,int y,int Texid){
        if (x>this.xtiles){
            return 0;
        }
        if (y>this.ytiles){
            return 0;
        }
        disp_ids[x][y] = Texid;
        //System.out.print("set data at |"+x+","+y+"TO >");
        //System.out.println(disp_ids[x][y]);
        return 1;
    }

    public void clear_plane(){
        this.disp_ids= new int[0][0];
    }
    //moving methods

    //buffer methods
    public void clear_buffer(String dir){
        //valid UDLR A ALL
        if(dir == "A"){
            this.clear_buffer("U");
            this.clear_buffer("D");
            this.clear_buffer("L");
            this.clear_buffer("R");

        }
        else if(dir == "U"){

        }
        else if(dir == "D"){

        }
        else if(dir == "L"){

        }
        else if(dir == "R"){

        }

    }
    public void shiftplane(int xdir,int ydir){//shifts plane for view and copies to buffer if needed

    }

    //methods for copying to buffer plane
    public void copybuff2plane(int[]stripedata,boolean xory,String side){


    }

    public void copyplane2buff(int[]stripedata,int xory){
}




    public void loadbuffer(int[][] bufferdata,int xory){//loads data to buffer

    }
    public void calcbufferdata(int dispx, int dispy) {//uses x and ypos to work out boundingcalc for buffer

    }

    public int get_bufferdata(int x,int y){
        //System.out.println();
        if (x>this.xtiles){
            return -1;
        }
        if (y>this.ytiles){
            return -1;
        }
        //System.out.print("got data at |"+x+","+y+"IS >");
        //System.out.println(disp_ids[x][y]);
        return disp_ids[x][y];
    }
    public int setbufferdata(int x,int y,int Texid){
        if (x>this.xtiles){
            return 0;
        }
        if (y>this.ytiles){
            return 0;
        }
        disp_ids[x][y] = Texid;
        //System.out.print("set data at |"+x+","+y+"TO >");
        //System.out.println(disp_ids[x][y]);
        return 1;
    }
}
