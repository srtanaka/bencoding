package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import srtanaka.bencoding.BencodeDictionary;
import srtanaka.bencoding.Bencoding;

public class TestBencodingDictionary {
  private static Map<Object, Object> SIMPLE_DICT;
  private static Map<Object, Object> LEXICAL_DICT;
  private static List<Object> TEST_LIST;
  private static Map<Object, Object> COMPLEX_DICT;

  // @formatter:off
  static final String SIMPLE_DICT_STRING_ENCODED = "d" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    "e";

  static final String LEXICAL_DICT_STRING_ENCODED = "d" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingInteger.POSITIVE_INT_1_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    TestBencodingInteger.ZERO_INT_STRING_ENCODED + 
    TestBencodingByteString.STRING_3_STRING_ENCODED + 
    TestBencodingInteger.NEGATIVE_INT_1_STRING_ENCODED + 
    "e";

  static final String COMPLEX_DICT_STRING_ENCODED = "d" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingList.INT_LIST_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    SIMPLE_DICT_STRING_ENCODED + 
    "e";
  // @formatter:on

  static final byte[] SIMPLE_DICT_BYTE_ENCODED = SIMPLE_DICT_STRING_ENCODED.getBytes(Bencoding.US_ASCII_CHARSET);
  static final byte[] LEXICAL_DICT_BYTE_ENCODED = LEXICAL_DICT_STRING_ENCODED.getBytes(Bencoding.US_ASCII_CHARSET);
  static final byte[] COMPLEX_DICT_BYTE_ENCODED = COMPLEX_DICT_STRING_ENCODED.getBytes(Bencoding.US_ASCII_CHARSET);

  @Before
  public void setup() {
    SIMPLE_DICT = new HashMap<Object, Object>();
    SIMPLE_DICT.put(TestBencodingByteString.STRING_1, TestBencodingByteString.STRING_2);

    LEXICAL_DICT = new HashMap<Object, Object>();
    LEXICAL_DICT.put(TestBencodingByteString.STRING_1, TestBencodingInteger.POSITIVE_INT_1);
    LEXICAL_DICT.put(TestBencodingByteString.STRING_3, TestBencodingInteger.NEGATIVE_INT_1);
    LEXICAL_DICT.put(TestBencodingByteString.STRING_2, TestBencodingInteger.ZERO_INT);

    TEST_LIST = new ArrayList<Object>();
    TEST_LIST.add(TestBencodingInteger.POSITIVE_INT_1);
    TEST_LIST.add(TestBencodingInteger.ZERO_INT);
    TEST_LIST.add(TestBencodingInteger.NEGATIVE_INT_1);

    COMPLEX_DICT = new HashMap<Object, Object>();
    COMPLEX_DICT.put(TestBencodingByteString.STRING_1, TEST_LIST);
    COMPLEX_DICT.put(TestBencodingByteString.STRING_2, SIMPLE_DICT);
  }

  @Test
  public void testEncodeSimpleDictionary() {
    try {
      assertEquals(BencodeDictionary.encode(SIMPLE_DICT), SIMPLE_DICT_STRING_ENCODED);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testEncodeLexicalDictionary() {
    try {
      assertEquals(BencodeDictionary.encode(LEXICAL_DICT), LEXICAL_DICT_STRING_ENCODED);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testEncodeComplexDictionary() {
    try {
      assertEquals(BencodeDictionary.encode(COMPLEX_DICT), COMPLEX_DICT_STRING_ENCODED);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testDecodeSimpleDictionary() {
    try {
      assertEquals(BencodeDictionary.decode(SIMPLE_DICT_BYTE_ENCODED), SIMPLE_DICT);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testDecodeLexicalDictionary() {
    try {
      assertEquals(BencodeDictionary.decode(LEXICAL_DICT_BYTE_ENCODED), LEXICAL_DICT);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }
  
  @Test
  public void testDecodeComplexDictionary() {
    try {
      assertEquals(BencodeDictionary.decode(COMPLEX_DICT_BYTE_ENCODED), COMPLEX_DICT);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }
}
