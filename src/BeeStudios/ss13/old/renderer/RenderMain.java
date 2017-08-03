package BeeStudios.ss13.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 * Created by Spartan 2 on 2017-07-21.
 */
public class RenderMain {


        public RenderMain() {
        }

        public void init() throws Exception {
        }

        public void clear() {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        }
    }

