package blast_man;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.text.DecimalFormat;
import java.util.*;

public class Game extends JPanel implements Runnable,KeyListener{
private BufferedImage back;
private int key;
private Sound s;
private Sound p;
private Pictures player; 

private int randx;
private boolean GameOver;
private int score;
private double time;
private double curtime;
private ArrayList<Pictures> lasers;







public Game() {
back=null;
randx=(int) (Math.random()*400-0+1)+0;
player=new Pictures("blastman_background.png",200,400,200,300);
s= new Sound();
p= new Sound();
new Thread(this).start();
this.addKeyListener(this);time=System.currentTimeMillis();
curtime=0;
score = 0;
key=-1;
p.playmusic("music.wav");
lasers = new ArrayList<Pictures>();









}

public void run() {

try {

while(true) {

Thread.currentThread().sleep(5);

repaint();

}

}

catch(Exception e) {}

}

public void paint (Graphics g)

{

Graphics2D twoDgraph = (Graphics2D)g;

if (back==null) {

back =(BufferedImage) (createImage(getWidth(), getHeight()));

}

Graphics g2d = back.createGraphics();

g2d.clearRect(0, 0, getSize().width, getSize().height);

//START CODING GRAPHICS HERE
drawPlayer(g2d);
g2d.drawImage(new ImageIcon(k.getPic()).getImage(),k.getX(),k.getY(),k.getW(),k.getH(), this);
g2d.drawString("Score: "+ score,54,60);
g2d.setFont(newFont("chiller", Font.BOLD,44));
// GRAPHICS ABOVE

g2d.drawString(new DecimalFormat("#0.00").format(curtime),20,30);


if (GameOver) {
	g2d.setFont(new Font("chiller", Font.BOLD,54));
	g2d.drawString("GAMEOVER", 300, 300);
}

//if (Paddle.getx+Paddle.getheight) {
	

	
else {curtime=(System.currentTimeMillis()-time)/1000;
 move(); }

//This line tells the program to draw everything above. If you delete this, nothing will show up.

twoDgraph.drawImage(back, 0, 0, null);
}

public void drawPlayer(Graphics g2d) {
	g2d.drawImage(new ImageIcon(player.getPic()).getImage(),0,0,getWidth(),getHeight(),this);
}

public void drawLasers(Graphics g2d) {
	for (Pictures laser : lasers) {
		g2d.drawImage(new ImageIcon(laser.getPic()).getImage(),laser.getX(),laser.getY(),laser.getW(),laser.getH(), this);
	}
}

public void collision() {
	for (Pictures laser : lasers) {
	
		if(player.Collision(laser)) {
			GameOver= true;
}
else moveLaser();
}
}	


public void moveLaser() {
	for (Pictures laser : lasers) {
		laser.fall();
	}
}
private Font newFont(String string, int bold, int i) {
	// TODO Auto-generated method stub
	return null;
}

public void movePlayer() {
	player.move();
}

public void keyTyped(KeyEvent e) {
}
public void keyPressed(KeyEvent e) {
	System.out.println(key);
	if(key==37)
		k.setDx(-2);
	if(key==39)
		k.setDx(2);
}
public void keyReleased(KeyEvent e) {
	key=e.getKeyCode();
	if(key==37||key==39)
		k.setDx(0);
	
}

public int randomInt() {
	return ((int)(Math.random() * 1600));
}
}



