package BeeStudios.ss13.Engine;

/**
 * Created by Spartan 2 on 2017-08-10.
 */
public class misc_array {
    //array
    private String [] xdat;

    public void append(String element){
        String[] temp  = new String[this.xdat.length+1];
        for (int x=0;x<this.xdat.length;x++){
            temp[x] = this.xdat[x];
        }
        temp[temp.length] = element;
        this.xdat = temp;

    }

}
