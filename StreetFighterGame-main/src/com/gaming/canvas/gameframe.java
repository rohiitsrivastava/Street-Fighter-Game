package com.gaming.canvas;

import java.io.IOException;

import javax.swing.JFrame;

import com.gaming.utils.GameConstant;



public class gameframe extends JFrame implements GameConstant  {
	
	public gameframe() throws IOException  {
		setResizable(false);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(GWIDTH, GHEIGHT);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board); // Board added in Frame.
		setVisible(true);
		
	}

	public static void main(String[] args) {
		
			try {
				gameframe obj = new gameframe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

}
