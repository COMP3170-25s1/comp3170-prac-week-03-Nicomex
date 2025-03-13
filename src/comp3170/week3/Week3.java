package comp3170.week3;
 
 import static org.lwjgl.opengl.GL11.glViewport;
 import static org.lwjgl.opengl.GL41.*;
 
 import java.io.File;
 import java.io.IOException;

import org.joml.Vector2f;

import comp3170.OpenGLException;
 import comp3170.IWindowListener;
 import comp3170.Shader;
 import comp3170.ShaderLibrary;
 import comp3170.Window;
 
 public class Week3 implements IWindowListener {
 
 	private Window window;
 	private Shader shader;
 	
 	
 	final private File DIRECTORY = new File("src/comp3170/week3"); 
 	
 	private int width = 800;
 	private int height = 800;
 	
 	long oldTime;
 	private Scene scene;
 	
 	private static final float TRANSLATION_SPEED = 1;
	private static final float TAU = 6.283185f; 
 	private static final float ROTATION_SPEED = TAU / 12;
 	private static final float SCALE_SPEED = 2f;
 	
 	public Week3() throws OpenGLException  {
 		
 		// create window with title, size, and a listener (this)
 		window = new Window("Week 3 prac", width, height, this);
 		
 		// sets the window as resizable
 		window.setResizable(true);
 		// start running the window
 		window.run();
 	}
 	
 	@Override // run once
 	public void init() {
 		
 		new ShaderLibrary(DIRECTORY);
 		// set the background colour to white
 		glClearColor(0.25f, 0.25f, 0.25f, 1.0f);	
 		
 		// create the scene
 		scene = new Scene();
 		
 		oldTime = System.currentTimeMillis();
 		//scale the model once
 		scene.modelMatrix.translate(0.5f, 0.0f, 0.0f).scale(0.25f, 0.25f, 0);
 		
 	}
 
 
 	@Override
 	public void draw() {
 		
        // clear the colour buffer
 		glClear(GL_COLOR_BUFFER_BIT);	
 		
 		scene.draw();
 		
 		update();
 	    
 	}

 	private void update() {
 		// calculate seconds since last frame 
 	   long time = System.currentTimeMillis(); 
 	   
 	   float deltaTime = (time - oldTime) / 1000f; 
 	   oldTime = time; 
 	   //scene.update(deltaTime);
 	   System.out.println("update: dt =" + deltaTime + "s:");
 	   // scale updates by deltaTime 
 	  float rotation = ROTATION_SPEED * deltaTime;
 	  scene.modelMatrix.translate(0, TRANSLATION_SPEED * deltaTime, 0).rotateZ(rotation);
 	   
 	   
	}

	@Override
 	public void resize(int width, int height) {
 		this.width = width;
 		this.height = height;
 		glViewport(0,0,width,height);
 		
 	}
 	
 	@Override
 	public void close() {
 		// TODO Auto-generated method stub
 		
 	}
 
 	public static void main(String[] args) throws IOException, OpenGLException{
 		new Week3();
 	}
 
 	
 	
 }