
public class node {
	public int data = 0;

	// Possible direction to move
	public boolean up = true;
	public boolean down = true;
	public boolean left = true;
	public boolean right = true;

	public boolean visited = false;

	public Axis axs;
	public Axis previousAxs = new Axis();

	public node previous = null;

	public node() {

	}

	public node(int data) {
		this.data = data;
	}

	public boolean isDE() {
		if (up)
			return false;
		else if (down)
			return false;
		else if (left)
			return false;
		else if (right)
			return false;
		else
			return true;
	}

	public boolean hasPrevious() {
		if (previous == null)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
