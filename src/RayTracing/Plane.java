package RayTracing;

public class Plane extends Object3D{
	private Point_old normal;
	private int offset;
	
	public Plane(Point_old normal, int offset, int material_index) {
		super(material_index);
		this.normal = normal;
		this.offset = offset;	
	}
	public Point_old getNormal() {
		return normal;
	}
	public void setNormal(Point_old normal) {
		this.normal = normal;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
