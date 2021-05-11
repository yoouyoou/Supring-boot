package com.example.supringboot.dao;

import java.util.ArrayList;

import com.example.supringboot.domain.Post;

public interface PostDao {
	public ArrayList<Post> getAllPostList();
//	post 제목 기준 검색
	public ArrayList<Post> searchPostList(String title);
	public Post detailPost(int post_id);
	public int updatePost(Post post);
	public int removePost(int post_id);
	public int getPostCount();
	public int insertPost(Post post);
}