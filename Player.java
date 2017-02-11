import java.util.*;
public class Player {
	//Static list of players
	static ArrayList<Player> players = new ArrayList<>();
	//Queue of blocks
	ArrayList<Block> blocks = new ArrayList<>();
	//Velocities
	private int horizontalVel;
	private int verticalVel;
	//Position
	private int x;
	private int y;
	//Length of tron cycle
	private int length;
	boolean lost = false;
	//Speed of tron cycle to increase updates
	private int speed;

	public Player(int x, int y, int hVel, int vVel, int l){
		this.x = x;
		this.y = y;
		horizontalVel = hVel;
		verticalVel = vVel;
		length = l;
		players.add(this);
		blocks.add(new Block(this.x, this.y));
	}

	//Update player position and corresponding blocks
	public void update(){
		x += horizontalVel;
		y += verticalVel;
		Block z = new Block(x, y);
		blocks.add(0, z);
		if(blocks.size() > length)blocks.remove(blocks.size()-1);
	}

	//Check out of bounds
	public void bound(){
		if(x > 1500 || x < 0 || y > 700 || y < 0)lost = true;
	}

	//Collision detection
	public void collide(){
		for(Player z : players){
			int count = 0;
			for(Block b : z.blocks){
				count++;
				if(count == 1)continue;
				if((x+horizontalVel >= b.getX() && x <= b.getX() && (y >= b.getY() && y <= b.getY()+Block.size) && horizontalVel > 0) 
					|| (y+verticalVel >= b.getY() && y <= b.getY() && (x >= b.getX() && x <= b.getX()+Block.size) && verticalVel > 0) 
					|| (x+horizontalVel <= b.getX() && x >= b.getX() && (y >= b.getY() && y <= b.getY()+Block.size) && horizontalVel < 0) 
					|| (y+verticalVel <= b.getY() && y >= b.getY() && (x >= b.getX() && x <= b.getX()+Block.size) && verticalVel < 0)){
					lost = true;
				}
			}
		}
	}

	//Power up collision detection
	public void getPower(){
		PowerUp pw = null;
		for(PowerUp b : PowerUp.powerups){
			if((x+horizontalVel >= b.getX() && x <= b.getX() && (y >= b.getY() && y <= b.getY()+PowerUp.size) && horizontalVel > 0) 
				|| (y+verticalVel >= b.getY() && y <= b.getY() && (x >= b.getX() && x <= b.getX()+PowerUp.size) && verticalVel > 0) 
				|| (x+horizontalVel <= b.getX() && x >= b.getX() && (y >= b.getY() && y <= b.getY()+PowerUp.size) && horizontalVel < 0) 
				|| (y+verticalVel <= b.getY() && y >= b.getY() && (x >= b.getX() && x <= b.getX()+PowerUp.size) && verticalVel < 0)){
				pw = b;
				break;
			}
		}
		if(pw != null){
			length += pw.getIncrease();
			PowerUp.powerups.remove(pw);
		}
	}

	//Getters + Setters
	public void setHorizontalVel(int vel){
		horizontalVel = vel;
		verticalVel = 0; //Move only up/down or left/right
	}
	public void setVerticalVel(int vel){
		verticalVel = vel;
		horizontalVel = 0;
	}
	public void setLength(int l){
		length = l;
	}
	public void setSpeed(int s){
		speed = s;
	}
	public int getHorizontalVel(){
		return horizontalVel;
	}
	public int getVerticalVel(){
		return verticalVel;
	}
	public int getLength(){
		return length;
	}
	public int getSpeed(){
		return speed;
	}
}