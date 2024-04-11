package blast_man;
public class Pictures {
private String pic;
private int x;
private int y;
private int dx;
private int dy;
private boolean movert;
private boolean movedn;
private int width;
private int height;




	public Pictures() {
		pic = "";
		x=0;
		y=0;
		dx=1;
		dy=0;
		movert=true;
		width=0;
		height=0;
		movedn=false;
		
	}
	public Pictures( String s, int x1, int y1, int h1, int w1) {
		pic=s;
		x=x1;
		y=y1;
		movert=true;
		movedn=false;
		width=w1;
		height=h1;
	}
	
	
	public Pictures(String s, int x1, int y1, int dx1, int dy1, boolean rt, boolean dn, int h1, int w1) {
		pic=s;
		x=x1;
		y=y1;
		dx=dx1;
		dy=dy1;
		movert=rt;
		movedn=dn;
		width=w1;
		height=h1;
	}
	public void spin () {
		if (movert) {
			x+=dx;
		}
		else {
			x-=dx;	
		}
		if(movedn) {
			y+=dy;
		}
		else {
			y-=dy;
		}
		if (x<0)
			movert=true;
		if(x+width>800)
			movert=false;
		
		if (y<0)
			movedn=true;
		if (y+height>600)
			movedn=false;
			
			
	}
	public void move() {
		x+=dx;
		if(x<0)
			x=0;
		if(x+width>800)
			x=800-width;
		
		y+=dy;
		if(y<0)
			y=0;
		if(y+height>600)
		y=600-height;
	}
	public int getW() {
		return width;
	}
	public int getH() {
		return height;
	}
	public String getPic() {
		return pic;
		}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDX() {
		return dx;
	}
	public int getDY() {
		return dy;
	}
	public void setDx(int c) {
		dx=c;
	}

public void setdx(int dxfromgame) {
	dx=dxfromgame;
}
public void setdy(int dyfromgame) {
	dy=dyfromgame;
}
public void sety(int yfromgame) {
	y=yfromgame;
}
public void setx(int xfromgame) {
	x=xfromgame;
}
public boolean Collision(Pictures b) {
	if(getX()+getW()>=b.getX()&&getX()<=b.getX()+b.getW()&&getY()+getH()>=b.getY()&&getY()<=b.getY()+b.getH())
		return true;
	return false;
}
public void bounce () {
	if (movert) {
		x+=dx;
	}
	else {
		x-=dx;	
	
	}
	if (x<0)
		movert=true;
	if(x+width>850)
		movert=false;
	if (y<0)
		movedn=true;
	if (y+height>600)
		movedn=false;
}}
