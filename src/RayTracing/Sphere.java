package RayTracing;


public class Sphere extends Object3D{

	private Point3D position;
	private double raduis;
	
	public Sphere(Point3D position, double raduis, int material_index) {
		super(material_index);
		this.position = position;
		this.raduis = raduis;

	}

	public Point3D getPosition() {
		return position;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}

	public double getRaduis() {
		return raduis;
	}

	public void setRaduis(double raduis) {
		this.raduis = raduis;
	}	

}
