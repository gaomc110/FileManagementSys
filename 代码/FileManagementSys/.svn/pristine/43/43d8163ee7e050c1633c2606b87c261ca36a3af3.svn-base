package cn.com.sparknet.common.controller;

import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.DealInformationService;
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
@RequestMapping(value = "/DealInformationController")
public class DealInformationController {

    @Resource
    private DealInformationService dealInformationService;

    @RequestMapping("getDealInformationList")
    @ResponseBody
    public ListJson getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ParamsUtil.requestParamMap(request);
        int page = Integer.parseInt(StringUtil.nullToEmpty(map.get("page")));
        int rows = Integer.parseInt(StringUtil.nullToEmpty(map.get("limit")));
        String dealDate = StringUtil.nullToEmpty(map.get("dealDate"));
        if(!dealDate.equals("")){
            int i = Integer.parseInt(StringUtil.nullToEmpty(map.get("dealDate"))) + 1;
            map.put("StartdealDate",dealDate+"0101000000");
            map.put("EnddealDate",i+"01010000");
        }
        map.put("page", (page - 1) * rows);
        map.put("rows", rows);

        return dealInformationService.getDealInformationList(map);
    }
}
