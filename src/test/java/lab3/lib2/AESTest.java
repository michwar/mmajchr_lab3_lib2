package lab3.lib2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AESTest {
	
	@Test
	public void test() {
		byte[] key = "BardzoTajneHaslo".getBytes();
		AES aes = new AES();
		aes.setKey(key);
		String dane = "BardzoTajneDane1BardzoTajneDane2BardzoTajneDane3";
		byte[] szyfr = aes.encrypt(dane.getBytes());
		String dane2 = new String(aes.decrypt(szyfr));
		assertTrue(dane.equals(dane2));
	}

}
