package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.model.Comment;
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
	public String timelineListView(Model model) {
		
		// 하나의 카드 => ContentView 객체(뷰용 객체)
		
		List<ContentView> contentList = contentViewBO.getContentList();
		
		List<Post> postList = postBO.getPostList(); // post
		
		
		model.addAttribute("viewName", "timeline/timeline_list");
		model.addAttribute("postList", postList);
		model.addAttribute("contentList", contentList);
		return "template/layout";
	}
	
}
