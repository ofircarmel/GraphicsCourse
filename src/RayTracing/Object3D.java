package RayTracing;

public abstract class Object3D {
	
	private Material material;
	
	public Object3D(int materialIdx){
		material=Material.getByIndex(materialIdx);
		if(material==null) throw new RuntimeException("unrecognized material index");
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	
	
}
