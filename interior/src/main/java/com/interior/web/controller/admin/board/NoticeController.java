package com.interior.web.controller.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice")
public class NoticeController {

	@RequestMapping("/list")
	public String list() {
		return "";
	}
	@RequestMapping("/reg")
	@ResponseBody
	public String reg(String title, String content, String category, String[] foods, String food) {

		for(String item : foods) {
			System.out.println("foods : " + item);
		}
		System.out.println("radio : " + food );

		return String.format("title:%s<br>content:%s<br>category:%s<br>",title,content, category);
	}
	@RequestMapping("/edit")
	public String edit() {
		return "?";
	}
	@RequestMapping("/delete")
	public String delete() {
		return "?";
	}
}
