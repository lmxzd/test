package com.cy.pj.search.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("response")
public class ResponseHandlerController {
	@RequestMapping("doResponseUI")
	public String doResponseUI() {
		return "response";
	}
	@RequestMapping("doResponseType")
	public String doResponseType() {
		//return "forward:doResponseUI";//转发
		return "redirect:doResponseUI";//重定向
	}
	@RequestMapping("doResponseReply")
	@ResponseBody//写这个注解才能直接响应给浏览器
	public Reply doResponseReply() {
		Reply re = new Reply();
		re.setId(1);
		re.setContent("very good");
		return re;//这个响应转换依赖于jackson的api
	}
	@RequestMapping("doResponseMap")
	@ResponseBody
	public Map<String,Object> doResponseMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("1", new Reply());
		map.put("2", new Reply());
//		ObjectMapper om=new ObjectMapper(); //用jackson的api来将对象或map转换为字符串,但也可以不写,底层会自动转换
//		return om.writeValueAsString(map); //写成这样的话,返回值要为String
		return map;//map也可以自动转换
	}
}
