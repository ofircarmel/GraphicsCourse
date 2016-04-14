package RayTracing;

public class Cylinder extends Object3D{
	private Point3D position;
	private double length;
	private double radius;
	private Vector3D rotation;
	
	

	public Cylinder(Point3D position, double length, double radius, Vector3D rotation, int material_index) {
		super(material_index);
		this.position = position;
		this.length = length;
		this.radius = radius;
		this.rotation = rotation;
		
	}
	
	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Vector3D getRotation() {
		return rotation;
	}
	public void setRotation(Vector3D rotation) {
		this.rotation = rotation;
	}
}
