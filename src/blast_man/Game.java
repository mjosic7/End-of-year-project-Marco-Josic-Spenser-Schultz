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
private Pictures background;
int key;
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
randx=(int) (Math.random()*400-0+1)+0;
background=new Pictures("blastman_background.png");
player=new Pictures("scientist.png",400,400,70,90);
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
lasermove();
g2d.drawImage(new ImageIcon(background.getPic()).getImage(),0,0,getWidth(),getHeight(),this);
addLasers();
drawLasers(g2d);
movePlayer();
g2d.drawImage(new ImageIcon(player.getPic()).getImage(),player.getX(),player.getY(),player.getW(),player.getH(), this);
g2d.drawString("Score: "+ score,54,60);
g2d.setFont(newFont("chiller", Font.BOLD,44));
// GRAPHICS ABOVE

g2d.drawString(new DecimalFormat("#0.00").format(curtime),20,30);


if (GameOver) {
	g2d.setFont(new Font("chiller", Font.BOLD,54));
	g2d.drawString("GAMEOVER", 300, 300);
}


	
	


//This line tells the program to draw everything above. If you delete this, nothing will show up.

twoDgraph.drawImage(back, 0, 0, null);
}
public void lasermove() {
	for (Pictures laser : lasers)
	laser.setY(laser.getY()+1) ;
	
	//lasers.setdy(1);
}
public void addLasers() {
	double random = Math.random();
	if (random < .995) {
		return;
	}
	lasers.add(new Pictures("zap.png", randomInt(), 10, 20, 30));
	System.out.println("laser added");
}

public void drawLasers(Graphics g2d) {
	for (Pictures laser : lasers) {
		g2d.drawImage(new ImageIcon(laser.getPic()).getImage(),laser.getX(),laser.getY(),laser.getW(),laser.getH(), this);
		//System.out.println("laser drawn");
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
		player.setDx(-1);
	if(key==39)
		player.setDx(1);
}
public void keyReleased(KeyEvent e) {
	key=e.getKeyCode();
	if(key==37||key==39)
		player.setDx(0);
	
}

public int randomInt() {
	return ((int)(Math.random() * 1600));
}
}



