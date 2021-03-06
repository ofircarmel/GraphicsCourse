package RayTracing;

import java.awt.Transparency;
import java.awt.color.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/**
 *  Main class for ray tracing exercise.
 */
public class RayTracer {

	public int imageWidth;
	public int imageHeight;
	public Scene thisScene;

	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size as input.
	 */
	public static void main(String[] args) {

		try {

			RayTracer tracer = new RayTracer();

                        // Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;

			if (args.length < 2)
				throw new RayTracerException("Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3)
			{
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}


			// Parse scene file:
			tracer.parseScene(sceneFileName);

			// Render scene:
			tracer.renderScene(outputFileName);

//		} catch (IOException e) {
//			System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	/**
	 * Parses the scene file and creates the scene. Change this function so it generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException
	{
		FileReader fr = new FileReader(sceneFileName);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		System.out.println("Started parsing scene file " + sceneFileName);
		String[] newArray;
		
		
		Camera cam =null;
		Settings set = null;
		
		List <Material> mtl = new ArrayList<Material>();
		List<Light> lgt = new ArrayList<Light>();
		
		List <Object3D> objects = new ArrayList<Object3D>();		

		while ((line = r.readLine()) != null)
		{
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#'))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam"))
				{
                    // Add code here to parse camera parameters

					
					Point3D position = new Tuple3D(params[0],params[1],params[2]);
					
					Point3D look_at_point = new Tuple3D(params[3],params[4],params[5]);
					
					Vector3D up_vector = new Tuple3D(params[6],params[7],params[8]);
					
					int screen_dist = Integer.parseInt(params[9]);
					int screen_width = Integer.parseInt(params[10]);	

					cam = new Camera(position,look_at_point,up_vector,screen_dist,screen_width);
					
					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				}
				else if (code.equals("set"))
				{
                                        // Add code here to parse general settings parameters
					newArray = Arrays.copyOfRange(params, 0, 3);
					Color background_col = new Color(params[0],params[1],params[2]);
					int root_num_shadow_rays = Integer.parseInt(params[3]);
					int max_num_recurs = Integer.parseInt(params[3]);
					set = new Settings(background_col,root_num_shadow_rays,max_num_recurs);

					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				}
				else if (code.equals("mtl"))
				{
                                        // Add code here to parse material parameters
					
					Color diffuse_col = new Color(params[0],params[1],params[2]);
					
					Color specular_col = new Color(params[3],params[4],params[5]);

					Color reflection_col = new Color(params[6],params[7],params[8]);
					
					float phong_specular_coeff = Float.parseFloat(params[9]);
					float transparency = Float.parseFloat(params[10]);
					
					Material tmp = new Material(diffuse_col, specular_col, phong_specular_coeff, reflection_col, transparency);
					mtl.add(tmp);
				

					System.out.println(String.format("Parsed material (line %d)", lineNum));
				}
				else if (code.equals("sph"))
				{
                                        // Add code here to parse sphere parameters

                                        // Example (you can implement this in many different ways!):
					                    // Sphere sphere = new Sphere();
                                        // sphere.setCenter(params[0], params[1], params[2]);
                                        // sphere.setRadius(params[3]);
                                        // sphere.setMaterial(params[4]);
					
					
					Point3D position = new Tuple3D(params[0],params[1],params[2]);
					double raduis = Double.parseDouble(params[3]);
					
					int material_index = Integer.parseInt(params[4]);
					
					objects.add(new Sphere(position, raduis, material_index));

					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				}
				else if (code.equals("pln"))
				{
                                        // Add code here to parse plane parameters
					Vector3D normal = new Tuple3D(params[0],params[1],params[2]);
					int offset = Integer.parseInt(params[3]);
					int material_index = Integer.parseInt(params[4]);
					
					objects.add(new Plane(normal, offset, material_index));
					
					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				}
				else if (code.equals("cyl"))
				{
                                        // Add code here to parse cylinder parameters
					
					Point3D position = new Tuple3D(params[0],params[1],params[2]);
					double length = Double.parseDouble(params[3]);
					double radius = Double.parseDouble(params[4]);
					
					Vector3D rotation = new Tuple3D(params[5],params[6],params[7]);
					int material_index = Integer.parseInt(params[8]);
					
					objects.add(new Cylinder(position, length, radius, rotation, material_index));

					System.out.println(String.format("Parsed cylinder (line %d)", lineNum));
				}
				else if (code.equals("lgt"))
				{
                                        // Add code here to parse light parameters
					
					Point3D position = new Tuple3D(params[0],params[1],params[2]);
					Color color = new Color(params[3],params[4],params[5]);
					
					float specular_intensity = Float.parseFloat(params[6]);
					float shadow_intensity = Float.parseFloat(params[7]);
					float radius = Float.parseFloat(params[8]);
					
					lgt.add(new Light(position, color, specular_intensity, shadow_intensity, radius));

					


					

					System.out.println(String.format("Parsed light (line %d)", lineNum));
				}
				else
				{
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

                // It is recommended that you check here that the scene is valid,
                // for example camera settings and all necessary materials were defined.

		System.out.println("Finished parsing scene file " + sceneFileName);
		
		if(cam==null){
			throw new RuntimeException("Error: no camera specification found");
		}
		if(set==null){	
			throw new RuntimeException("Error: no settings found");
		}
		thisScene = new Scene(cam, set, mtl, objects, lgt);

	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName)
	{
		long startTime = System.currentTimeMillis();

		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];


                // Put your ray tracing code here!
                //
                // Write pixel color values in RGB format to rgbData:
                // Pixel [x, y] red component is in rgbData[(y * this.imageWidth + x) * 3]
                //            green component is in rgbData[(y * this.imageWidth + x) * 3 + 1]
                //             blue component is in rgbData[(y * this.imageWidth + x) * 3 + 2]
                //
                // Each of the red, green and blue components should be a byte, i.e. 0-255


		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

                // The time is measured for your own conveniece, rendering speed will not affect your score
                // unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

                // This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}




	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName)
	{
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
	    int height = buffer.length / width / 3;
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
	    ColorModel cm = new ComponentColorModel(cs, false, false,
	            Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	    SampleModel sm = cm.createCompatibleSampleModel(width, height);
	    DataBufferByte db = new DataBufferByte(buffer, width * height);
	    WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	    BufferedImage result = new BufferedImage(cm, raster, false, null);

	    return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {  super(msg); }
	}


}
