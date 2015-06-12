package aufgabe2_3_zipFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JFileChooser;

public class ReadTXTfromZip {
	
	public ReadTXTfromZip() {
		
		try {
			
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
			
			// open up the FileChooser Dialog
			int returnVal = fileChooser.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				
				final ZipFile zipFile = new ZipFile(fileChooser.getSelectedFile());
				
				// find the .txt File within the .zip archive
				ZipEntry txtFileEntry = findTXTFile(zipFile);
				
				// build a Stream to the .txt File in the .zip archive
				InputStream is = zipFile.getInputStream(txtFileEntry);
				/*
				 * InputStream is wrapped in a InputStreamReader to decode from binary to text
				 * InputStreamReader is then wrapped in a BufferedReader in order to be able to
				 * read a line at a time...
				 */
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				// Print .txt File content onto console
				String output;
				while((output = br.readLine()) != null) {
					System.out.println(output);
				}

				br.close();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Searches for the first TXT-File it can find in the .zip archive
	 * @param zipFile .zip archive that should be searched
	 * @return returns first .txt File found in the .zip archive
	 * 		   if no .txt File is found null is returned
	 */	
	private ZipEntry findTXTFile(ZipFile zipFile) {
		ZipEntry txtFileEntry = null;
		
		final Enumeration<? extends ZipEntry> entries = zipFile.entries();
		
		while(entries.hasMoreElements()) {
			final ZipEntry zipEntry = entries.nextElement();
			
			if(!zipEntry.isDirectory()) {
				final String fileName = zipEntry.getName();
				if(fileName.toLowerCase().endsWith(".txt"))
					txtFileEntry = zipEntry;
					break;
			}
		}
		
		return txtFileEntry;
	}
	
	public static void main(String[] args) {
		new ReadTXTfromZip();
	}
}
