import java.util.*;
public class PowerUp{
	private int x;
	private int y;
	static int size;
	private int increase;
	static ArrayList<PowerUp> powerups = new ArrayList<>();
	public PowerUp(int x, int y, int i){
		this.x = x;
		this.y = y;
		increase = i;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getIncrease(){
		return increase;
	}
}