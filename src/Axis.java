
public class Axis {
	public int x;
	public int y;
	
	public Axis() {
		
	}
	
	public Axis(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Axis(Axis axs) {
		this.x = axs.x;
		this.y = axs.y;
	}
	
	public boolean equal(Axis axs) {
		if(x == axs.x && y == axs.y)
			return true;
		return false;
	}
	
	public void copy(Axis axs) {
		this.x = axs.x;
		this.y = axs.y;
	}
	
	@Override
	public String toString() {
		return x + ", " + y;
	}
}
