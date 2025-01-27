package com.example.supringboot.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
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
	public ModelAndView getItemList(@RequestParam(required=false) Integer deleteException, HttpServletRequest request) {

		logger.info("deleteException : " + deleteException);
		
		ModelAndView modelAndView = new ModelAndView(adminPageView);
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		logger.info("user_id : " + user_id);
		
		HashMap<String, ArrayList<Item>> hashmap = supringService.getAdminItemList(user_id);
			
		modelAndView.addObject("ongoingItemList", hashmap.get("ongoingItemList"))
			.addObject("expiredItemList", hashmap.get("expiredItemList"))
			.addObject("selection", 0);
		if(deleteException != null) {
			modelAndView.addObject("deleteException", deleteException);
		}
		
		return modelAndView;
	}
	
//	Modal로 띄워서 표현할 것 이기 때문에(ajax 통신) json 형태로 response하도록 하였음
	@GetMapping("/admin/itemRegisterList/{item_id}")
	@ResponseBody
	public List<Order_reg> getItemRegisterList(@PathVariable int item_id) {
		logger.info("getItemRegisterList: " + item_id);
		
		ArrayList<Order_reg> registerList = supringService.getAdminRegisterList(item_id);
		
		return registerList;
	}
}
