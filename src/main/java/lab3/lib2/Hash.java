package lab3.lib2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	MessageDigest md;
	
	public Hash(String algorithm) throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance(algorithm);
	}

}
