package ransanmoi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class RanSanMoi extends JFrame {
	
	GameScreen game;
	
	public static ArrayList<User> user;
	
	public RanSanMoi() {
		this.setSize(600,435);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Game ran");
		this.setLocation(400, 200);
		
		user = new ArrayList<User>();
		ReadData();
		
		game = new GameScreen();
		add(game);
		
		this.addKeyListener(new xuLy());
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				UpdateData();
			}
			
		});
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		RanSanMoi f = new RanSanMoi();
	}
	
	private class xuLy implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				GameScreen.isPlaying=!GameScreen.isPlaying;
				if(GameScreen.isGameOver) {
					GameScreen.isGameOver = false;
					game.ran.resetGame();
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				game.ran.setVector(ConRan.LEN);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.ran.setVector(ConRan.XUONG);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.ran.setVector(ConRan.TRAI);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.ran.setVector(ConRan.PHAI);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	public static void UpdateData() {
		FileWriter fw;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("data/data.txt");
			bw = new BufferedWriter(fw);
			
			for(User u: user) {
				bw.write(u.getName() + " " + u.getLevel() + " " + u.getDiem());
				bw.newLine();
			}
		} catch (IOException e) {}
		finally {
			try {
				bw.close();
			} catch (IOException e) {}
		}
	}
	
	public static void ReadData() {
		FileReader fr;
		BufferedReader br = null;
		try {
			fr = new FileReader("data/data.txt");
			br = new BufferedReader(fr);
			
			String line = null;
			while((line = br.readLine()) != null){
				String[] str = line.split(" ");
				user.add(new User(str[0], str[1], str[2]));
			}
		} catch (IOException e) {}
		finally {
			try {
				br.close();
			} catch (IOException e) {}
		}
	}
}
