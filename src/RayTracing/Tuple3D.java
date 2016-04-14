package RayTracing;

public class Tuple3D implements Vector3D,Point3D{
		
	private double x,y,z;
	
	private void set(double x, double y, double z){
		this.x=x;	this.y=y;	this.z=z;
	}
	
	public Tuple3D(double x, double y, double z) {
		set(x,y,z);
	}
	
	public Tuple3D(String xStr, String yStr, String zStr) {
		this(Double.parseDouble(xStr),Double.parseDouble(yStr),Double.parseDouble(zStr));
	}
	public Tuple3D(Vector3D other) {
		this(other.getX(),other.getY(),other.getZ());
	}
	
	public Tuple3D(Vector3D other,Vector3D origin) {
		this(other.sub(origin));
	}
	
	@Override
	public double getX() { return x; }
	@Override
	public double getY() { return y; }
	@Override
	public double getZ() { return z; }
	
	@Override
	public Vector3D add(Vector3D other) {
		return new Tuple3D(x+other.getX(),y+other.getY(),z+other.getZ());
	}
	@Override
	public Vector3D sub(Vector3D other) {
		return add(other.scale(-1));
	}
	
	@Override
	public Vector3D scale(double scalar) {
		return new Tuple3D(x*scalar,y*scalar,z*scalar);
	}
	
	@Override
	public double dotFactor(Vector3D other){
		return x*other.getX()+y*other.getY()+z*other.getZ();
	}
	
	@Override
	public double norm() {
		return Math.sqrt(dotFactor(this));	
	}
	
	@Override
	public void normalize() {
		double norm=norm();
		if (norm == 0.0) throw new RuntimeException("Zero-vector has no direction");
		set(x/norm,y/norm,z/norm);
	}
	
	@Override
	public Vector3D normalized() {
		Tuple3D result = new Tuple3D(this);
		result.normalize();
		return result;
	}
	
	@Override
	public double distance(Vector3D other) {
		return sub(other).norm();
	}
	

}
