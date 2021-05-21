package com.example.supringboot.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;

import org.springframework.ui.Model;

@Controller
public class GetAllPostController {
	private static final Logger logger = LoggerFactory.getLogger(GetAllPostController.class);
	
	@Value("post/postList")
	private String postListView;
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/post/getPostList")
	public String openPostList(Model model) {
		ArrayList<Post> postList = postService.getPostList();
		model.addAttribute("postList", postList);

		return postListView;
	}
}