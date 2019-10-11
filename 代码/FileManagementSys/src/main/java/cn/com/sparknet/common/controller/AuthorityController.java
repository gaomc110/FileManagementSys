package cn.com.sparknet.common.controller;

import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.AuthorityService;
import cn.com.sparknet.common.util.ParamsUtil;
import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/AuthorityController")
public class AuthorityController extends JsonController {
    @Resource
    AuthorityService authorityService;


    /**
     * 查询当前用户下看到的角色
     */
    @RequestMapping("/getRoleList")
    @ResponseBody
    public ListJson getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ParamsUtil.requestParamMap(request);
        String loginid = request.getParameter("loginid");
        int page = Integer.parseInt(StringUtil.nullToEmpty(map.get("page")));
        int rows = Integer.parseInt(StringUtil.nullToEmpty(map.get("limit")));

        map.put("page", (page - 1) * rows);
        map.put("rows", rows);
        map.put("loginid", loginid);

        return authorityService.getRoleList(map);
    }

}
