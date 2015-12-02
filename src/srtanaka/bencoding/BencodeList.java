package srtanaka.bencoding;

import java.util.ArrayList;
import java.util.List;

/**
 * Bencoding class to encode and decode lists.
 * 
 * @author srtanaka
 * 
 */
public class BencodeList {
  public static final char PREFIX = 'l';
  public static final char SUFFIX = 'e';

  /**
   * @param l
   *          - the list to encode
   * @return an encoded byte string representation of the given input
   */
  @SuppressWarnings("unchecked")
  public static String encode(List<Object> l) {
    StringBuilder sb = new StringBuilder();

    sb.append(BencodeList.PREFIX);

    for (Object o : l) {
      if ( o instanceof Integer ) {
        sb.append(BencodeInteger.encode((int) o));
      } else if ( o instanceof String ) {
        sb.append(BencodeByteString.encode((String) o));
      } else if ( o instanceof List ) {
        sb.append(BencodeList.encode((List<Object>) o));
      }
    }

    sb.append(BencodeList.SUFFIX);

    return sb.toString();
  }

  /**
   * @param b
   *          - the byte string to decode
   * @return the decoded list
   * @throws Exception
   *           if the encoding is malformed
   */
  public static List<Object> decode(byte[] b) throws MalformedBencodingException {
    String s = new String(b, Bencoding.US_ASCII_CHARSET);

    return decode(s);
  }
  /**
   * @param s
   *          - the string to decode
   * @return the decoded list
   * @throws Exception
   *           if the encoding is malformed
   */
  public static List<Object> decode(String s) throws MalformedBencodingException {
    if ( !s.startsWith(String.valueOf(BencodeList.PREFIX)) || !s.endsWith(String.valueOf(BencodeList.SUFFIX)) ) {
      throw new MalformedBencodingException("List not encoded properly");
    }

    s = s.substring(1, s.length() - 1);

    List<Object> l = new ArrayList<Object>();

    int endIndex;
    String substring;
    while ( s.length() > 0 ) {
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
          endIndex = Integer.parseInt(s.split(String.valueOf(BencodeByteString.SEPARATOR), 2)[0]) + s.indexOf(":");
          substring = s.substring(0, s.indexOf(BencodeByteString.SEPARATOR) + endIndex);
          l.add(BencodeByteString.decode(substring));
          s = s.substring(endIndex + 1);
          break;
        case BencodeInteger.PREFIX:
          endIndex = s.indexOf(BencodeInteger.SUFFIX);
          substring = s.substring(0, endIndex + 1);
          l.add(BencodeInteger.decode(substring));
          s = s.substring(endIndex + 1);
          break;
        default:
          throw new MalformedBencodingException("List not encoded properly");
      }
    }

    return l;
  }
}
