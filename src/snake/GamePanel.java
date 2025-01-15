package snake;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

/**
 * GamePanel is the main panel for the Snake game.
 * It handles the game logic, rendering, and user input.
 */
public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';

	Timer timer;
	Random random;

	public GamePanel() {
		// Initialize the game panel
		setFocusable(true);
		setPreferredSize(new java.awt.Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(java.awt.Color.BLACK);
		this.addKeyListener(new MyKeyAdapter());
		random = new Random();
		startGame();

		// Add any additional initialization code here
	}

	public boolean running = false;

	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);

		timer.start();
		// Initialize game state, start timers, etc.
	}


	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(java.awt.Graphics g) {
		// Draw the grid
		for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
		}
		for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
		}
		// Draw game elements (snake, apple, etc.)
		g.setColor(java.awt.Color.RED);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		//snake
		for (int i = 0; i < bodyParts; i++) {
			if (i == 0) {
				g.setColor(java.awt.Color.GREEN);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			} else {
				g.setColor(new java.awt.Color(45, 180, 0));
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
		// on the panel
	}

	public void newApple() {
		// Generate a new apple at a random position
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {
		// Update the position of the snake
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;
		}
	}

	public void checkApple() {
		// Check if the snake has eaten an apple
	}

	public void checkCollision() {
		// Check for collisions with walls or itself
	}

	public void gameOver(java.awt.Graphics g) {
		// Display game over screen
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollision();
		} else {
			repaint();
		}
	}

	public class MyKeyAdapter extends java.awt.event.KeyAdapter {
		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
			// Handle key events
		}
	}
}
