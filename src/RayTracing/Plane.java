package RayTracing;

public class Plane extends Object3D{
	private Vector3D normal;
	private int offset;
	
	public Plane(Vector3D normal, int offset, int material_index) {
		super(material_index);
		this.normal = normal;
		this.offset = offset;	
	}
	
	
	public Vector3D getNormal() {
		return normal;
	}


	public void setNormal(Vector3D normal) {
		this.normal = normal;
	}


	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
