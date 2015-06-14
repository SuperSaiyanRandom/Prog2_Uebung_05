package aufgabe2_4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TXTtoZIP {

	ZipOutputStream zos;
	public final File txtFile;
	public final File zipFile;
	
	public TXTtoZIP() {
		
		txtFile = createTXTFile("aufgabe2_4.txt");
		zipFile = createZipFile("aufgabe2_4.zip");
		
		System.out.println("Erfolgreich verpackt");
	}
	
	public File createZipFile(String filename) {
		try {
			File zipFile = new File(filename);
			
			zos = new ZipOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(zipFile)));
			copyTXTintoZip(txtFile, zos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {zos.close();} catch (IOException e) {e.printStackTrace();}
		}
		
		return zipFile;
	}
	
	
	private void copyTXTintoZip(File txtFile, ZipOutputStream zos) {
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(txtFile));
			
			ZipEntry entry = new ZipEntry(txtFile.getName());
			zos.putNextEntry(entry);
			byte[] buffer = new byte[2048];
			int lenBuffer;
			while ((lenBuffer = bis.read(buffer)) > 0) {
				zos.write(buffer, 0, lenBuffer);
			}
				
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	private static File createTXTFile(String filename) {
		File txtFile = new File(filename);
		
		try {
			txtFile.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(txtFile));
			
			bw.write("Hallo, ich bin gerade am Programmieren.");

			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return txtFile;
	}
	
	public static void main(String[] args) {
		new TXTtoZIP();
	}
	
}
