package BeeStudios.ss13.GAME_MAIN;

//import org.lwjgl.Version;

import BeeStudios.ss13.DisplayRenderTile_manager;
import BeeStudios.ss13.SND_handler_main;
import BeeStudios.ss13.Settings.Settings_class;
import BeeStudios.ss13.Texmanager;
import BeeStudios.ss13.TextureLoader;
import BeeStudios.ss13.Tiles.Sheetdata;
import BeeStudios.ss13.UI.Button;
import BeeStudios.ss13.UI.UI_ButtonClickable;
import org.lwjgl.*;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
public class Game_Main {
    //
    G_VARS sdfg = new G_VARS();

    //config
        Settings_class CFG = new Settings_class();
    //managers
        Texmanager TexMan = new Texmanager();
        TextureLoader TexLdr = new TextureLoader();//moved to post init EDIT:was
        DisplayRenderTile_manager TexD = new DisplayRenderTile_manager(10,10,false);
        SND_handler_main BGMX = new SND_handler_main();

    //GLstuff
        //## The window handle
        private long window;
        //##

    //

    //
    public void run(int mode){
        System.out.print("\n|Loading: opengl\n");
        setup_OPENGL_LWJGL();
        System.out.print("|Loading: glmisc");
        Setup_misc_opengl();
        System.out.print("\n|GL Done \n");
        if (mode == 0){
            Game_Main_Loop();
        }
        else if(mode == 2){
            Test_2();
        }
        else{
            System.out.println("TESTINGMODE!");
            test();
        }
        cleanup();
        System.out.println("END_game!");
    }

    public void cleanup(){
        System.out.print("\ncleaning up TexMan");
        this.TexMan.cleartexturebuffer();
        System.out.print(".");
        this.TexD.cleanup_manager();
        System.out.print(". Done \n");

        //gl cleanup
        System.out.print("cleaning up Cleaning opengl");
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(this.window);
        glfwDestroyWindow(this.window);
        System.out.print(".");

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        System.out.print(". Done \n");

    }

