package ColoredFiles;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadAndColor {

	BufferedReader read,counter;
	String f = "RGBColors";
	String[][] calc;
	JFileChooser choser;

	public ReadAndColor() throws IOException {
		Color[] c =readFile();
		createGui(c);
	}

	private void createGui(Color[] c) {
		JFrame frame = new JFrame("Colored");
		JLabel label = new JLabel("Color");
		frame.add(label);
		
		//TODO 
		
		frame.setVisible(true);
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private Color[] readFile() throws IOException {
		//TODO
		return null;
	}

	private int getSizeOfFile(BufferedReader read) throws IOException {
		int cnt = 0;
		while (read.readLine() != null) {
			cnt++;
		}
		read.close();
		return cnt;
	}

	public static void main(String[] args) {
		try {
			new ReadAndColor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}