package lab3.lib2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	
	private Key key;
	
	public void setKey(Key key) {
		this.key = key;
	}
	
	public void setKey(byte[] bkey) {
		Key key = new SecretKeySpec(bkey, ALGORITHM);
		setKey(key);
	}

	public int encrypt(InputStream in, OutputStream out) throws IOException {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			try(CipherOutputStream cout = new CipherOutputStream(out, cipher)) {
				StreamCopier copier = new StreamCopier(in, cout);
				return copier.copyAll();
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No AES support", e);
		}
	}

	public int decrypt(InputStream in, OutputStream out) throws IOException {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			try(CipherInputStream cin = new CipherInputStream(in, cipher)) {
				StreamCopier copier = new StreamCopier(cin, out);
				return copier.copyAll();
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No AES support", e);
		}
	}
	
	public byte[] encrypt(byte[] bytes) {
		return null;
	}
	
	public byte[] decrypt(byte[] bytes) {
		return null;
	}

}
