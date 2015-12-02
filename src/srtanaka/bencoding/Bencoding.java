package srtanaka.bencoding;

import java.nio.charset.Charset;
import java.util.List;

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
    } else if (o instanceof List) {
      return BencodeList.encode((List) o).getBytes(c);
    } else {
      throw new IllegalArgumentException("Cannot encode this type of object!");
    }
  }

  /**
   * Convenience method to decode an encoded byte string without having to know
   * its contents.
   * 
   * @param b
   *          - the byte string to decode
   * @return a decoded object.
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static Object decode(byte[] b) throws MalformedBencodingException {
    String s = new String(b, Bencoding.US_ASCII_CHARSET);
    return decode(s);
  }

  /**
   * Convenience method to decode an encoded string without having to know its
   * contents.
   * 
   * @param s
   *          - the string to decode
   * @return a decoded object.
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static Object decode(String s) throws MalformedBencodingException {
    switch ( s.charAt(0) ) {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        return BencodeByteString.decode(s);
      case BencodeInteger.PREFIX:
        return BencodeInteger.decode(s);
      default:
        throw new MalformedBencodingException(
          "Malformed encoding, could not decode!");
    }
  }
}
