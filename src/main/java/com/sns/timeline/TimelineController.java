package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.Comment;
import com.sns.timeline.model.ContentView;
@RequestMapping("/timeline")
@Controller
public class TimelineController {

	
	@Autowired
	private PostBO postBO;
	@RequestMapping("/timeline_list_view")
	public String timelineListView(Model model) {
		
		// 하나의 카드 => ContentView 객체(뷰용 객체)
		
		List<ContentView> contentList = new ArrayList<>();
		
		List<Post> postList = postBO.getPostList(); // post
		
		List<Comment> commentlist ; // comment
		
		model.addAttribute("viewName", "timeline/timeline_list");
		model.addAttribute("postList", postList);
		model.addAttribute("contentList", contentList);
		return "template/layout";
	}
	
}
