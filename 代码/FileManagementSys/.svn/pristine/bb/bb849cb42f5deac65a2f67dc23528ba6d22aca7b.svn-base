package cn.com.sparknet.common.service.impl;


import cn.com.sparknet.common.dao.AddressDao;
import cn.com.sparknet.common.dao.Page;
import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service("addressService")
public class AddressServiceImpl implements AddressService{
    @Resource
    private AddressDao addressDao;

    @Override
    public ListJson getAddressList(Map<String, Object> map) {
        ListJson listJson=new ListJson();
        try{
            Page page=addressDao.AddressList(map);
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
