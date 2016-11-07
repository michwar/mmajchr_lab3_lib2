package lab3.lib2;

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

}
