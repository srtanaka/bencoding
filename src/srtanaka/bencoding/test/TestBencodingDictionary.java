package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import srtanaka.bencoding.BencodeDictionary;
import srtanaka.bencoding.Bencoding;

public class TestBencodingDictionary {
  private static Map<Object, Object> SIMPLE_DICT;

  // @formatter:off
  static final String SIMPLE_DICT_STRING_ENCODED = "d" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    "e";
  // @formatter:on

  static final byte[] SIMPLE_DICT_BYTE_ENCODED = SIMPLE_DICT_STRING_ENCODED.getBytes(Bencoding.US_ASCII_CHARSET);

  @Before
  public void setup() {
    SIMPLE_DICT = new HashMap<Object, Object>();
    SIMPLE_DICT.put(TestBencodingByteString.STRING_1, TestBencodingByteString.STRING_2);
}

  @Test
  public void testEncodeDictionary() {
    try {
      assertEquals(BencodeDictionary.encode(SIMPLE_DICT), SIMPLE_DICT_STRING_ENCODED);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }
}
