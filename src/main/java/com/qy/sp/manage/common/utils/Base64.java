package com.qy.sp.manage.common.utils;

public class Base64 {

	public Base64() {}

	public static char[] encode(byte data[]) {
		char out[] = new char[((data.length + 2) / 3) * 4];
		int i = 0;
		for (int index = 0; i < data.length; index += 4) {
			boolean quad = false;
			boolean trip = false;
			int val = 0xff & data[i];
			val <<= 8;
			if (i + 1 < data.length) {
				val |= 0xff & data[i + 1];
				trip = true;
			}
			val <<= 8;
			if (i + 2 < data.length) {
				val |= 0xff & data[i + 2];
				quad = true;
			}
			out[index + 3] = alphabet[quad ? val & 0x3f : 64];
			val >>= 6;
			out[index + 2] = alphabet[trip ? val & 0x3f : 64];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3f];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3f];
			i += 3;
		}

		return out;
	}

	public static byte[] decode(char data[]) {
		int len = ((data.length + 3) / 4) * 3;
		if (data.length > 0 && data[len - 1] == '=')
			len--;
		if (data.length > 0 && data[len - 2] == '=')
			len--;
		byte out[] = new byte[len];
		int shift = 0;
		int accum = 0;
		int index = 0;
		for (int ix = 0; ix < data.length; ix++) {
			int value = codes[data[ix] & 0xff];
			if (value < 0)
				continue;
			accum <<= 6;
			shift += 6;
			accum |= value;
			if (shift >= 8) {
				shift -= 8;
				out[index++] = (byte) (accum >> shift & 0xff);
			}
		}

		if (index != out.length)
			throw new Error("miscalculated data length!");
		else
			return out;
	}

	public static void main(String args[]) {
		byte data[] = { 0, -1, 2, -3, 4, 5, -6, 7, 8, -9, 11, -12, 14, 15 };
		if (args.length == 1)
			data = args[0].getBytes();
		char code[] = encode(data);
		byte datb[] = decode(code);
		char recd[] = encode(datb);
		System.out.println("data = " + fromBytes(data));
		System.out.println("code = " + fromBytes(code));
		System.out.println("datb = " + fromBytes(datb));
		System.out.println("recd = " + fromBytes(recd));
		compare(data, datb);
		compare(code, recd);
	}

	static void compare(char b1[], char b2[]) {
		if (b1 == null || b2 == null)
			System.out.println("Null array!");
		else if (b1.length != b2.length) {
			System.out.println("arrays are different lengths!");
		} else {
			for (int i = 0; i < b1.length; i++)
				if (b1[i] != b2[i]) {
					System.out.println("arrays disagree at byte " + i);
					return;
				}

		}
	}

	static void compare(byte b1[], byte b2[]) {
		if (b1 == null || b2 == null) {
			System.out.println("Null array!");
			return;
		}
		if (b1.length != b2.length) {
			System.out.println("arrays are different lengths!");
			return;
		}
		for (int i = 0; i < b1.length; i++)
			if (b1[i] != b2[i]) {
				System.out.println("arrays disagree at byte " + i);
				return;
			}

	}

	static String fromBytes(byte data[]) {
		StringBuffer buf = new StringBuffer(data.length * 3);
		for (int i = 0; i < data.length; i++) {
			if (i > 0)
				buf.append(' ');
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() < 2)
				buf.append(' ');
			buf.append(hex);
		}

		return new String(buf);
	}

	static String fromBytes(char data[]) {
		return new String(data);
	}

	static char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
	static byte codes[];

	static {
		codes = new byte[256];
		for (int i = 0; i < 256; i++)
			codes[i] = -1;

		for (int i = 65; i <= 90; i++)
			codes[i] = (byte) (i - 65);

		for (int i = 97; i <= 122; i++)
			codes[i] = (byte) ((26 + i) - 97);

		for (int i = 48; i <= 57; i++)
			codes[i] = (byte) ((52 + i) - 48);

		codes[43] = 62;
		codes[47] = 63;
	}
}
