package com.cy.pj.search.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("search")
@Controller
public class SearchController {
	@RequestMapping("doSearchUI")
	public String doSearchUI() {
		return "search";
		//1.结果会返回给DispatcherServlet
		//2.DispatcherServlet调用视图解析器对view进行解析
		//3.视图解析器将解析结果/WEB-INF/pages/search.html返回给DispatcherServlet
		//4.前端控制器将页面响应到客户端面
	}
	@RequestMapping("doSearch")
	@ResponseBody
	public Object doSearch(String key) {
		Map<String,Object> map=new HashMap<>();
		map.put(key, "hello cgb1907");
		return map;
	}
}
