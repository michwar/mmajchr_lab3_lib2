package lab3.lib2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	public static final String KEY_ALGO = "AES";
	
	private Key key;
	
	public void setKey(Key key) {
		this.key = key;
	}
	
	public void setKey(byte[] bkey) {
		Key key = new SecretKeySpec(bkey, KEY_ALGO);
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
	
	public int encrypt(File src, File dst) throws IOException {
		try(FileInputStream fin = new FileInputStream(src)) {
			try(FileOutputStream fout = new FileOutputStream(dst)) {
				return encrypt(fin, fout);
			}
		}
	}
	
	public int decrypt(File src, File dst) throws IOException {
		try(FileInputStream fin = new FileInputStream(src)) {
			try(FileOutputStream fout = new FileOutputStream(dst)) {
				return decrypt(fin, fout);
			}
		}
	}
	
	public byte[] encrypt(byte[] bytes) {
		try {
			try(ByteArrayInputStream bin = new ByteArrayInputStream(bytes)) {
				try(ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
					encrypt(bin, bout);
					return bout.toByteArray();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("ByteArrays IOException", e);
		}
	}
	
	public byte[] decrypt(byte[] bytes) {
		try {
			try(ByteArrayInputStream bin = new ByteArrayInputStream(bytes)) {
				try(ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
					decrypt(bin, bout);
					return bout.toByteArray();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("ByteArrays IOException", e);
		}
	}

}
