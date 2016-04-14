package RayTracing;


public class Sphere extends Object3D{

	private Point_old position;
	private double raduis;
	
	

	
	public Sphere(Point_old position, double raduis, int material_index) {
		super(material_index);
		this.position = position;
		this.raduis = raduis;

	}
	public Point_old getPosition() {
		return position;
	}
	public void setPosition(Point_old position) {
		this.position = position;
	}
	public double getRaduis() {
		return raduis;
	}
	public void setRaduis(double raduis) {
		this.raduis = raduis;
	}
	public int getMaterial_index() {
		return material_index;
	}
	public void setMaterial_index(int material_index) {
		this.material_index = material_index;
	}
	

}
