package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import controller.Game;
import controller.FileLoader;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class ScrGame extends JFrame {
	private static final long serialVersionUID = 1L;
	public JLabel statusbar;
	public JLabel pause;
	private boolean isMute = false;

	public JLabel getStatusbar() {
		return statusbar;
	}

	public void setStatusbar(JLabel statusbar) {
		this.statusbar = statusbar;
	}

	public JLabel getPause() {
		return pause;
	}

	public void setPause(JLabel pause) {
		this.pause = pause;
	}

	Clip musi;

	public ScrGame() {

		setResizable(false);
		setTitle("Tetris");
		setSize(454, 567);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		statusbar = new JLabel("0");
		pause = new JLabel("run");
		var board = new Game(this);
		musi = FileLoader.LoadSound("/music.wav");
		musi.loop(Clip.LOOP_CONTINUOUSLY);

		JToggleButton music = new JToggleButton("");
		music.setSelected(true);
		music.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (music.isSelected()) {
					musi.start();
				}
				if (!music.isSelected()) {
					musi.stop();

				}
			}

		});

		music.setSelectedIcon(new ImageIcon(FileLoader.loadImage("/music.png")));
		music.setIcon(new ImageIcon(FileLoader.loadImage("/mute.png")));
		music.setBounds(348, 330, 48, 46);
		getContentPane().add(music);

		pause.setFont(new Font("Ink Free", Font.BOLD, 30));
		pause.setHorizontalAlignment(SwingConstants.CENTER);
		pause.setForeground(Color.WHITE);
		pause.setBounds(326, 249, 95, 32);
		getContentPane().add(pause);

		statusbar.setHorizontalAlignment(SwingConstants.CENTER);
		statusbar.setFont(new Font("Ink Free", Font.BOLD, 49));
		statusbar.setForeground(Color.WHITE);
		statusbar.setBounds(310, 158, 111, 59);
		getContentPane().add(statusbar);

		board.setBounds(0, 0, 300, 530);

		getContentPane().add(board);

		JLabel score = new JLabel("Score");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setForeground(new Color(255, 255, 255));
		score.setFont(new Font("Ink Free", Font.BOLD, 36));
		score.setBounds(320, 109, 101, 38);
		getContentPane().add(score);

		JLabel exhome = new JLabel("Quit");
		exhome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		exhome.setHorizontalAlignment(SwingConstants.CENTER);
		exhome.setFont(new Font("Ink Free", Font.BOLD, 35));
		exhome.setForeground(Color.WHITE);
		exhome.setBounds(326, 434, 95, 59);
		getContentPane().add(exhome);

		JLabel bgrJLabel = new JLabel("");
		bgrJLabel.setIcon(new ImageIcon(FileLoader.loadImage("/backGround.png")));
		bgrJLabel.setBounds(0, 0, 448, 538);
		getContentPane().add(bgrJLabel);
		board.start();

	}

	public JLabel getStatusBar() {

		return statusbar;
	}

	public boolean isMute() {
		return isMute;
	}

	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}

	public Clip getMusi() {
		return musi;
	}

	public void setMusi(Clip musi) {
		this.musi = musi;
	}

}
