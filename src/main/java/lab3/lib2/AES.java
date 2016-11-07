package lab3.lib2;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

public class AES {
	
	public static final String ALGORITHM = "AES/CBC/PKCS5Padding";

	public int encrypt(InputStream src, OutputStream out) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			try(CipherOutputStream cout = new CipherOutputStream(out, cipher)) {
				StreamCopier copier = new StreamCopier(src, cout);
				return copier.copyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No AES support", e);
		}
	}

	void decrypt(InputStream src, OutputStream out) {
		
	}

}
