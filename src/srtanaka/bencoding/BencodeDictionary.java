package srtanaka.bencoding;

import java.util.HashMap;
import java.util.List;
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
  @SuppressWarnings("unchecked")
  public static String encode(Map<Object, Object> m) {
    TreeMap<Object, Object> map = new TreeMap<Object, Object>(m);

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
      } else if ( value instanceof List ) {
        sb.append(BencodeList.encode((List<Object>) value));
      } else if ( value instanceof Map<?, ?> ) {
        sb.append(BencodeDictionary.encode((Map<Object, Object>) value));
      } else {
        throw new IllegalArgumentException("Cannot encode unexpected type");
      }
    }

    sb.append(BencodeDictionary.SUFFIX);

    return sb.toString();
  }

  /**
   * @param b
   *          - the byte string to decode
   * @return the decoded dictionary
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static Map<Object, Object> decode(byte[] b) throws MalformedBencodingException {
    String s = new String(b, Bencoding.US_ASCII_CHARSET);

    return decode(s);
  }

  /**
   * @param s
   *          - the string to decode
   * @return the decoded dictionary
   * @throws MalformedBencodingException
   *           if the encoding is malformed
   */
  public static Map<Object, Object> decode(String s) throws MalformedBencodingException {
    if ( !s.startsWith(String.valueOf(PREFIX)) || !s.endsWith(String.valueOf(Bencoding.SUFFIX)) ) {
      throw new MalformedBencodingException("Dictionary not encoded properly");
    }

    Map<Object, Object> d = new HashMap<Object, Object>();

    s = s.substring(1, s.length() - 1);

    int endIndex;
    String substring;
    String key = null;

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
          endIndex = Integer.parseInt(s.split(String.valueOf(BencodeByteString.SEPARATOR), 2)[0]) + s.indexOf(BencodeByteString.SEPARATOR);
          substring = s.substring(0, s.indexOf(BencodeByteString.SEPARATOR) + endIndex);
          if ( key == null ) {
            key = BencodeByteString.decode(substring);
          } else {
            d.put(key, BencodeByteString.decode(substring));
            key = null;
          }
          s = s.substring(endIndex + 1);
          break;
        case BencodeInteger.PREFIX:
          endIndex = s.indexOf(BencodeInteger.SUFFIX);
          substring = s.substring(0, endIndex + 1);
          if ( key == null ) {
            throw new MalformedBencodingException(
              "Missing key for this dictionary entry");
          } else {
            d.put(key, BencodeInteger.decode(substring));
            key = null;
          }
          s = s.substring(endIndex + 1);
          break;
        case BencodeList.PREFIX:
          endIndex = Bencoding.findEndIndex(s.substring(1), 1);
          endIndex++;
          substring = s.substring(0, endIndex + 1);
          if ( key == null ) {
            throw new MalformedBencodingException(
              "Missing key for this dictionary entry");
          } else {
            d.put(key, BencodeList.decode(substring));
            key = null;
          }
          s = s.substring(endIndex + 1);
          break;
        case BencodeDictionary.PREFIX:
          endIndex = Bencoding.findEndIndex(s.substring(1), 1);
          endIndex++;
          substring = s.substring(0, endIndex + 1);
          if ( key == null ) {
            throw new MalformedBencodingException(
              "Missing key for this dictionary entry");
          } else {
            d.put(key, BencodeDictionary.decode(substring));
            key = null;
          }
          s = s.substring(endIndex + 1);
          break;
        default:
          throw new MalformedBencodingException(
            "Dictionary not encoded properly");
      }
    }

    return d;
  }
}
