package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.bo.ContentViewBO;
import com.sns.timeline.model.ContentView;
@RequestMapping("/timeline")
@Controller
public class TimelineController {

	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private ContentViewBO contentViewBO;
	@RequestMapping("/timeline_list_view")
	public String timelineListView(Model model,
			
			HttpServletRequest request) {
		
		// 하나의 카드 => ContentView 객체(뷰용 객체)
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		List<ContentView> contentViewList = contentViewBO.generateContentViewList(userId);
		
		// List<Post> postList = postBO.getPostList(); // post
		
		
		model.addAttribute("viewName", "timeline/timeline_list");
		// model.addAttribute("postList", postList);
		model.addAttribute("contentViewList", contentViewList);
		return "template/layout";
	
	
	}
	
}