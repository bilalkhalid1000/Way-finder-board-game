import java.util.ArrayList;
import java.util.Random;

public class test {
	private Random rand = new Random();
	private node board[][] = new node[8][8];

	private final int PLAYER = 80;
	private final int DEST = 90;
	private final int ENEMY = -1;

	private Axis startAxs = new Axis(4, 4); // starting position coordinates
	private Axis endAxs = new Axis(2, 1); // destination coordinates
	private Axis playerAxs = new Axis(startAxs);

	public test() {
		initilaize();
		board[playerAxs.x][playerAxs.y].data = PLAYER;
		board[playerAxs.x][playerAxs.y].visited = true;
		board[endAxs.x][endAxs.y].data = DEST;
	}

	public void initilaize() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new node();
				board[x][y].axs = new Axis(x, y);
			}
		}
		generateEnemies();
//		generateBoardFromSeed("11356526465657435154717475828384");
//		generateBoardFromSeed("11356526465657435154717475828334");
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if(hasLeft(new Axis(x,y)) && board[x-1][y].data == 0)
					board[x][y].left = true;
				else
					board[x][y].left = false;
				if(hasRight(new Axis(x,y)) && board[x+1][y].data == 0)
					board[x][y].right = true;
				else
					board[x][y].right = false;
				if(hasUp(new Axis(x,y)) && board[x][y-1].data == 0)
					board[x][y].up = true;
				else
					board[x][y].up = false;
				if(hasDown(new Axis(x,y)) && board[x][y+1].data == 0)
					board[x][y].down = true;
				else
					board[x][y].down = false;
			}
		}
	}

	public boolean isRepeating(ArrayList<Axis> prevAxs, Axis axs) {
		for (Axis x : prevAxs) {
			if (x.equal(axs))
				return true;
		}
		return false;
	}
	
	public void generateBoardFromSeed(String seed) {
		for (int i = 0; i < seed.length(); i += 2) {
			int x = Integer.parseInt(seed.charAt(i) + "");
			int y = Integer.parseInt(seed.charAt(i + 1) + "");

			board[x - 1][y - 1].data = ENEMY;
		}
	}
	
	public void generateEnemies() {
		ArrayList<Axis> prevAxs = new ArrayList<>();
		prevAxs.add(playerAxs);
		prevAxs.add(endAxs);

		for (int i = 0; i < 16; i++) {
			int x = rand.nextInt(8);
			int y = rand.nextInt(8);

			if (isRepeating(prevAxs, new Axis(x, y))) {
				i--;
				continue;
			} else {
				prevAxs.add(new Axis(x, y));
				board[x][y].data = ENEMY;
				board[x][y].up = false;
				board[x][y].down = false;
				board[x][y].right = false;
				board[x][y].left = false;
			}
		}
	}

	public void print() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (x == 7)
					System.out.println(board[x][y]);
				else
					System.out.print(board[x][y] + ",\t");
			}
		}
	}

	private boolean moveDown() {
		if (board[playerAxs.x][playerAxs.y].down != false && !board[playerAxs.x][playerAxs.y+1].visited) {
			board[playerAxs.x][playerAxs.y].data = 0;
			board[playerAxs.x][playerAxs.y].down = false;
			board[playerAxs.x][playerAxs.y].visited = true;
			playerAxs.y++;
			board[playerAxs.x][playerAxs.y].up = false;
			board[playerAxs.x][playerAxs.y].previous = board[playerAxs.x][playerAxs.y - 1];
			board[playerAxs.x][playerAxs.y].previousAxs.copy(board[playerAxs.x][playerAxs.y-1].axs);
			board[playerAxs.x][playerAxs.y].data = PLAYER;
			return true;
		} else
			return false;
	}

	private boolean moveUp() {
		if (board[playerAxs.x][playerAxs.y].up != false && !board[playerAxs.x][playerAxs.y-1].visited) {
			board[playerAxs.x][playerAxs.y].data = 0;
			board[playerAxs.x][playerAxs.y].up = false;
			board[playerAxs.x][playerAxs.y].visited = true;
			playerAxs.y--;
			board[playerAxs.x][playerAxs.y].down = false;
			board[playerAxs.x][playerAxs.y].previous = board[playerAxs.x][playerAxs.y + 1];
			board[playerAxs.x][playerAxs.y].previousAxs.copy(board[playerAxs.x][playerAxs.y+1].axs);
			board[playerAxs.x][playerAxs.y].data = PLAYER;
			return true;
		} else
			return false;
	}

	private boolean moveRight() {
		if (board[playerAxs.x][playerAxs.y].right != false && !board[playerAxs.x+1][playerAxs.y].visited) {
			board[playerAxs.x][playerAxs.y].data = 0;
			board[playerAxs.x][playerAxs.y].right = false;
			board[playerAxs.x][playerAxs.y].visited = true;
			playerAxs.x++;
			board[playerAxs.x][playerAxs.y].left = false;
			board[playerAxs.x][playerAxs.y].previous = board[playerAxs.x - 1][playerAxs.y];
			board[playerAxs.x][playerAxs.y].previousAxs.copy(board[playerAxs.x-1][playerAxs.y].axs);
			board[playerAxs.x][playerAxs.y].data = PLAYER;
			return true;
		} else
			return false;
	}

	private boolean moveLeft() {
		if (board[playerAxs.x][playerAxs.y].left != false && !board[playerAxs.x-1][playerAxs.y].visited) {
			board[playerAxs.x][playerAxs.y].data = 0;
			board[playerAxs.x][playerAxs.y].left = false;
			board[playerAxs.x][playerAxs.y].visited = true;
			playerAxs.x--;
			board[playerAxs.x][playerAxs.y].right = false;
			board[playerAxs.x][playerAxs.y].previous = board[playerAxs.x + 1][playerAxs.y];
			board[playerAxs.x][playerAxs.y].previousAxs.copy(board[playerAxs.x+1][playerAxs.y].axs);
			board[playerAxs.x][playerAxs.y].data = PLAYER;
			return true;
		} else
			return false;
	}

	public void move() {
		if (moveDown()) {
			print();
		} else if (moveLeft()) {
			print();
		} else if (moveUp()) {
			print();
		} else if (moveRight()) {
			print();
		} else if (!playerAxs.equal(startAxs) && board[playerAxs.x][playerAxs.y].isDE() || board[playerAxs.x][playerAxs.y].hasPrevious()) {
			board[playerAxs.x][playerAxs.y].data = 0;
			playerAxs.copy(board[playerAxs.x][playerAxs.y].previousAxs);
			board[playerAxs.x][playerAxs.y].data = PLAYER;
			
			print();
		} else {
			print();
			System.out.println("Player is stuck");
			System.exit(0);
		}
	}

	public boolean hasUp(Axis current) {
		if (current.y - 1 >= 0 && board[current.x][current.y - 1].data != ENEMY)
			return true;
		return false;
	}

	public boolean hasDown(Axis current) {
		if (current.y + 1 <= 7 && board[current.x][current.y + 1].data != ENEMY)
			return true;
		return false;
	}

	public boolean hasRight(Axis current) {
		if (current.x + 1 <= 7 && board[current.x + 1][current.y].data != ENEMY)
			return true;
		return false;
	}

	public boolean hasLeft(Axis current) {
		if (current.x - 1 >= 0 && board[current.x - 1][current.y].data != ENEMY)
			return true;
		return false;
	}
	
	public void run() {
		print();
		System.out.println("____________________________________________________________");
		while (true) {
			move();
			System.out.println("-------------------------------------------------------");
			if ((playerAxs.x == endAxs.x) && (playerAxs.y == endAxs.y)) {
				System.out.println("destination reatched");
				System.exit(0);
			}
		}
	}

	public static void main(String a[]) {
		test t = new test();
		t.run();
	}
}
