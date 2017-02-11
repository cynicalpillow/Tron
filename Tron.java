import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Tron {
	/* Simple Tron cycle game
	 * By Rui
	 */
	static int speed = 4;
	static JFrame f;
	static DrawPanel p;
	static Player one;
	static Player two;
	static boolean run = true;
	public static void init(){
		f = new JFrame();
		Block.size = speed;
		PowerUp.size = speed*3;
		p = new DrawPanel();
		p.addKeyListener(p);
		one = new Player(0, 0, speed, 0, 150);
		two = new Player(1480, 658, -speed, 0, 150);
		one.setSpeed(1);
		two.setSpeed(1);
		//generatePowerUps();
		f.setSize(1500, 700);
		p.setFocusable(true);
		f.add(p);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void update(){
		if(PowerUp.powerups.size() == 0){
			//generatePowerUps();
		}
		for(Player x : Player.players){
			for(int i = 0; i < x.getSpeed(); i++){
				x.update();
				//x.getPower();
				x.bound();
				x.collide();
				if(x.lost){
					run = false;
					break;
				}
			}
			if(x.lost)break;
		}
		p.repaint();
		try{
			Thread.sleep(10);
		} catch (Exception e){}
	}
	static class DrawPanel extends JPanel implements KeyListener{
		public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.green);
            for(int i = 0; i < one.blocks.size(); i++){
            	g.fillRect(one.blocks.get(i).getX(), one.blocks.get(i).getY(), Block.size, Block.size);
            }
            g.setColor(Color.blue);
            for(int i = 0; i < two.blocks.size(); i++){
            	g.fillRect(two.blocks.get(i).getX(), two.blocks.get(i).getY(), Block.size, Block.size);
            }
            g.setColor(Color.black);
            for(int i = 0; i < PowerUp.powerups.size(); i++){
            	g.fillRect(PowerUp.powerups.get(i).getX(), PowerUp.powerups.get(i).getY(), PowerUp.size, PowerUp.size);
            }
        }
      	public void keyPressed(KeyEvent e){
	      	if(e.getKeyCode() == KeyEvent.VK_W){
	      		if(one.getVerticalVel() == 0)one.setVerticalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_S){
				if(one.getVerticalVel() == 0)one.setVerticalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_A){
	      		if(one.getHorizontalVel() == 0)one.setHorizontalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_D){
	      		if(one.getHorizontalVel() == 0)one.setHorizontalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
	      		if(one.getHorizontalVel() > 0 || one.getHorizontalVel() < 0)one.setSpeed(2);
	      		else if(one.getVerticalVel() > 0 || one.getVerticalVel() < 0)one.setSpeed(2);
	      	} else if(e.getKeyCode() == KeyEvent.VK_UP){
	      		if(two.getVerticalVel() == 0)two.setVerticalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
	      		if(two.getVerticalVel() == 0)two.setVerticalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_LEFT){
	      		if(two.getHorizontalVel() == 0)two.setHorizontalVel(-speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
	      		if(two.getHorizontalVel() == 0)two.setHorizontalVel(speed);
	      	} else if(e.getKeyCode() == KeyEvent.VK_SLASH){
	      		if(two.getHorizontalVel() > 0 || two.getHorizontalVel() < 0)two.setSpeed(2);
	      		else if(two.getVerticalVel() > 0 || two.getVerticalVel() < 0)two.setSpeed(2);
	      	}
      	}
	    public void keyReleased(KeyEvent e){
	    	if(e.getKeyCode() == KeyEvent.VK_SHIFT){
	      		if(one.getHorizontalVel() > 0 || one.getHorizontalVel() < 0)one.setSpeed(1);
	      		else if(one.getVerticalVel() > 0 || one.getVerticalVel() < 0)one.setSpeed(1);
	      	} else if(e.getKeyCode() == KeyEvent.VK_SLASH){
	      		if(two.getHorizontalVel() > 0 || two.getHorizontalVel() < 0)two.setSpeed(1);
	      		else if(two.getVerticalVel() > 0 || two.getVerticalVel() < 0)two.setSpeed(1);
	      	}
	    }
	    public void keyTyped(KeyEvent e){}
	}
	public static void generatePowerUps(){
		int num = (int)(Math.random()*2+1);
		for(int i = 0; i < num; i++){
			PowerUp p = new PowerUp((int)(Math.random()*1400 + 1), (int)(Math.random()*600 + 1), (int)(Math.random()*50 + 50));
			PowerUp.powerups.add(p);
		}
	}
	public static void main(String args[]){
		init();
		while(run){
			update();
		}
	}
}