package lab3.lib2;

import java.io.InputStream;
import java.io.OutputStream;

public class StreamCopier {

	private byte[] buffer;
	private InputStream in;
	private OutputStream out;
	private int total;

	public static int STANDARD_BUFFER_SIZE = 0x10000;

	public StreamCopier(InputStream in, OutputStream out, int bufSize) {
		super();
		this.buffer = new byte[bufSize];
		this.in = in;
		this.out = out;
	}

	public StreamCopier(InputStream in, OutputStream out) {
		this(in, out, STANDARD_BUFFER_SIZE);
	}

}
