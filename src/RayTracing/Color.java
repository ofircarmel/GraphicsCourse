package RayTracing;


public class Color {
	private int R_VAL,G_VAL,B_VAL;
	
	
	public Color(int r,int g,int b){
		R_VAL=r%256;
		G_VAL=g%256;
		B_VAL=b%256;
	}

	public Color(Color c){
		this(c.R_VAL,c.G_VAL,c.G_VAL);
	}
	
	public int getR() {	return R_VAL;}
	public int getG() { return G_VAL;}
	public int getB() { return B_VAL; }

	public void setR(int r_VAL) { R_VAL = r_VAL;}
	public void setG(int g_VAL) { G_VAL = g_VAL; }
	public void setB(int b_VAL) { B_VAL = b_VAL; }

}
