package com.huowolf.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BottonPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton endButton;
	private JLabel scoreLabel;
	private int score;
	
	public BottonPanel() {

		//setFocusable(false);
		setLayout(null);
		setBounds(455, 0, 235, 450);
		
		startButton = new JButton("开始游戏");
		startButton.setBounds(10,20, 100, 25);
		add(startButton);
		
		pauseButton = new JButton("暂停游戏");
		pauseButton.setBounds(10, 60, 100, 25);
		add(pauseButton);	
		
		endButton = new JButton("结束游戏");
		endButton.setBounds(10,100, 100, 25);
		add(endButton);		
		
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Serif",Font.BOLD,18));
		scoreLabel.setBounds(30, 140, 100, 30);
		add(scoreLabel);
		
		/*
		scorePanel = new JPanel();
		scorePanel.setBounds(30, 180, 150, 100);
		//scorePanel.setBackground(Color.BLUE);
		add(scorePanel);*/
		
		Color c= new Color(0, 250,154);
		this.setBackground(c);
		
		this.setFocusable(true);
		
	}
	
	
	public JButton getStartButton() {
		return startButton;
	}


	public JButton getPauseButton() {
		return pauseButton;
	}


	public JButton getEndButton() {
		return endButton;
	}



	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.PINK);
		g.setFont(new Font("Serif",Font.BOLD,50));
		g.drawString(score+"", 40, 230);
	}
	

}
