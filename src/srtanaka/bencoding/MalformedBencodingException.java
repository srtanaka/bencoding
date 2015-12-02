package srtanaka.bencoding;

/**
 * Checked throwable exception used when some encoding is malformed.
 * 
 * @author srtanaka
 *
 */
public class MalformedBencodingException extends Exception {
  private static final long serialVersionUID = 1L;

  MalformedBencodingException(String message) {
    super(message);
  }
}
