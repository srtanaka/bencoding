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

  /**
   * @param b
   *          - the byte string to decode
   * @return the decoded string
   * @throws Exception
   *           if the encoding is malformed
   */
  public static String decode(byte[] b) throws MalformedBencodingException {
    String s = new String(b, US_ASCII_CHARSET);

    if ( s.indexOf(SEPARATOR) == -1 ) {
      throw new MalformedBencodingException("No separator character");
    }
    int length = Integer.parseInt(s.substring(0, s.indexOf(SEPARATOR)));

    s = s.substring(s.indexOf(SEPARATOR) + 1);

    if ( s.length() != length ) {
      throw new MalformedBencodingException("Length mismatch");
    }

    return s;
  }
}
