package srtanaka.bencoding.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestBencodingInteger.class,
  TestBencodingByteString.class,
  TestBencodingList.class,
  TestBencodingDictionary.class
})
public class BencodingTestSuite {}
