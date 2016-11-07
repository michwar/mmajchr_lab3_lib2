package lab3.lib2;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	MessageDigest md;
	
	public Hash(String algorithm) throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance(algorithm);
	}
	
	public Hash(HashType type) {
		try {
			this.md = MessageDigest.getInstance(type.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No " + type + " support.", e);
		}
	}
	
	public byte[] hash(byte[] data) {
		try {
			MessageDigest md = (MessageDigest)this.md.clone();
			return md.digest(data);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't hash", e);
		}
	}
	
	public byte[] hash(InputStream in) throws IOException {
		try {
			MessageDigest md = (MessageDigest)this.md.clone();
			byte[] buffer = new byte[4096];
			for(;;) {
				int read = in.read(buffer);
				if(read < 0) {
					break;
				}
				md.update(buffer, 0, read);
			}
			return md.digest();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't hash", e);
		}
	}

}
