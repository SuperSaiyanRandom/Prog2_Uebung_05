package ColoredFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadColors {

	BufferedReader reader;
	BufferedWriter write;
	String f = "RGBColors";
	
	
	public ReadColors() throws IOException {
		
			
			// local txt-file "RGBNumber.txt" is created in program folder
			File file = new File("RGBNumber.txt");
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			write = new BufferedWriter(fw);
			
			for(int i=0; i<5; i++){
				// (int) (Math.random()*256) : returns a random int between 0 and 256
				write.write( ((int) (Math.random()*256)) + "," );
				write.write( ((int) (Math.random()*256)) + "," );
				write.write( ((int) (Math.random()*256)) + "\n" );
			}
			
			write.close();
	}

	public static void main(String[] args) {
		try {
			new ReadColors();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
