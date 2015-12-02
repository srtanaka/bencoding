bencoding
=========
*A simple Java library for Bencoding some things*

What is Bencoding?
------------------
Bencoding is a simple serialization format which can represent integers, strings, lists, and dictionaries.

A quick description of the Bencoding format, courtesy of Wikipedia ([link](https://en.wikipedia.org/wiki/Bencode)):

> Bencode uses ASCII characters as delimiters and digits.

> - An __integer__ is encoded as __i__*\<integer encoded in base ten ASCII>*__e__. Leading zeros are not allowed (although the number zero is still represented as "0"). Negative values are encoded by prefixing the number with a minus sign. The number 42 would thus be encoded as `i42e`, 0 as `i0e`, and -42 as `i-42e`. Negative zero is not permitted.

> - A __byte string__ (a sequence of bytes, not necessarily characters) is encoded as *\<length>*:*\<contents>*. The length is encoded in base 10, like integers, but must be non-negative (zero is allowed); the contents are just the bytes that make up the string. The string "spam" would be encoded as `4:spam`. The specification does not deal with encoding of characters outside the ASCII set; to mitigate this, some BitTorrent applications explicitly communicate the encoding (most commonly UTF-8) in various non-standard ways. This is identical to how netstrings work, except that netstrings additionally append a comma suffix after the byte sequence.

> - A __list of values__ is encoded as __l__*\<contents>*__e__ . The contents consist of the bencoded elements of the list, in order, concatenated. A list consisting of the string "spam" and the number 42 would be encoded as: `l4:spami42ee`. Note the absence of separators between elements.

> - A __dictionary__ is encoded as __d__*\<contents>*__e__. The elements of the dictionary are encoded each key immediately followed by its value. All keys must be byte strings and must appear in lexicographical order. A dictionary that associates the values 42 and "spam" with the keys "foo" and "bar", respectively (in other words, {"bar": "spam", "foo": 42}}), would be encoded as follows: `d3:bar4:spam3:fooi42ee`. (This might be easier to read by inserting some spaces: `d 3:bar 4:spam 3:foo i42e e`.)

> There are no restrictions on what kind of values may be stored in lists and dictionaries; they may (and usually do) contain other lists and dictionaries. This allows for arbitrarily complex data structures to be encoded.

Some examples from the BitTorrent Specification Wikipedia page ([link](https://wiki.theory.org/BitTorrentSpecification#Bencoding)):
> __Bencoded Strings__
> Bencoded strings are encoded as follows: *\<string length encoded in base ten ASCII>*:*\<string data>*, or *key*:*value*
> Note that there is no constant beginning delimiter, and no ending delimiter.

> __Example:__ `4:spam` represents the string "spam"

> __Example:__ `0:` represents the empty string ""

> __Integers__
> Integers are encoded as follows: __i__*\<integer encoded in base ten ASCII>*__e__
> The initial i and trailing e are beginning and ending delimiters. You can have negative numbers such as `i-3e`. Only the significant digits should be used, one cannot pad the Integer with zeroes. such as `i04e`. However, `i0e` is valid.

> __Example:__ `i3e` represents the integer "3"
> - NOTE: The maximum number of bit of this integer is unspecified, but to handle it as a signed 64bit integer is mandatory to handle "large files" aka .torrent for more that 4Gbyte.

> __Lists__
> Lists are encoded as follows: __l__*\<bencoded values>*__e__
> The initial l and trailing e are beginning and ending delimiters. Lists may contain any bencoded type, including integers, strings, dictionaries, and even lists within other lists.

> __Example:__ `l4:spam4:eggse` represents the list of two strings: [ "spam", "eggs" ]

> __Example:__ `le` represents an empty list: []

> __Dictionaries__
> Dictionaries are encoded as follows: __d__*\<bencoded string>\<bencoded element>*__e__
> The initial d and trailing e are the beginning and ending delimiters. Note that the keys must be bencoded strings. The values may be any bencoded type, including integers, strings, lists, and other dictionaries. Keys must be strings and appear in sorted order (sorted as raw strings, not alphanumerics). The strings should be compared using a binary comparison, not a culture-specific "natural" comparison.

> __Example:__ `d3:cow3:moo4:spam4:eggse` represents the dictionary { "cow" => "moo", "spam" => "eggs" }

> __Example:__ `d4:spaml1:a1:bee' represents the dictionary { "spam" => [ "a", "b" ] }

>   __Example:__ `d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee` represents { "publisher" => "bob", "publisher-webpage" => "www.example.com", "publisher.location" => "home" }

> __Example:__ `de` represents an empty dictionary {}

The Task at Hand:
-----------------
Design and implement a Java class library for Bencoding.

The Java class library should be capable of both *encoding* and *decoding* Bencoded values.

Some Limitations...
-------------------
The class library may use the Java SE 7 or 8 libraries, but must not rely on any third-party Java libraries aside from Junit 4 for unit testing.
