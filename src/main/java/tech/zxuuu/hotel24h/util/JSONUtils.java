package tech.zxuuu.hotel24h.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JSONUtils {

  public static String buildJSON(Map<String, Object> map) {
    JSONObject jsonObject = new JSONObject();
    for (String key : map.keySet()) {
      jsonObject.put(key, map.get(key));
    }
    return jsonObject.toJSONString();
  }
}
