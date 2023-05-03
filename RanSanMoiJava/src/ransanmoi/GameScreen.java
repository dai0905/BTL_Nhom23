package ransanmoi;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable {
	
	static int [][] bg = new int [20][20];
	static int [][] bg1 = new int [20][20];
	
	static int WIDTH = 400;
	static int HEIGHT = 400;
	
	static boolean isPlaying = false;
	
	ConRan ran;
	
	Thread thread;
	
	static int CurrentLevel = 1;
	static int diem = 0;
	
	static boolean isGameOver = false;
	
	public GameScreen() {
		
		ran = new ConRan();
		
		bg[7][8] = 2;
		bg1[10][10] = 3;
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		long t = 0;
		while(true) {
			
			if(isPlaying) {
				
				ran.update();
			}
			
			repaint();
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {}
		}
	}
	
	public void paintBg(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH+200, HEIGHT);
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++) {
				g.fillRect(i*20, j*20, 18, 18);
				if(bg[i][j] == 2) {
					g.setColor(Color.red);
					g.fillRect(i*20, j*20, 18, 18);
					g.setColor(Color.black);
				}
				//
				if(bg1[i][j] == 3) {
					g.setColor(Color.green);
					g.fillRect(i*20, j*20, 18, 18);
					g.setColor(Color.black);
				}
				//
			}
	}
	
	private void veKhung(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect(0, 0, WIDTH , HEIGHT);	
	}
	
	public void paint(Graphics g) {
		paintBg(g);
		ran.veRan(g);
		veKhung(g);
		
		if(!isPlaying) {
			g.setColor(Color.white);
			g.setFont(g.getFont().deriveFont(22.0f));
			g.drawString("PRESS SPACE TO PLAY GAME!", 40, 200);
		}
		if(isGameOver) {
			g.setColor(Color.white);
			g.setFont(g.getFont().deriveFont(25.0f));
			g.drawString("GAME OVER!", 100, 250);
		}
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(28.0f));
		g.drawString("LEVEL: "+CurrentLevel, 450, 50);
		
		g.setFont(g.getFont().deriveFont(20.0f));
		g.drawString("Diem: "+diem, 450, 100);
		
		for(int i=0; i<RanSanMoi.user.size(); i++) {
			g.drawString(RanSanMoi.user.get(i).toString(), 450, i*30+150 );
		}
	}
}
