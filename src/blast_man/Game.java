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
private Pictures wo;
private Pictures f;
private Pictures k;
private int randx;
private boolean GameOver;
private int score;
private double time;
private double curtime;

// hi





public Game() {
back=null;
randx=(int) (Math.random()*400-0+1)+0;
wo=new Pictures("blastman_background.png",200,400,200,300);
s= new Sound();
p= new Sound();
new Thread(this).start();
this.addKeyListener(this);
k=new Pictures("scientist.png", 400, 400, 70, 90);
f=new Pictures("zap.png", randx,0,70,90,0,0);

time=System.currentTimeMillis();
curtime=0;
score = 0;
key=-1;
p.playmusic("music.wav");









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

//take a snap shop of the current screen and same it as an image

//that is the exact same width and height as the current screen

if (back==null) {

back =(BufferedImage) (createImage(getWidth(), getHeight()));

}

//create a graphics reference to the back ground image

//we will draw all changes on the background image

Graphics g2d = back.createGraphics();

//this clears the old image, like an EtchASketch. If you see the old image when we learn motion, you deleted this line.

g2d.clearRect(0, 0, getSize().width, getSize().height);

//START CODING GRAPHICS HERE
g2d.drawImage(new ImageIcon(wo.getPic()).getImage(),0,0,getWidth(),getHeight(),this);
g2d.drawImage(new ImageIcon(k.getPic()).getImage(),k.getX(),k.getY(),k.getW(),k.getH(), this);
g2d.drawString("Score: "+ score,54,60);
g2d.setFont(newFont("chiller", Font.BOLD,44));

g2d.drawString(new DecimalFormat("#0.00").format(curtime),20,30);

if(k.Collision(f))
{
randx=(int) (Math.random()*800-0+1)+0;
f.setX(randx);
f.setY(0);
s.playmusic("splat.wav");
}
else moveFruit(g2d);
if (f.getY()+300==800) {
	GameOver= true;
}
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


private Font newFont(String string, int bold, int i) {
	// TODO Auto-generated method stub
	return null;
}

public void move() {
	k.move();
}

public void moveFruit(Graphics g2d) {
	g2d.drawImage(new ImageIcon(f.getPic()).getImage(),f.getX(),f.getY(),f.getW(),f.getH(), this);
	f.fall();
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
}



