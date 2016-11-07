package lab3.lib2;

import java.io.InputStream;
import java.io.OutputStream;

public class StreamCopier {

	private byte[] buffer;
	private InputStream in;
	private OutputStream out;
	private int total;

	public StreamCopier(InputStream in, OutputStream out, int bufSize) {
		super();
		this.buffer = new byte[bufSize];
		this.in = in;
		this.out = out;
	}

}
