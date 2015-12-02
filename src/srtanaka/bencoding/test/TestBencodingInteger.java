package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

import srtanaka.bencoding.BencodeInteger;

public class TestBencodingInteger {
  static final int INT = 123;

  static final String INT_STRING_ENCODED = "i123e";

  static final byte[] INT_BYTE_ENCODED = INT_STRING_ENCODED.getBytes(Charset.forName("US-ASCII"));

  @Test
  public void testEncodeInteger() {
    assertEquals(Arrays.toString(BencodeInteger.encode(INT)), Arrays.toString(INT_BYTE_ENCODED));
  }
}
