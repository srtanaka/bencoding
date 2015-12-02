package srtanaka.bencoding;

import java.util.List;

/**
 * Bencoding class to encode and decode lists.
 * 
 * @author srtanaka
 * 
 */
public class BencodeList {
  private static final char PREFIX = 'l';
  private static final char SUFFIX = 'e';

  /**
   * @param l
   *          - the list to encode
   * @return an encoded byte string representation of the given input
   */
  public static String encode(List<Object> l) {
    StringBuilder sb = new StringBuilder();

    sb.append(BencodeList.PREFIX);

    for (Object o : l) {
      if ( o instanceof Integer ) {
        sb.append(BencodeInteger.encode((int) o));
      } else if ( o instanceof String ) {
        sb.append(BencodeByteString.encode((String) o));
      }
    }

    sb.append(BencodeList.SUFFIX);

    return sb.toString();
  }
}
