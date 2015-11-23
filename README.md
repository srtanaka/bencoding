# bencoding
*A simple Java library for Bencoding some things*

The Task at Hand:
-----------------
Design and implement a Java class library for Bencoding.

What is Bencoding?
------------------
Bencoding is a simple serialization format which can represent integers, strings, lists, and dictionaries.
 
A quick description of the Bencoding format, courtesy of Wikipedia ([link](https://en.wikipedia.org/wiki/Bencode)):
 
> Bencode uses ASCII characters as delimiters and digits.
 
> - An __integer__ is encoded as __i__*\<integer encoded in base ten ASCII>*__e__. Leading zeros are not allowed (although the number zero is still represented as "0"). Negative values are encoded by prefixing the number with a minus sign. The number 42 would thus be encoded as ```i42e```, 0 as ```i0e```, and -42 as ```i-42e```. Negative zero is not permitted.

> - A __byte string__ (a sequence of bytes, not necessarily characters) is encoded as *\<length>*:*\<contents>*. The length is encoded in base 10, like integers, but must be non-negative (zero is allowed); the contents are just the bytes that make up the string. The string "spam" would be encoded as ```4:spam```. The specification does not deal with encoding of characters outside the ASCII set; to mitigate this, some BitTorrent applications explicitly communicate the encoding (most commonly UTF-8) in various non-standard ways. This is identical to how netstrings work, except that netstrings additionally append a comma suffix after the byte sequence.

> - A __list of values__ is encoded as __l__*\<contents>*__e__ . The contents consist of the bencoded elements of the list, in order, concatenated. A list consisting of the string "spam" and the number 42 would be encoded as: ```l4:spami42ee```. Note the absence of separators between elements.

> - A __dictionary__ is encoded as __d__*\<contents>*__e__. The elements of the dictionary are encoded each key immediately followed by its value. All keys must be byte strings and must appear in lexicographical order. A dictionary that associates the values 42 and "spam" with the keys "foo" and "bar", respectively (in other words, {"bar": "spam", "foo": 42}}), would be encoded as follows: ```d3:bar4:spam3:fooi42ee```. (This might be easier to read by inserting some spaces: ```d 3:bar 4:spam 3:foo i42e e```.)

> There are no restrictions on what kind of values may be stored in lists and dictionaries; they may (and usually do) contain other lists and dictionaries. This allows for arbitrarily complex data structures to be encoded.
 
Some Limitations...
-------------------
The Java class library should be capable of both encoding and decoding Bencoded values.
 
The class library may use the Java SE 7 or 8 libraries, but must not rely on any third-party Java libraries aside from Junit 4 for unit testing.
