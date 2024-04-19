import javax.swing.ImageIcon;
public class blastman extends Ship {
public blastman()

{

super();

}

public blastman(int xV, int yV, int w, int h)

{

super(xV, yV, new ImageIcon("scientist"), w, h);

}

public void setX(int x1) {
super.setX(x1-super.getX());

}

public void setY(int y1) {
super.setY(y1-super.getY());

}




return false;

}

}

