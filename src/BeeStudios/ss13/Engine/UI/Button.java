package BeeStudios.ss13.Engine.UI;

/**
 * Created by Spartan 2 on 2017-08-04.
 */
import java.awt.*;
import java.nio.DoubleBuffer;

//import org.lwjgl.input.Mouse;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

public class Button {
    int xx,yy,ww,hh;
    String t;
    float R = 0.7F,G = 0.7F,B = 0.7F;
    float oR = 1F,oG = 1F,oB = 1F;
    boolean mouseOverEnabled = false, visible = true;
    //added to fix
    long window;
    DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
    DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);

    public Button(int x, int y, int w, int h,String text, long window){
        xx=x;
        yy=y;
        ww=w;
        hh=h;
        t=text;
        this.window = window;
    }
    public void draw(){
        System.out.println(isMouseOver());
        if(visible){
            if(isMouseOver() && mouseOverEnabled)
                GL11.glColor3d(oR,oG,oB);
            else
                GL11.glColor3d(R,G,B);
            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2d(xx-1, yy+1);
                GL11.glVertex2d(xx+ww+1, yy+1);
                GL11.glVertex2d(xx+ww+1, yy+hh-1);
                GL11.glVertex2d(xx-1, yy+hh-1);
            }
            GL11.glEnd();
            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2d(xx+1, yy-1);
                GL11.glVertex2d(xx+ww-1, yy-1);
                GL11.glVertex2d(xx+ww-1, yy+hh+1);
                GL11.glVertex2d(xx+1, yy+hh+1);
            }
            GL11.glEnd();

//		Font awtFont = new Font("Arial", Font.BOLD, 16);
//		TrueTypeFont font = new TrueTypeFont(awtFont, true);
//		font.drawString(xx,yy, t.toUpperCase());
        }
    }

    public boolean isClicked(){
        Rectangle r = new Rectangle();
        r.x=xx;
        r.y=yy;
        r.width=ww;
        r.height=hh;

        glfwGetCursorPos(this.window, this.b1, this.b2);
        System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
        //int MouseX = (int)b1.get(0);
        //return r.contains(new Point(Mouse.getX(), 800-Mouse.getY()))&&Mouse.isButtonDown(0);
        return r.contains(new Point((int)this.b1.get(0), 800-(int)this.b2.get(0))) && (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_1) == 1);
    }
    public boolean isMouseOver(){
        Rectangle r = new Rectangle();
        r.x=xx;
        r.y=yy;
        r.width=ww;
        r.height=hh;

        glfwGetCursorPos(this.window, this.b1, this.b2);
        //System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
        //System.out.println(new Point((int)this.b1.get(0), 800-(int)this.b2.get(0)));
        //return r.contains(new Point(Mouse.getX(), 800-Mouse.getY()));
        return r.contains(new Point((int)this.b1.get(0), 800-(int)this.b2.get(0)));
    }
    public void setColor(float r, float g, float b){
        R = r;
        G = g;
        B = b;
    }
    public void setMouseOverColor(float r, float g, float b){
        oR = r;
        oG = g;
        oB = b;
    }
    public void enableMouseOver(boolean b){
        mouseOverEnabled = b;
    }
    public void setVisible(boolean b){
        visible = b;
    }


}
