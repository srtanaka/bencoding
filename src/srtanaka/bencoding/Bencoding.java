package srtanaka.bencoding;

import java.nio.charset.Charset;

/**
 * Bencoding control class to delegate encoding/decoding work.
 * 
 * @author srtanaka
 *
 */
public class Bencoding {
  /**
   * Default character encoding to use for Bencoding.
   * <p>
   * <code>"Bencode uses ASCII characters as delimiters and digits."</code>
   */
  public static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");
  public static final char SUFFIX = 'e';

  /**
   * @param o
   *          - the object to encode
   * @return an encoded byte string representation of the given input, using the
   *         default US-ASCII charset encoding
   * @throws IllegalArgumentException
   */
  public static byte[] encode(Object o) throws IllegalArgumentException {
    return Bencoding.encode(o, US_ASCII_CHARSET);
  }

  /**
   * @param o
   *          - the object to encode
   * @param c
   *          - the charset encoding to use
   * @return an encoded byte string representation of the given input
   * @throws IllegalArgumentException
   */
  public static byte[] encode(Object o, Charset c) throws IllegalArgumentException {
    if ( o instanceof Integer ) {
      return BencodeInteger.encode((int) o).getBytes(c);
    } else if ( o instanceof String ) {
      return BencodeByteString.encode((String) o).getBytes(c);
    } else {
      throw new IllegalArgumentException("Cannot encode this type of object!");
    }
  }
}
