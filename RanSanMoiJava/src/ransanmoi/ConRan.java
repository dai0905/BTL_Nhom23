package ransanmoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

public class ConRan {

	int doDai = 3;
	int []x;
	int []y;
	
	public static int LEN = 1;
	public static int XUONG = -1;
	public static int TRAI = 2;
	public static int PHAI = -2;
	
	int vector = ConRan.XUONG;
	
	long t1 = 0;
	int speed = 200;
	int maxLen = 5;
	
	boolean udAfterChangeVt = true;
	
	public ConRan() {
		x = new int[100];
		y = new int[100];
		
		x[0] = 5;
		y[0] = 4;
		
		x[1] = 5;
		y[1] = 3;
		
		x[2] = 5;
		y[2] = 2;
	}
	
	public void resetGame() {
		x = new int[100];
		y = new int[100];
		
		x[0] = 5;
		y[0] = 4;
		
		x[1] = 5;
		y[1] = 3;
		
		x[2] = 5;
		y[2] = 2;
		
		doDai = 3;
		vector = ConRan.XUONG;
	}
	
	public void setVector(int v) {
		if(vector != -v && udAfterChangeVt) {
			vector = v;
			udAfterChangeVt = false;
		}
	}
	
	public boolean tdCoNamTrongRan(int x1, int y1) {
		for(int i=0; i<doDai; i++) {
			if(x[i] == x1 && y[i] == y1) return true;
		}
		return false;
	}
	
	public Point layToaDoMoi() {
		Random r = new Random();
		int x;
		int y;
		do {
			x = r.nextInt(19);
			y = r.nextInt(19);
		}while(tdCoNamTrongRan(x,y));

		return new Point(x,y);
	}
	//
	public Point layToaDoVC() {
		Random r = new Random();
		int x;
		int y;
		do {
			x = r.nextInt(19);
			y = r.nextInt(19);
		}while(tdCoNamTrongRan(x,y));

		return new Point(x,y);
	}
	//
	public int getCurrentSpeed() {
		int speed = 200;
		for(int i=0; i<GameScreen.CurrentLevel; i++)
			speed*=0.8;
		return speed;
	}
	
	public void update() {
		
		if(doDai == maxLen) {
			GameScreen.CurrentLevel++;
			//
			GameScreen.bg1[layToaDoMoi().x][layToaDoMoi().y] =3 ;
			//
			maxLen += 5;
			speed = getCurrentSpeed();
			vector = ConRan.XUONG;
		}
		
		//Xu ly va cham
		for(int i=1; i<doDai; i++) {
			if(x[0] == x[i] && y[0] == y[i]) {
				GameScreen.isPlaying = false;
				GameScreen.isGameOver = true;
				
				String name = JOptionPane.showInputDialog("Moi ban nhap ten: ");
				RanSanMoi.user.add(new User(name, String.valueOf(GameScreen.CurrentLevel), String.valueOf(GameScreen.diem)));
				
				GameScreen.diem = 0;
				GameScreen.CurrentLevel = 1;
				speed = 200;
				maxLen = 5;
			}
		}
		
		if(System.currentTimeMillis()-t1>speed) {
			
			udAfterChangeVt = true;
			
			if(GameScreen.bg[x[0]][y[0]] == 2) {
				doDai++;
				GameScreen.bg[x[0]][y[0]] = 0;
				GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y] =2 ;
				
				GameScreen.diem++;
			}
			//
			if(GameScreen.bg1[x[0]][y[0]] == 3) {
				GameScreen.isPlaying = false;
				GameScreen.isGameOver = true;
				
				String name = JOptionPane.showInputDialog("Moi ban nhap ten: ");
				RanSanMoi.user.add(new User(name, String.valueOf(GameScreen.CurrentLevel), String.valueOf(GameScreen.diem)));
				
				GameScreen.diem = 0;
				GameScreen.CurrentLevel = 1;
				speed = 200;
				maxLen = 5;
			}
			//
			for(int i=doDai-1; i>0; i--) {
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			
			if(vector == ConRan.LEN) y[0]--;
			if(vector == ConRan.XUONG) y[0]++;
			if(vector == ConRan.TRAI) x[0]--;
			if(vector == ConRan.PHAI) x[0]++;
			
			if(x[0]<0) x[0] = 19;
			if(x[0]>19) x[0] = 0;
			if(y[0]<0) y[0] = 19;
			if(y[0]>19) y[0] = 0;
			
			t1 = System.currentTimeMillis();
		}
		
	}
	
	public void veRan(Graphics g) {
		g.setColor(Color.red);
		for(int i=0; i<doDai; i++) 
			g.fillRect(x[i]*20, y[i]*20, 18, 18);
	}
}
