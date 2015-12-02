package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;

import org.junit.Test;

import srtanaka.bencoding.BencodeByteString;
import srtanaka.bencoding.MalformedBencodingException;

public class TestBencodingByteString {
  static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");

  static final String STRING_1 = "first";
  static final String STRING_2 = "second";
  static final String STRING_3 = "third";
  static final String EMPTY_STRING = "";

  static final String STRING_1_STRING_ENCODED = "5:first";
  static final String STRING_2_STRING_ENCODED = "6:second";
  static final String STRING_3_STRING_ENCODED = "5:third";
  static final String EMPTY_STRING_STRING_ENCODED = "0:";

  static final byte[] STRING_1_BYTE_ENCODED = STRING_1_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] STRING_2_BYTE_ENCODED = STRING_2_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] STRING_3_BYTE_ENCODED = STRING_3_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] EMPTY_STRING_BYTE_ENCODED = EMPTY_STRING_STRING_ENCODED.getBytes(US_ASCII_CHARSET);

  static final byte[] MALFORMED_1_ENCODED = "12:hello".getBytes(US_ASCII_CHARSET);
  static final byte[] MALFORMED_2_ENCODED = "34world".getBytes(US_ASCII_CHARSET);

  @Test
  public void testEncodeByteString() {
    assertEquals(BencodeByteString.encode(STRING_1), STRING_1_STRING_ENCODED);
    assertEquals(BencodeByteString.encode(STRING_2), STRING_2_STRING_ENCODED);
    assertEquals(BencodeByteString.encode(STRING_3), STRING_3_STRING_ENCODED);
  }

  @Test
  public void testEncodeEmptyByteString() {
    assertEquals(BencodeByteString.encode(EMPTY_STRING), EMPTY_STRING_STRING_ENCODED);
  }

  @Test
  public void testDecodeByteString() {
    try {
      assertEquals(BencodeByteString.decode(STRING_1_BYTE_ENCODED), STRING_1);
      assertEquals(BencodeByteString.decode(STRING_2_BYTE_ENCODED), STRING_2);
      assertEquals(BencodeByteString.decode(STRING_3_BYTE_ENCODED), STRING_3);
    } catch ( MalformedBencodingException e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testDecodeEmptyByteString() {
    try {
      assertEquals(BencodeByteString.decode(EMPTY_STRING_BYTE_ENCODED), EMPTY_STRING);
    } catch ( MalformedBencodingException e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test(expected = MalformedBencodingException.class)
  public void testDecodeLengthMismatchByteString() throws MalformedBencodingException {
    BencodeByteString.decode(MALFORMED_1_ENCODED);
  }

  @Test(expected = MalformedBencodingException.class)
  public void testDecodeNoSeparatorByteString() throws MalformedBencodingException {
    BencodeByteString.decode(MALFORMED_2_ENCODED);
  }
}
