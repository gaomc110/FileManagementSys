package cn.com.sparknet.common.service;

import cn.com.sparknet.common.json.ListJson;

import java.util.Map;

public interface OperationalService {

    /**
     * 操作信息列表查询
     * @param
     * @return
     */
    ListJson getOperationalList(Map<String, Object> map);

}
