package cn.com.sparknet.common.service.impl;


import cn.com.sparknet.common.dao.OperationalDao;
import cn.com.sparknet.common.dao.Page;
import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.OperationalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("operationalService")
public class OperationalServiceImpl implements OperationalService{
    @Resource
    private OperationalDao operationalDao;

    @Override
    public ListJson getOperationalList(Map<String, Object> map) {
        ListJson listJson=new ListJson();
        try{
            Page page=operationalDao.OperationalList(map);
            if(page.getCount()>0){
                listJson.setData(page.getList());
                listJson.setCount(page.getCount());
                listJson.setCode(0);
                listJson.setMsg("");
            }else{
                listJson.setData(page.getList());
                listJson.setCount(page.getCount());
                listJson.setCode(1);
                listJson.setMsg("暂无数据可查");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return listJson;
    }
}
