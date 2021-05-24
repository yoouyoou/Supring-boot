package com.example.supringboot.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Item;
import com.example.supringboot.service.SupringBootFacade;

@Controller
public class AdminPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);
	@Value("/Account/adminPage")
	String adminPageView;
	
	@Autowired
	SupringBootFacade supringService;
	
	@GetMapping("/admin/adminPage")
	public String adminPage() {
		return adminPageView;
	}
	
	@GetMapping("/admin/itemList")
	public ModelAndView getItemList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(adminPageView);
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		logger.info("user_id : " + user_id);
		
		ArrayList<Item> itemList = supringService.getAdminItemList(user_id);
		
		for(Item item : itemList) {
			System.out.println(item.getTitle());
		}
		
		modelAndView.addObject("itemList", itemList)
			.addObject("selection", 0);
		
		return modelAndView;
	}
	
	@GetMapping("/admin/itemRegisterList")
	public ModelAndView getItemRegisterList(@RequestParam int item_id) {
		logger.info("getItemRegisterList: " + item_id);
		ModelAndView modelAndView = new ModelAndView(adminPageView);
		modelAndView.addObject("selection", 0)
		.addObject("registerSelection", 1);
		return modelAndView;
	}
}
