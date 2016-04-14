package RayTracing;

public interface Vector3D {
	double getX();
	double getY();
	double getZ();
	
	Vector3D add(Vector3D other);
	Vector3D sub(Vector3D other);
	Vector3D scale(double scalar);
	
	double dotFactor(Vector3D other);
	double norm();
	
	void normalize();
	Vector3D normalized();
	double distance(Vector3D other);
}