    public void setup_OPENGL_LWJGL(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        //this.window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
        if (CFG.Fullscreen){
            this.window = glfwCreateWindow(CFG.Disp_xres, CFG.Disp_yres, CFG.WinTitle,glfwGetPrimaryMonitor(),NULL);
        }
        else {
            this.window = glfwCreateWindow(CFG.Disp_xres, CFG.Disp_yres, CFG.WinTitle, NULL, NULL);
        }

        if ( this.window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            if ( key == GLFW_KEY_Z && action == GLFW_RELEASE ){
                //System.out.println('a');
                if (sdfg.Player_Z == 0){
                    sdfg.Player_Z = 1;
                }
                else{
                    sdfg.Player_Z = 0;
                }
            }

        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(this.window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    this.window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(this.window);
        // Enable v-sync
        glfwSwapInterval(1);

        //enable2d
        //glEnable(GL_TEXTURE_2D);

        // Make the window visible
        glfwShowWindow(this.window);

    }

    public void Setup_misc_opengl(){
        //GLFWImage xicon = TexLdr.loadImage("\\icon\\sad_pepe.png");
        //glfwSetWindowIcon(this.window,1,xicon);
        //int tx = TexLdr.BindResourceTex("ASCII_titlepic.PNG");
        //TexLdr.loadImage()

    }
    public void test(){
        //TexMan.addtexturetobuffer(TexLdr.BindResourceTex("ASCII_titlepic.PNG"));


        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        //this.TexLdr = new TextureLoader();


        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(this.window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(this.window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }

    }
    public void Test_2(){
        System.out.println("mainloopt2");
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        // Set the clear color
        //glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        glClearColor(CFG.Disp_clearcolour[0], CFG.Disp_clearcolour[1], CFG.Disp_clearcolour[2], CFG.Disp_clearcolour[3]);

        //int tx = TexLdr.BindResourceTex("ASCII_titlepic.PNG");
        //int tx = TexLdr.BindResourceTex("Test_sample.png");
        //int tx2 = TexLdr.BindResourceTex("construction_floors.dmi");
        //int tx2 = TexLdr.BindResourceTex("construction_floors.dmi");
        int tx2 = TexLdr.BindResourceTex("/turf/floors.dmi");//"turf\\floors.dmi"

        //int tx2 = TexLdr.BindResourceTex("Test_sample - Copy.png");
        //int tx2 = TexLdr.BindResourceTex("test_sample.png");
        int tx = TexLdr.BindResourceTex("/Test/tiletes.png");
        //BufferedImage tdat = TexLdr.loadImage("Test_sample.png");
        //System.out.println(tdat.createGraphics());
        //int tx = TexLdr.loadTexture(tdat);

        System.out.println(TexD.setplanedata(0,0,tx));
        //Sys
        //if(true){
        //    throw new RuntimeException("");
        //}

        float tdf = 0.0f;
        float[] tx2_dim = {1183,1183};//{704,704};
        float[] tx2_tpos = {1,1};
        float[] tx2_tscale = {8,8};
        float[] tx2_xyz = {0.025f,0.025f,0};
        //override callback earlier
        glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            if ( key == GLFW_KEY_X && action == GLFW_RELEASE ) {
                tx2_tpos[0] = tx2_tpos[0] + 1;
            }
            if ( key == GLFW_KEY_S && action == GLFW_RELEASE ) {
                tx2_tpos[1] = tx2_tpos[1] + 1;
            }
            if ( key == GLFW_KEY_Z && action == GLFW_RELEASE ) {
                tx2_tpos[0] = tx2_tpos[0] - 1;
            }
            if ( key == GLFW_KEY_A && action == GLFW_RELEASE ) {
                tx2_tpos[1] = tx2_tpos[1] - 1;
            }
        });
        BGMX.load("/snd/untitled3a.wav",true);
        //BGMX.load("/snd/testdownconvert2.ogg",true);
        BGMX.play();
        while (!glfwWindowShouldClose(this.window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            //System.out.println("tex= "+tx);

            //System.out.println(TexD.get_planedata(0,0));

            //TexLdr.drawtex_QUAD_args(new int[1],new int[1],TexD.get_planedata(0,0),0.0f,0.0f,1.0f,1.0f);//-0.5f
            //float[] tx2_dim = {64,64};
            //float[] tx2_tpos = {2,2};
            //float[] tx2_tscale = {1,1};

            TexLdr.drawtex_QUAD_args2(tx2,tx2_dim,tx2_tpos,32,tx2_tscale,false,CFG,tx2_xyz,false);
            tx2_xyz[0]= tx2_xyz[0]+0.01f;//0.025f;
            tx2_xyz[1]= tx2_xyz[1]+0.01f;//0.025f;

            if (tx2_xyz[0]>1.25){
                tx2_xyz[0] = -1.250f;
                tx2_xyz[1] = -1.250f;
                tx2_tpos[0] = tx2_tpos[0]+1;
            }
            if(tx2_tpos[0]>22){
                tx2_tpos[0]=0;
                tx2_tpos[1] = tx2_tpos[1] +1;
            }
            if (tx2_tpos[1]>22){
                tx2_tpos[1] = 0;
            }
            //

            //was glrender stuff here moved to glfff txt
            glfwSwapBuffers(this.window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void Game_Main_Loop(){
        System.out.println("mainloop");
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);
        if(CFG.ORTHOMAIN){// ortho space [(0,0 = top left]
            glOrtho( 0.f, CFG.Disp_xres, CFG.Disp_yres, 0.f, 0.f, 1.f );
        }

        // Set the clear color
        //glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        glClearColor(CFG.Disp_clearcolour[0], CFG.Disp_clearcolour[1], CFG.Disp_clearcolour[2], CFG.Disp_clearcolour[3]);

        // load assets
        DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
        //Button butontest = new Button(-1,0,10,1,"test£",this.window);
        int[] taz = {0,255,0};
        float[][] tax = new float[2][2];
        tax[0][0] = 0;
        tax[0][1] = 0;
        tax[1][0] = 0.5f;
        tax[1][1] = 0.5f;
        UI_ButtonClickable butontest = new UI_ButtonClickable(taz,new String[3],tax,true,true);


        int[] tx2_dim = {1184,1184};//{64,64};//{1183,1183};//{704,704};
        int[] tx3_dim = {704,704};
        float[] tx2_tpos = {1,1};
        float[] tx2_tscale = {8,8};
        float[] tx2_xyz = {0.025f,0.025f,0};
        int tx2 = TexLdr.BindResourceTex("/turf/floors.dmi");//"turf\\floors.dmi"
        int tx3 = TexLdr.BindResourceTex("/turf/construction_floors.dmi");//"turf\\floors.dmi"
        //int tx2 = TexLdr.BindResourceTex("/Test/tiletes.png");//"turf\\floors.dmi"
        Sheetdata st2;
        Sheetdata sta = new Sheetdata(tx2,"",tx2_dim[0],tx2_dim[1],32,32);
        Sheetdata stb = new Sheetdata(tx3,"",tx3_dim[0],tx3_dim[1],32,32);

        //game loop
        while (!glfwWindowShouldClose(this.window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            ///
            glfwGetCursorPos(window, b1, b2);
            System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
            if (sdfg.Player_Z ==0){
                st2 = sta;

            }
            else{
                st2 = stb;
            }
           // Button butontest = new Button(-1,0,10,1,"test£",this.window);
            //butontest.setColor(0,255,255);
            //butontest.draw();

            //butontest.setVisible(true);
            //
            //for     (int x=1;x<=(CFG.Disp_xres/32);x++){
            //    for (int y=1;y<=(CFG.Disp_yres/32);y++){
            //        TexLdr.Drawdatafromtilesheet_quick(st2,x,y,x*st2.tilewidth,y*st2.textureheight);

            //   }
            //}
            //working out disp res

            for     (int x=0;x<st2.numtiles_x;x++){
                for (int y=0;y<st2.numtiles_y;y++){
                    TexLdr.Drawdatafromtilesheet_quick(st2,x+1,y+1,(x*32)+64,(y*32)+64);
                }
            }
            //TexLdr.Drawdatafromtilesheet_quick(st2,1,1,0,0);
            //TexLdr.dr(st2,0,64,tx2);

            System.out.println(st2.texturewidth);
            /*
            float[][] xtoi = butontest.makesquarecoords(tax);
            System.out.print("\n");
            System.out.println("x : " + xtoi[0][0] + ", y = " + xtoi[1][0]);
            System.out.println("x : " + xtoi[0][1] + ", y = " + xtoi[1][1]);
            System.out.println("x : " + xtoi[0][2] + ", y = " + xtoi[1][2]);
            System.out.println("x : " + xtoi[0][3] + ", y = " + xtoi[1][3]);

            System.out.print("\n");
            */

            /*
            System.out.print("\n:");
            System.out.print(xtoi[0][0]);
            System.out.print("|");
            System.out.print(xtoi[1]);
            System.out.print("|");
            System.out.print(xtoi[2]);
            System.out.print("|");
            System.out.print(xtoi[3]);
            System.out.print("|");
            */
            //butontest.XRender();


            LOOP_processinput();

            LOOP_updatestate();

            LOOP_render();


            //was glrender stuff here moved to glfff txt
            glfwSwapBuffers(this.window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }


    }
    private void LOOP_processinput(){
        //process input from subsystems and states

    }
    private void LOOP_updatestate(){
        //update render state

    }
    private void LOOP_render(){
        //render to screen

    }

}
