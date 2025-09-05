/* TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:

Solution() Initializes the object of the system.
String encode(String longUrl) Returns a tiny URL for the given longUrl.
String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4420/
*/

package systemDesign;

import java.util.HashMap;
import java.util.Map;

public class Codec {
  private static final String BASE = "http://tinyurl.com/";
  private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int BASE62 = ALPHABET.length();

  private long counter = 1;
  private final Map<String, String> codeToUrl = new HashMap<>();
  private final Map<String, String> urlToCode = new HashMap<>();

  public String encode(String longUrl) {
    if (urlToCode.containsKey(longUrl)) {
      return BASE +  urlToCode.get(longUrl);
    }

    String code = toBase62(counter++);
    codeToUrl.put(code, longUrl);
    urlToCode.put(longUrl, code);

    return BASE + code;
  }

  public String decode(String shortUrl) {
    int idx = shortUrl.lastIndexOf('/');
    String code = (idx >= 0) ? shortUrl.substring(idx + 1) : shortUrl;
    return codeToUrl.get(code);
  }
  
  private String toBase62(long n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      int rem = (int)(n % BASE62);
      sb.append(ALPHABET.charAt(rem));
      n /=  BASE62;
    }
    return sb.reverse().toString();
  }
}
