package cn.com.sparknet.common.controller;

import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.ArchivesQueryService;
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
@RequestMapping(value ="/ArchivesQueryController")
public class ArchivesQueryController {

    @Resource
    private ArchivesQueryService archivesQueryService;

    @RequestMapping("getFilelocationList")
    @ResponseBody
    public ListJson getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ParamsUtil.requestParamMap(request);
        int page = Integer.parseInt(StringUtil.nullToEmpty(map.get("page")));
        int rows = Integer.parseInt(StringUtil.nullToEmpty(map.get("limit")));
        map.put("page",  (page-1)*rows);
        map.put("rows", rows);
        return  archivesQueryService.getFilelocationList(map);
    }
}
