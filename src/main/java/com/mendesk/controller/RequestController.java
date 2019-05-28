package com.mendesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendesk.dao.UserDao;
import com.mendesk.entity.Comment;
import com.mendesk.entity.Request;
import com.mendesk.entity.User;
import com.mendesk.repository.CommentRepo;
import com.mendesk.repository.RequestRepo;
import com.mendesk.util.AuthSystem;
import com.mendesk.util.JsonResponse;

@RequestMapping("/requests")
@Controller
public class RequestController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RequestRepo requestRepo;

	@Autowired
	CommentRepo commentRepo;

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, ModelMap model) {
		Request request = requestRepo.getOne(id);
		List<Comment> comments = request.getComments();
		model.addAttribute("appRequest", request);
		model.addAttribute("requestComments", comments);
		return "requests/show";
	}

	@PostMapping("/{id}/comments")
	public @ResponseBody JsonResponse createComment(@RequestParam("content") String content,
			@PathVariable("id") Integer id, Authentication auth,  ModelMap model) {

		Request request = requestRepo.getOne(id);
		
		User user = userDao.findByUserName(AuthSystem.currentUsername());

		commentRepo.save(new Comment(content, request, user));

		JsonResponse jsonResponse = new JsonResponse("OK", 200);
		return jsonResponse;

	}
}
