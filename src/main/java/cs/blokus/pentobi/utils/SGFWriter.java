package cs.blokus.pentobi.utils;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

@Component
public class SGFWriter {
	
	public void createNewFile(Long idGame) {
		String filename = "./game" + idGame + ".blksgf";
		new File(filename);
		String data = "(;GM[Blokus Two-Player]CA[UTF-8])";
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		    outStream.writeBytes(data);
		    outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(Long idGame, String move) {
		try {
			String filename = "./game" + idGame + ".blksgf";
			FileOutputStream fos = new FileOutputStream(filename);
		    FileInputStream fis = new FileInputStream(filename);
		    DataInputStream reader = new DataInputStream(fis);
		    String data = reader.readUTF();
		    String writeData = data.replace(")", "");
		    writeData += move;
		    reader.close();
			DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		    outStream.writeUTF(writeData);
		    outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
