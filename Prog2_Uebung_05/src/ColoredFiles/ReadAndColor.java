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
import javax.swing.filechooser.FileFilter;
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
		
		/* 
		 * choose random color from c[]
		 * make rColor the labels color for text
		*/
		Color rColor = c[ (int) (Math.random()*c.length) ];
		label.setForeground(rColor);
		
		frame.setVisible(true);
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private Color[] readFile() throws IOException {
		
		Color[] listColor = null;
		
		// create FileChooser Object
		File currentDirectory = new File(System.getProperty("user.dir"));
		choser = new JFileChooser(currentDirectory);
		
		/*
		 * FileChooser is opened
		 * returnVal saved the state of FileChooser on popdown
		 * 	•JFileChooser.CANCEL_OPTION 
		 * 	•JFileChooser.APPROVE_OPTION 
		 * 	•JFileChooser.ERROR_OPTION
		 */
		int returnVal = choser.showOpenDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file =  choser.getSelectedFile();
			
			Color tempColor;
			int red, green, blue;
			String currentLine;
			String[] rgbStringList;
			
			/*
			 * read the file chosen from JFileChooser line by line
			 * set the number of colors in listColor to the number of lines in file
			 * create a color-object out of the information in each line
			 */
			
			read = new BufferedReader(new FileReader(file));
			
			/* getSizeOfFile(BufferedReader) doesn't use the read BufferedReader but
			 * creates a equal new one, because the method getSizeOfFile closes the
			 * BufferedReader after using it. The BufferedReader is however still needed...
			 */
			listColor = new Color[getSizeOfFile(new BufferedReader(new FileReader(file)))];
			
			int count = 0;
			while((currentLine = read.readLine()) != null) {
				
				rgbStringList = currentLine.split(",");
				
				// TODO: if String Array is not 3 parts (red,green,blue) skip this string
				// Better: use String Format
				if(!(rgbStringList.length == 3))
					continue;
				
				red 	= Integer.parseInt(rgbStringList[0]);
				green 	= Integer.parseInt(rgbStringList[1]);
				blue 	= Integer.parseInt(rgbStringList[2]);
				tempColor = new Color(red,green,blue);
				
				listColor[count] = tempColor;
				count++;
			}
		}
		
		return listColor;
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