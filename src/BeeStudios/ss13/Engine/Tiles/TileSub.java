package BeeStudios.ss13.Engine.Tiles;

/**
 * Created by Spartan 2 on 2017-08-06.
 */
public class TileSub {
    public String name = "";
    public int x = 0;
    public int y = 0;

    public TileSub(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public TileSub(int x, int y) {
        //this.name = name;
        this.x = x;
        this.y = y;
    }

    //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int[] GetCoords(){
        int[] intreturner = {this.getX(),this.getY()};
        return intreturner;
    }
    //
}
