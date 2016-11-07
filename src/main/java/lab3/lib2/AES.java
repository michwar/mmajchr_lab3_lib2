package lab3.lib2;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class AES {
	
	public static final String ALGORITHM = "AES/CBC/PKCS5Padding";

	public int encrypt(InputStream in, OutputStream out) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			try(CipherOutputStream cout = new CipherOutputStream(out, cipher)) {
				StreamCopier copier = new StreamCopier(in, cout);
				return copier.copyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No AES support", e);
		}
	}

	public int decrypt(InputStream in, OutputStream out) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			try(CipherInputStream cin = new CipherInputStream(in, cipher)) {
				StreamCopier copier = new StreamCopier(cin, out);
				return copier.copyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No AES support", e);
		}
	}

}
