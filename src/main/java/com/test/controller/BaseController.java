package com.test.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * action模板，实现了了baseaction的action类可以在无参数的情况下进行一些静态页面跳转
 * 其他Controller也可继承该类
 * @author wgt
 *
 */
@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Resource
	ServletContext application;

	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	//方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
	@RequestMapping("/goURL/{folder}/{file}")
	public String goURL(@PathVariable String folder,@PathVariable String file) {
		//System.out.println("goURL.folder|file===" + folder+"/"+file);
		logger.info("goURL.folder|file===" + folder+"/"+file);
		return folder+"/"+file;
	}

	/**
	 *例：如在页面下使用http://localhost:8080/项目名/base/goURL/test,即可跳转到webApp下的test页面
	 */
	@RequestMapping("/goURL/{file}")
	public String goURL(@PathVariable String file){
		//System.out.println("goURL.folder|file===" + "/"+file);
		logger.info("goURL.folder|file===" + "/"+file);
		return file;
	}
}
