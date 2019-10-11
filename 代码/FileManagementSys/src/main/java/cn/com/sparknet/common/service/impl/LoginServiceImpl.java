package cn.com.sparknet.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.dao.LoginDao;
import cn.com.sparknet.common.service.LoginService;
import cn.com.sparknet.common.util.StringUtil;
/**
 * 用户登录
 * @author GAOMC
 *
 */
@Service ("loginService")
public class LoginServiceImpl implements LoginService {

    private static Logger log = Logger.getLogger(LoginServiceImpl.class);

    @Resource
    LoginDao  loginDao;

    
    public int userLogincout(Map<String, Object> map) {
		try{
			return loginDao.contuserlogo(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
		
	}

    /**
     * 用户登录 
     * @param map
     * @return
     */
    
    public User businessLogin(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        User user = null;
        try {
            user = new User();
            list = loginDao.businessLogin(map);
            if(list.size()>0){
                for (Map<String, Object> userMap : list) {
                    user.setUSER_ID(StringUtil.nullToEmpty(userMap.get("U_ID").toString()));
                    user.setUSER_NAME(StringUtil.nullToEmpty(userMap.get("U_NAME").toString()));
                    user.setUSER_PWD(StringUtil.nullToEmpty(userMap.get("U_PWD").toString()));
                    user.setUSER_START(StringUtil.nullToEmpty(userMap.get("STATE").toString()));
                } 
            }
           
        } catch ( Exception e ) {
            log.error(e.getMessage(), e);
        }
        return user;
    }
    
    


	
}
