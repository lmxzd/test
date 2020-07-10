package com.cy.pj.search.controller;

import java.util.Date;

import javax.xml.ws.RequestWrapper;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("request")
@Controller
public class RequestHandleController {
	/**
	 * 请求url定义分为两个,一个是普通URL的定义实现,一个是REST风格的URL的定义实现
	 * 普通URL定义实现(多个url可以对应同一个资源)*/
	  @RequestMapping(value={"doHandleUrl","doWelcomeUI"})
	  public String doHandleUrl() {
		 return "welcome"; 
	  }
	  /**
	   * REST风格的URL，其url格式为{a}/{b}/{c}
	   * 假如希望方法参数获取url中{}表达式内部的值可以使用@PathVariable对参数进行修饰
	   * 属于一种软件架构设计风格,可以少些很多代码
	   * */
	  @RequestMapping("{module}/{page}")
	  public String doMoudleUrl(
			  @PathVariable String module,
			  @PathVariable String page) {
		 return module+"/"+page;
	  }
	  @RequestMapping(value="doRequestType",method=RequestMethod.GET)
	  //@GetMapping("doRequestType")上面的可以简写为这两种形式
	  //@PostMapping("doRequestType")
	  @ResponseBody//这个注解的意思?            不加这个还是去找相应的html/jsp
	  public String doMethodType() {
		  return "request type";
	  }
	  @GetMapping("param")
	  @ResponseBody
	  public String doRequestParam(
			 Reply rw,//pojo
			 @RequestParam(required=false) String msg,//这个设置为false意思这个参数可写可不写,true是表示必须写
			 @DateTimeFormat(pattern="yyyy/MM/dd")Date begin) {
		  return "request parameter handle msg="+msg+",begin="+begin+",rw="+rw.toString();
	  }
}
