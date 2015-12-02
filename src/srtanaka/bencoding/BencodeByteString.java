package srtanaka.bencoding;

import java.nio.charset.Charset;

/**
 * Bencoding class to encode and decode byte strings.
 * 
 * @author srtanaka
 * 
 */
public class BencodeByteString {
  private static final char SEPARATOR = ':';
  private static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");

  /**
   * @param s
   *          - the string to encode
   * @return an encoded byte string representation of the given input
   */
  public static byte[] encode(String s) {
    String encode = s.length() + String.valueOf(SEPARATOR) + s;
    return encode.getBytes(US_ASCII_CHARSET);
  }
}
