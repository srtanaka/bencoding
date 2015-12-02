package srtanaka.bencoding.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import srtanaka.bencoding.BencodeList;

public class TestBencodingList {
  static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");

  // @formatter:off
  static final String STRING_LIST_STRING_ENCODED = "l" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    TestBencodingByteString.STRING_3_STRING_ENCODED + 
    "e";

  static final String INT_LIST_STRING_ENCODED = "l" + 
    TestBencodingInteger.POSITIVE_INT_1_STRING_ENCODED + 
    TestBencodingInteger.ZERO_INT_STRING_ENCODED + 
    TestBencodingInteger.NEGATIVE_INT_1_STRING_ENCODED + 
    "e";

  static final String MIXED_LIST_STRING_ENCODED = "l" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    TestBencodingByteString.STRING_2_STRING_ENCODED + 
    TestBencodingByteString.STRING_3_STRING_ENCODED + 
    TestBencodingInteger.POSITIVE_INT_1_STRING_ENCODED + 
    TestBencodingInteger.ZERO_INT_STRING_ENCODED + 
    TestBencodingInteger.NEGATIVE_INT_1_STRING_ENCODED + 
    "e";

  static final String LIST_LIST_STRING_ENCODED = "l" + 
    STRING_LIST_STRING_ENCODED + 
    "e";

  static final String COMPLEX_LIST_STRING_ENCODED = "l" + 
    TestBencodingByteString.STRING_1_STRING_ENCODED + 
    MIXED_LIST_STRING_ENCODED +
    TestBencodingInteger.POSITIVE_INT_1_STRING_ENCODED + 
    LIST_LIST_STRING_ENCODED + 
    "e";
  // @formatter:on

  static final byte[] STRING_LIST_BYTE_ENCODED = STRING_LIST_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] INT_LIST_BYTE_ENCODED = INT_LIST_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] MIXED_LIST_BYTE_ENCODED = MIXED_LIST_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] LIST_LIST_BYTE_ENCODED = LIST_LIST_STRING_ENCODED.getBytes(US_ASCII_CHARSET);
  static final byte[] COMPLEX_LIST_BYTE_ENCODED = COMPLEX_LIST_STRING_ENCODED.getBytes(US_ASCII_CHARSET);

  private static List<Object> STRING_LIST;
  private static List<Object> INT_LIST;
  private static List<Object> MIXED_LIST;
  private static List<Object> LIST_LIST;
  private static List<Object> COMPLEX_LIST;

  @Before
  public void setup() {
    STRING_LIST = new ArrayList<Object>();
    STRING_LIST.add(TestBencodingByteString.STRING_1);
    STRING_LIST.add(TestBencodingByteString.STRING_2);
    STRING_LIST.add(TestBencodingByteString.STRING_3);

    INT_LIST = new ArrayList<Object>();
    INT_LIST.add(TestBencodingInteger.POSITIVE_INT_1);
    INT_LIST.add(TestBencodingInteger.ZERO_INT);
    INT_LIST.add(TestBencodingInteger.NEGATIVE_INT_1);

    MIXED_LIST = new ArrayList<Object>();
    MIXED_LIST.add(TestBencodingByteString.STRING_1);
    MIXED_LIST.add(TestBencodingByteString.STRING_2);
    MIXED_LIST.add(TestBencodingByteString.STRING_3);
    MIXED_LIST.add(TestBencodingInteger.POSITIVE_INT_1);
    MIXED_LIST.add(TestBencodingInteger.ZERO_INT);
    MIXED_LIST.add(TestBencodingInteger.NEGATIVE_INT_1);

    LIST_LIST = new ArrayList<Object>();
    LIST_LIST.add(STRING_LIST);
    
    COMPLEX_LIST = new ArrayList<Object>();
    COMPLEX_LIST.add(TestBencodingByteString.STRING_1);
    COMPLEX_LIST.add(MIXED_LIST);
    COMPLEX_LIST.add(TestBencodingInteger.POSITIVE_INT_1);
    COMPLEX_LIST.add(LIST_LIST);
    }

  @Test
  public void testEncodeStringList() {
    assertEquals(BencodeList.encode(STRING_LIST), STRING_LIST_STRING_ENCODED);
  }

  @Test
  public void testEncodeIntList() {
    assertEquals(BencodeList.encode(INT_LIST), INT_LIST_STRING_ENCODED);
  }

  @Test
  public void testEncodeMixedList() {
    assertEquals(BencodeList.encode(MIXED_LIST), MIXED_LIST_STRING_ENCODED);
  }

  @Test
  public void testEncodeListList() {
    assertEquals(BencodeList.encode(LIST_LIST), LIST_LIST_STRING_ENCODED);
  }

  @Test
  public void testDecodeStringList() {
    try {
      assertEquals(BencodeList.decode(STRING_LIST_BYTE_ENCODED), STRING_LIST);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testDecodeIntList() {
    try {
      assertEquals(BencodeList.decode(INT_LIST_BYTE_ENCODED), INT_LIST);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }

  @Test
  public void testDecodeListList() {
    try {
      assertEquals(BencodeList.decode(LIST_LIST_BYTE_ENCODED), LIST_LIST);
    } catch ( Exception e ) {
      fail("Throws exception: " + e.getMessage());
    }
  }
}
