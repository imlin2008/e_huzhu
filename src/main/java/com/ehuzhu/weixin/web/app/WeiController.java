/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ehuzhu.weixin.web.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/weixinapp")
public class WeiController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  showEhuzhu(HttpServletRequest request) {
		String code = request.getParameter("code");
		ModelAndView model = null;
		if(StringUtils.isEmpty(code)){
			  model = new ModelAndView("weixin/notMember");
			  model.addObject("key", "you are not member");
		}else{
			  model = new ModelAndView("weixin/isMember");  
			  model.addObject("key", "是会员");
		}
		return model;
	}
}
