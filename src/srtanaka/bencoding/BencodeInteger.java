package srtanaka.bencoding;

import java.nio.charset.Charset;

/**
 * Bencoding class to encode and decode integers.
 * 
 * @author srtanaka
 * 
 */
public class BencodeInteger {
  private static final char PREFIX = 'i';
  private static final char SUFFIX = 'e';
  private static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");

  /**
   * @param i
   *          - the integer to encode
   * @return an encoded byte string representation of the given input
   */
  public static byte[] encode(int i) {
    String s = BencodeInteger.PREFIX + Integer.toString(i) + BencodeInteger.SUFFIX;
    return s.getBytes(US_ASCII_CHARSET);
  }
}
