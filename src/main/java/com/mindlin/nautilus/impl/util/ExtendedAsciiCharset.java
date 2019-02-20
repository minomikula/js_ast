package com.mindlin.nautilus.impl.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.nio.charset.spi.CharsetProvider;
import java.util.Arrays;
import java.util.Iterator;

public class ExtendedAsciiCharset extends Charset {
	
	public static final ExtendedAsciiCharset INSTANCE = new ExtendedAsciiCharset();
	
	static final char[] unicodeLookup = new char[] { 0x00C7, 0x00FC, 0x00E9, 0x00E2, 0x00E4, 0x00E0, 0x00E5, 0x00E7,
			0x00EA, 0x00EB, 0x00E8, 0x00EF, 0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4, 0x00F6,
			0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2, 0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED,
			0x00F3, 0x00FA, 0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD, 0x00BC, 0x00A1, 0x00AB,
			0x00BB, 0x2591, 0x2592, 0x2593, 0x2502, 0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
			0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C, 0x2500, 0x253C, 0x255E, 0x255F, 0x255A,
			0x2554, 0x2569, 0x2566, 0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559, 0x2558, 0x2552,
			0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588, 0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393,
			0x03C0, 0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4, 0x221E, 0x03C6, 0x03B5, 0x2229,
			0x2261, 0x00B1, 0x2265, 0x2264, 0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A, 0x207F,
			0x00B2, 0x25A0, 0x00A0 };
	
	protected ExtendedAsciiCharset() {
		super("Extended_ASCII", new String[] { "ExtendedAscii", "EASCII" });
	}
	
	@Override
	public boolean contains(Charset cs) {
		return StandardCharsets.US_ASCII.contains(cs) || cs instanceof ExtendedAsciiCharset;
	}
	
	@Override
	public CharsetDecoder newDecoder() {
		return new ExtendedAsciiCharsetDecoder();
	}
	
	@Override
	public CharsetEncoder newEncoder() {
		return new ExtendedAsciiCharsetEncoder();
	}
	
	public static class ExtendedAsciiCharsetDecoder extends CharsetDecoder {
		
		protected ExtendedAsciiCharsetDecoder() {
			super(ExtendedAsciiCharset.INSTANCE, 1, 1);
		}

		@Override
		protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
			while (in.hasRemaining()) {
				final int code = in.get() & 0xFF;
				out.put((code >= 0x80 && code <= 0xFF) ? unicodeLookup[code - 0x80] : ((char) code));
			}
			return CoderResult.UNDERFLOW;
		}
	}
	
	public static class ExtendedAsciiCharsetEncoder extends CharsetEncoder {

		protected ExtendedAsciiCharsetEncoder() {
			super(ExtendedAsciiCharset.INSTANCE, 1, 1);
		}

		@Override
		protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
			while (in.hasRemaining()) {
				final char c = in.get();
				byte b;
				if (c >= 0x80) {
					b = 0;
					for (int i = 0; i < unicodeLookup.length; i++)
						if (unicodeLookup[i] == c) {
							b = (byte) i;
							break;
						}
					if (b == 0)
						return CoderResult.unmappableForLength(1);
				} else {
					b = (byte) c;
				}
				out.put(b);
			}
			return CoderResult.UNDERFLOW;
		}
		
	}
	
	public static class ExtendedAsciiCharsetProvider extends CharsetProvider {
		@Override
		public Charset charsetForName(String name) {
			return ExtendedAsciiCharset.INSTANCE.name().equals(name)
					|| ExtendedAsciiCharset.INSTANCE.aliases().contains(name) ? ExtendedAsciiCharset.INSTANCE : null;
		}
		
		@Override
		public Iterator<Charset> charsets() {
			return Arrays.asList((Charset) ExtendedAsciiCharset.INSTANCE).iterator();
		}
		
	}
}
