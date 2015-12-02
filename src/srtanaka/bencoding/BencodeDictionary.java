package srtanaka.bencoding;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Bencoding class to encode and decode dictionaries.
 * 
 * @author srtanaka
 *
 */
public class BencodeDictionary {
  public static final char PREFIX = 'd';
  public static final char SUFFIX = 'e';

  /**
   * @param m
   *          - the dictionary to encode
   * @return an encoded byte string representation of the given input
   */
  public static String encode(Map<Object, Object> m) {
    TreeMap<Object, Object> map = new TreeMap<Object,Object>(m);

    StringBuilder sb = new StringBuilder();

    sb.append(BencodeDictionary.PREFIX);

    for (Entry<Object, Object> e : map.entrySet()) {
      Object key = e.getKey();
      if ( !( key instanceof String ) ) {
        throw new IllegalArgumentException("Cannot encode non byte string keys");
      } else {
        sb.append(BencodeByteString.encode((String) key));
      }

      Object value = e.getValue();
      if ( value instanceof Integer ) {
        sb.append(BencodeInteger.encode((int) value));
      } else if ( value instanceof String ) {
        sb.append(BencodeByteString.encode((String) value));
      }
    }

    sb.append(Bencoding.SUFFIX);
    return sb.toString();
  }
}
