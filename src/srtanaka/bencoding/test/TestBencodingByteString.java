package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

import srtanaka.bencoding.BencodeByteString;

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

  static final byte[] TEST_STRING_1_BYTE_ENCODED = STRING_1_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] TEST_STRING_2_BYTE_ENCODED = STRING_2_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] TEST_STRING_3_BYTE_ENCODED = STRING_3_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] EMPTY_STRING_BYTE_ENCODED = EMPTY_STRING_STRING_ENCODED.getBytes(US_ASCII_CHARSET);

  @Test
  public void testEncodeByteString() {
    assertEquals(Arrays.toString(BencodeByteString.encode(STRING_1)), Arrays.toString(TEST_STRING_1_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeByteString.encode(STRING_2)), Arrays.toString(TEST_STRING_2_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeByteString.encode(STRING_3)), Arrays.toString(TEST_STRING_3_BYTE_ENCODED));
  }

  @Test
  public void testEncodeEmptyByteString() {
    assertEquals(Arrays.toString(BencodeByteString.encode(EMPTY_STRING)), Arrays.toString(EMPTY_STRING_BYTE_ENCODED));
  }
}
