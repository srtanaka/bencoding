package srtanaka.bencoding;

/**
 * Bencoding class to encode and decode integers.
 * 
 * @author srtanaka
 * 
 */
public class BencodeInteger {
  public static final char PREFIX = 'i';
  public static final char SUFFIX = 'e';

  /**
   * @param i
   *          - the integer to encode
   * @return an encoded string representation of the given input
   */
  public static String encode(int i) {
    String encode = BencodeInteger.PREFIX + Integer.toString(i) + BencodeInteger.SUFFIX;
    return encode;
  }

  /**
   * @param b
   *          - the byte string to decode
   * @return the decoded integer
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static int decode(byte[] b) throws MalformedBencodingException {
    String s = new String(b, Bencoding.US_ASCII_CHARSET);
    return BencodeInteger.decode(s);
  }

  /**
   * @param s
   *          - the string to decode
   * @return the decoded integer
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static int decode(String s) throws MalformedBencodingException {
    if ( !s.startsWith(String.valueOf(BencodeInteger.PREFIX)) || !s.endsWith(String.valueOf(BencodeInteger.SUFFIX)) ) {
      throw new MalformedBencodingException("Integer not encoded properly");
    }

    return Integer.parseInt(s.substring(1, s.length() - 1));
  }
}
