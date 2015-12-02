package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

import srtanaka.bencoding.BencodeInteger;
import srtanaka.bencoding.MalformedBencodingException;

public class TestBencodingInteger {
  static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");

  static final int POSITIVE_INT_1 = 123;
  static final int POSITIVE_INT_2 = 2345;
  static final int POSITIVE_INT_3 = 34567;
  static final int ZERO_INT = 0;
  static final int NEGATIVE_INT_1 = -456;
  static final int NEGATIVE_INT_2 = -5678;
  static final int NEGATIVE_INT_3 = -67890;

  static final String POSITIVE_INT_1_STRING_ENCODED = "i123e";
  static final String POSITIVE_INT_2_STRING_ENCODED = "i2345e";
  static final String POSITIVE_INT_3_STRING_ENCODED = "i34567e";
  static final String ZERO_INT_STRING_ENCODED = "i0e";
  static final String NEGATIVE_INT_1_STRING_ENCODED = "i-456e";
  static final String NEGATIVE_INT_2_STRING_ENCODED = "i-5678e";
  static final String NEGATIVE_INT_3_STRING_ENCODED = "i-67890e";

  static final byte[] POSITIVE_INT_1_BYTE_ENCODED = POSITIVE_INT_1_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] POSITIVE_INT_2_BYTE_ENCODED = POSITIVE_INT_2_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] POSITIVE_INT_3_BYTE_ENCODED = POSITIVE_INT_3_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] ZERO_INT_BYTE_ENCODED = ZERO_INT_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] NEGATIVE_INT_1_BYTE_ENCODED = NEGATIVE_INT_1_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] NEGATIVE_INT_2_BYTE_ENCODED = NEGATIVE_INT_2_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] NEGATIVE_INT_3_BYTE_ENCODED = NEGATIVE_INT_3_STRING_ENCODED.getBytes(US_ASCII_CHARSET);

  static final byte[] MALFORMED_BYTE_ENCODED = "i1234".getBytes(US_ASCII_CHARSET);

  @Test
  public void testEncodeInteger() {
    assertEquals(Arrays.toString(BencodeInteger.encode(POSITIVE_INT_1)), Arrays.toString(POSITIVE_INT_1_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeInteger.encode(POSITIVE_INT_2)), Arrays.toString(POSITIVE_INT_2_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeInteger.encode(POSITIVE_INT_3)), Arrays.toString(POSITIVE_INT_3_BYTE_ENCODED));
  }

  @Test
  public void testEncodeZeroInteger() {
    assertEquals(Arrays.toString(BencodeInteger.encode(ZERO_INT)), Arrays.toString(ZERO_INT_BYTE_ENCODED));
  }

  @Test
  public void testEncodeNegativeInteger() {
    assertEquals(Arrays.toString(BencodeInteger.encode(NEGATIVE_INT_1)), Arrays.toString(NEGATIVE_INT_1_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeInteger.encode(NEGATIVE_INT_2)), Arrays.toString(NEGATIVE_INT_2_BYTE_ENCODED));
    assertEquals(Arrays.toString(BencodeInteger.encode(NEGATIVE_INT_3)), Arrays.toString(NEGATIVE_INT_3_BYTE_ENCODED));
  }

  @Test
  public void testDecodeInteger() {
    assertEquals(BencodeInteger.decode(POSITIVE_INT_1_BYTE_ENCODED), POSITIVE_INT_1);
    assertEquals(BencodeInteger.decode(POSITIVE_INT_2_BYTE_ENCODED), POSITIVE_INT_2);
    assertEquals(BencodeInteger.decode(POSITIVE_INT_3_BYTE_ENCODED), POSITIVE_INT_3);
  }

  @Test
  public void testDecodeZeroInteger() {
    assertEquals(BencodeInteger.decode(ZERO_INT_BYTE_ENCODED), ZERO_INT);
  }

  @Test
  public void testDecodeNegativeInteger() {
    assertEquals(BencodeInteger.decode(NEGATIVE_INT_1_BYTE_ENCODED), NEGATIVE_INT_1);
    assertEquals(BencodeInteger.decode(NEGATIVE_INT_2_BYTE_ENCODED), NEGATIVE_INT_2);
    assertEquals(BencodeInteger.decode(NEGATIVE_INT_3_BYTE_ENCODED), NEGATIVE_INT_3);
  }

  @Test(expected = MalformedBencodingException.class)
  public void testDecodeMalEncodedInteger() throws MalformedBencodingException {
    BencodeInteger.decode(MALFORMED_BYTE_ENCODED);
  }
}
