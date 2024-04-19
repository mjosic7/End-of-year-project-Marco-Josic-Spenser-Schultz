package blast_man;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	private BufferedImage back;
	private int key, count, lives;
	private ImageIcon background, Ship;
	private Sound music;
	private boolean start, win, moveRight;
	private char screen;
//	private PlayerShip player;
	private Sound p;
	private Sound p2;
	private double currtime1;
	private double time;
	private boolean soundPlayed;
	private boolean playSound;

	public Game() {

		new Thread(this).start();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		win = false;
		start = true;
		moveRight = true;
		key = -1;
		count = 0;
		screen = 'S';
		background = new ImageIcon("blastman_background.png");
	//	Ship = new ImageIcon("ship.png");
		p = new Sound();
		p2 = new Sound();
		music = new Sound();
		currtime1 = 0;
		time = System.currentTimeMillis();
		soundPlayed=false;
		playSound=false;
		
	}

	public void screen(Graphics g2d) {
		switch (screen) {
		case 'S':
			g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			drawStartScreen(g2d);
			break;
		case 'G':
			g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			drawalien(g2d);
			drawplayership(g2d);
			moveAliens();
			drawSM(g2d);
			drawalienmissile(g2d);
			alienhit();
			count++;
			if (count % 100 == 0) {
				getalienmissile(g2d);

			}

			checkplayerhit();
			checklose();
			checkwin();
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Arial", Font.BOLD, 20));
			double currentTime = System.currentTimeMillis();
			currtime1 += (currentTime - time) / 1000;
			time = currentTime;
			g2d.drawString(new DecimalFormat("#0.00").format(currtime1), 20, 50);
			g2d.drawString("Lives:" + lives, 20, 20);
			if (lives == 0)
				g2d.fillRect(0, 0, 3000, 3000);
			g2d.setColor(Color.BLUE);

			break;
		case 'W':
			drawWinScreen(g2d);
			break;
		case 'L':
			drawlosescreen(g2d);
			break;

		}

		if (checkaliensbottom()) {
			screen = 'L';
			playSound=true;
		}

	}
	


	public ArrayList<AlienShip> setAliens() {
		ArrayList<AlienShip> temp = new ArrayList<>();
		int x = 75;
		int y = 100;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				temp.add(new AlienShip(i + x, y, 50, 50));
				x += 100;

			}

			y += 50;
			x = 75;

		}

		return temp;

	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(5);
				repaint();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics buffer = back.getGraphics();
		screen(buffer);
		g2d.drawImage(back, 0, 0, this);
		if (soundPlayed==false&&playSound==true) {
			music.playmusic("loser.wav");
			soundPlayed=true;
		}

	}

	public void checklose() {
		//checkPlayerHit();
		if (checkplayerhit()) {

			if (lives <= 0) {
				screen = 'L';
				playSound=true;

			}
		}
		}
	//}

	

	public void checkwin() {
		if (aliens.isEmpty()) {
			screen = 'W';
			music.playmusic("winner.wav");

			

		}

	}

	public void drawStartScreen(Graphics g2d) {
		g2d.setFont(new Font("Times new roman", Font.BOLD, 50));
		g2d.setColor(Color.white);
		g2d.drawString("Space Invaders", 515, 400);
		g2d.drawString("Press B to begin", 505, 600);

	}

	public void drawWinScreen(Graphics g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 3000, 3000);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Times new roman", Font.BOLD, 50));
		g2d.setColor(Color.white);
		g2d.drawString("YOU WIN!", 550, 400);

	}

	public void drawlosescreen(Graphics g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Broadway", Font.BOLD, 50));
		g2d.drawString("YOU LOST!", 250, 300);

	}

	public boolean checkaliensbottom() {
		for (AlienShip a : aliens) {
			if (a.getY() + a.getH() >= getHeight()) {
				return true;

			}

		}

		return false;

	}

	public void drawalien(Graphics g2d) {
		for (AlienShip a : aliens) {
			g2d.drawImage(a.getPic().getImage(), a.getX(), a.getY(), a.getW(), a.getH(), this);

		}

	}

	public void drawplayership(Graphics g2d) {
		g2d.drawImage(player.getPic().getImage(), player.getX(), player.getY(), player.getW(), player.getH(), this);

	}

	public void moveAliens() {
		if (checkalien()) {
			changeDyAlien(20);

		}

		for (AlienShip a : aliens) {
			if (moveRight) {
				a.setdx(2);

			} else {

				a.setdx(-2);

			}

		}

	}

	public void changeDyAlien(int dy) {
		for (AlienShip a : aliens) {
			a.setdy(dy);

		}

	}

	public boolean checkalien() {
		for (AlienShip a : aliens) {
			if (a.getX() + a.getW() >= getWidth() || a.getX() < 0) {
				moveRight = !moveRight;
				return true;

			}

		}

		return false;

	}

	public void playerMissile(int x) {
		mMissiles.add(new ShipMissile(x, player.getY(), 30, 30));

	}

	public void drawSM(Graphics g2d) {
		for (ShipMissile sm : mMissiles) {
			g2d.drawImage(sm.getPic().getImage(), sm.getX(), sm.getY(), sm.getH(), sm.getW(), this);
			sm.setdy(-5);

		}

	}

	public void getalienmissile(Graphics g2d) {
		int randAlien = (int) (Math.random() * aliens.size());
		fmissiles.add(new AlienMissile(aliens.get(randAlien).getX() + (aliens.get(randAlien).getW()) / 2,
				aliens.get(randAlien).getY() + aliens.get(randAlien).getH(), 30, 30));

	}

	public void drawalienmissile(Graphics g2d) {
		for (AlienMissile am : fmissiles) {
			g2d.drawImage(am.getPic().getImage(), am.getX(), am.getY(), am.getW(), am.getH(), this);
			am.setdy(5);

		}

	}

	public boolean checkplayerhit() {
		boolean hitDetected = false;
		for (int i = 0; i < fmissiles.size(); i++) {
			AlienMissile am = fmissiles.get(i);
			if (player.hit(am)) {
				fmissiles.remove(i);
				lives--;
				hitDetected = true;
				break;

			}

		}

		return hitDetected;

	}

	public boolean alienhit() {
		if (!aliens.isEmpty()) {
			for (int i = 0; i < aliens.size(); i++) {
				for (int j = 0; j < mMissiles.size(); j++) {
					if (aliens.get(i).hit(mMissiles.get(j))) {
						aliens.remove(i);
						i--;
						mMissiles.remove(j);
						j--;
						music.playmusic("shooting.wav");

						return true;

					}

				}

			}

		}

		return false;

	}

	public void move() {
		//for (int i = fmissiles.size() - 1; i >= 0; i--) {
		//	fmissiles.get(i).move();
		//	if (fmissiles.get(i).getY() > getHeight()) {
		//		fmissiles.remove(i);

			}

		}

		for (int i = mMissiles.size() - 1; i >= 0; i--) {
			mMissiles.get(i).move();
			if (mMissiles.get(i).getY() > getHeight()) {
				mMissiles.remove(i);

			}

		}

	}

	@Override

	public void keyTyped(KeyEvent e) {

	}

	@Override

	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if (key == 66) {
			screen = 'G';

		}

		if (key == 76) {
//key=l

			screen = 'W';

		}

	}

	@Override

	public void keyReleased(KeyEvent e) {

	}

	@Override

	public void mouseDragged(MouseEvent e) {

	}

	@Override

	public void mouseMoved(MouseEvent m) {
	//	player.setX(m.getX());

	}

	@Override

	public void mouseClicked(MouseEvent e) {

	}

	@Override

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
		//	playerMissile(e.getX() + (player.getW() / 4));

		}

	}

	@Override

	public void mouseReleased(MouseEvent e) {

	}

	@Override

	public void mouseEntered(MouseEvent e) {

	}

	@Override

	public void mouseExited(MouseEvent e) {

	}

}
