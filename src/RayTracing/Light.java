package RayTracing;


public class Light {
	private Point_old position;
	private Point_old color;
	private float specular_intensity;
	private float shadow_intensity;
	private float radius;
	public Light(Point_old position, Point_old color, float specular_intensity, float shadow_intensity, float radius) {
		super();
		this.position = position;
		this.color = color;
		this.specular_intensity = specular_intensity;
		this.shadow_intensity = shadow_intensity;
		this.radius = radius;
	}
	public Point_old getPosition() {
		return position;
	}
	public void setPosition(Point_old position) {
		this.position = position;
	}
	public Point_old getColor() {
		return color;
	}
	public void setColor(Point_old color) {
		this.color = color;
	}
	public float getSpecular_intensity() {
		return specular_intensity;
	}
	public void setSpecular_intensity(float specular_intensity) {
		this.specular_intensity = specular_intensity;
	}
	public float getShadow_intensity() {
		return shadow_intensity;
	}
	public void setShadow_intensity(float shadow_intensity) {
		this.shadow_intensity = shadow_intensity;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}


}
