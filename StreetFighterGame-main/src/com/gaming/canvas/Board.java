package com.gaming.canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gaming.spirtes.KenPlayer;
import com.gaming.spirtes.power;
import com.gaming.spirtes.RyuPlayer;
import com.gaming.utils.GameConstant;




public class Board extends JPanel implements GameConstant {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private power ryuFullPower;
	private power kenFullPower;
	private Timer timer;
	private boolean gameOver;
	
	private void gameLoop() {
		timer = new Timer(GAME_LOOP,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				if(gameOver) {
					timer.stop();
				}
				ryuPlayer.fall();
				collision();
				isGameOver();
				
			}
		});
		timer.start();
	}
	
	private void loadPower() {
		ryuFullPower = new power(30, "Ryu");
		kenFullPower = new power(GWIDTH - 550, "Ken");
	}
	
	private void printFullPower(Graphics g) {
		ryuFullPower.printRectangle(g);
		kenFullPower.printRectangle(g);
	}
	
	private boolean isCollide() {
		int xDistance = Math.abs(ryuPlayer.getX() - kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY()- kenPlayer.getY());
		int maxH = Math.max(ryuPlayer.getH(), kenPlayer.getH());
		int maxW = Math.max(ryuPlayer.getW(), kenPlayer.getW());
		return xDistance<=(maxW-50) && yDistance<=(maxH-50);
		
	}
	
	private void collision() {
		if(isCollide()) {
			if(ryuPlayer.isAttacking() && kenPlayer.isAttacking()) {
				
			}
			else
			if(ryuPlayer.isAttacking())
			{
				kenPlayer.setCurrentMove(DAMAGE);
				kenFullPower.setHealth();
			}
			else if(kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				ryuFullPower.setHealth();

			}
			ryuPlayer.setCollide(true);
			//System.out.println("Collision.....");
			ryuPlayer.setSpeed(0);
		}
		else {
			ryuPlayer.setCollide(false);
			ryuPlayer.setSpeed(SPEED);
		}
	}
	
	private void isGameOver() {
		if(ryuFullPower.getHealth()<=0 || kenFullPower.getHealth()<=0) {
			gameOver = true;
		}
		
	}
	
	private void printGameOver(Graphics pen) {
		if(gameOver) {
			pen.setColor(Color.RED);
		pen.setFont(new Font("times", Font.BOLD, 40));
		pen.drawString("Game Over", GWIDTH/2-100, GHEIGHT/2-100);
		}
	}
	
	
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
		
		
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ryuPlayer.setSpeed(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					ryuPlayer.setSpeed(-SPEED);
					ryuPlayer.setCollide(false);
					ryuPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_W) {
					kenPlayer.setCurrentMove(KICK);
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					ryuPlayer.jump();
				}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					kenPlayer.jump();
				}
				else if (e.getKeyCode() == KeyEvent.VK_F) {
					kenPlayer.setCurrentMove(PUNCH);;
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ryuPlayer.setSpeed(SPEED);
					ryuPlayer.move();
					//repaint();
				}
				// Ryu Kick
				else if (e.getKeyCode()== KeyEvent.VK_K) {
					ryuPlayer.setCurrentMove(KICK);
					
				}
				// Ryu Punch
				else if (e.getKeyCode()== KeyEvent.VK_P) {
					ryuPlayer.setCurrentMove(PUNCH);
				}
				// Ken 
				else if (e.getKeyCode() == KeyEvent.VK_J) {
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.move();
					//repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_L) {
					kenPlayer.setSpeed(SPEED);
					kenPlayer.move();
					//repaint();
				}
				
				
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics pen) {
		// Rendering / Painting
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		printFullPower(pen);
		printGameOver(pen);
		
		
		
	}

	
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,0, 1400,900, null);
	}
	
	
	
	private void loadBackgroundImage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource("bg.jpeg"));
			}
			catch(Exception ex) {
				System.out.println("Background Image Loading Fail...");
				System.exit(0);
			
			}
	}
}
