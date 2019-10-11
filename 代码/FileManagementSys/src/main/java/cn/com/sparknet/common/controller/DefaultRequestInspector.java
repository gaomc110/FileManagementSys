package cn.com.sparknet.common.controller;

import org.apache.commons.codec.digest.DigestUtils;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 请求检查接口默认实现。
 *
 * @author hankai
 * @version 1.0.0
 * @since Oct 31, 2016 10:30:05 PM
 *
 */

public  class DefaultRequestInspector {

  public static String signRequestParameters(Map<String, ?> parameters) {
    return signRequestParameters(parameters, "8dffab6aafc8ebc974bd82364ef9516b");
  }

  public static  String signRequestParameters(Map<String, ?> parameters, String sk) {
    final StringBuffer toBeSigned = new StringBuffer();
    final List<String> paramNames = new ArrayList<String>();
    paramNames.addAll(parameters.keySet());
    Collections.sort(paramNames);
    for (final String param : paramNames) {
      if (param.equalsIgnoreCase("access_token")
          || param.equalsIgnoreCase("sign")) {
        continue;
      }
      final Object objValue = parameters.get(param);
      String value = null;
      if (objValue instanceof String[]) {
        final String[] array = (String[]) objValue;
        if (array.length > 0) {
          value = array[0];
        }
      } else {
        value = objValue.toString();
      }
      if (value != null) {
        try {
          value = URLEncoder.encode(value, "UTF-8");
        } catch (final UnsupportedEncodingException ex) {
          //logger.warn(String.format("Failed to url encode request parameter: %s = ", param, value));
        }
        toBeSigned.append(param + "=" + value + "&");
      }
    }
    if (toBeSigned.length() > 0) {
      toBeSigned.deleteCharAt(toBeSigned.length() - 1);
    }
    toBeSigned.append(sk);
    final String expSign =DigestUtils.sha1Hex(toBeSigned.toString());
    return expSign;
  }

}
