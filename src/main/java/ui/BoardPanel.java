package ui;

import game.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	Board board;
	private static JButton startButton;
	private JButton[][] cells;

	public void init(int width, int height) {
		board = new Board(width, height);
		cells = new JButton[width][height];
		setLayout(new GridLayout(width, height));
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				JButton currentButton = new LifeCell(i, j);
				currentButton.setBackground(Color.black);
				currentButton.addActionListener(new CellActionListener());
				cells[i][j] = currentButton;
				add(currentButton);
			}
		}
	}

	public static void main(String[] args) {
		int width = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Columns. Integers Only.",null));
		int height = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Rows. Integers Only.",null));
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.init(width, height);
		frame.add(boardPanel, BorderLayout.CENTER);
		startButton = new JButton("Start");
		startButton.addActionListener(boardPanel.new StartButtonListener());
		frame.add(startButton, BorderLayout.SOUTH);
		frame.setSize(width*LifeCell.SIZE,height*LifeCell.SIZE+30);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void run() {
		Thread thread = new Thread(new Runnable(){
			public void run(){
				long timer = System.currentTimeMillis();
				while (true) {
					if (System.currentTimeMillis() - timer > 1000) {
						board.update();
						updateButtonColor();
						timer += 1000;
					}
				}
			}
		});
		thread.start();
	}
	
	public void updateButtonColor(){
		for(int r=0;r<cells.length;r++){
			for(int c=0;c<cells[0].length;c++){
				boolean alive = board.getStateAt(r, c);
				LifeCell button = (LifeCell)cells[r][c];
				if(alive) button.setBackground(Color.GREEN);
				else button.setBackground(Color.BLACK);
			}
		}
	}

	private class CellActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(!startButton.isEnabled()) return;
			LifeCell source = (LifeCell) event.getSource();
			boolean updatedCellIsAlive = board.changeState(source.row, source.column);
			if (updatedCellIsAlive)
				source.setBackground(Color.GREEN);
			else
				source.setBackground(Color.BLACK);
		}
	}
	
	private class StartButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton start = (JButton) e.getSource();
			start.setEnabled(false);
			run();
		}
		
	}

}
