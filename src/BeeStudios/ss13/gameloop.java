package BeeStudios.ss13;

import BeeStudios.ss13.testing.TileManager;

/**
 * Created by Spartan 2 on 2017-07-25.
 */
public class gameloop {

    //
    int current = 0;



    public void gloop(){
        double secsPerUpdate = 1.0d / 30.0d;
        double previous = getTime();
        double steps = 0.0;
        while (true) {
            double loopStartTime = getTime();
            double elapsed = loopStartTime - previous;
            previous = current;
            steps += elapsed;
            handleInput();
            while (steps >= secsPerUpdate) {
                updateGameState();
                steps -= secsPerUpdate;
            }
            render();
            sync(current);
        }
    }
    private void sync(double loopStartTime) {
        float loopSlot = 1f / 50;
        double endTime = loopStartTime + loopSlot;
        while(getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {}
        }
    }
    private double getTime(){
        return 0.0;
    }
    private void handleInput(){

    }
    private void updateGameState(){

    }
    private void render(){

    }
    public static void main(String[] args) {
        TileManager tx = new TileManager();
        tx.loadtile("");
        //return 1010;

    }
}
