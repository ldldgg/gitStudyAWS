package com.edu.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.model.MemberDto;
import com.edu.member.service.MemberService;
import com.edu.util.Paging;

// 어노테이션 드리븐
@Controller
public class MemberController {

	private static final Logger logger 
		= LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	// 로그인화면
	@RequestMapping(value = "/auth/login.do", method = RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		logger.info("Welcome MemberController login! ");
		
		
		return "auth/LoginForm";
	}
	
	// 로그인
	@RequestMapping(value = "/auth/loginCtr.do", method = RequestMethod.POST)
	public String loginCtr(String email, String password
			, HttpSession session, Model model) {
		logger.info("Welcome MemberController loginCtr! " + email 
			+ ", " + password);
		
		MemberDto memberDto = memberService.memberExist(email, password);
		
		String url = "";
		if(memberDto != null) {
			session.setAttribute("_memberDto_", memberDto);
			
			url = "redirect:/member/list.do";
		}else {
			url = "/auth/LoginFail";
		}
		
		return url;
	}
	
	// 로그아웃
	@RequestMapping(value = "/auth/logout.do", method= RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		logger.info("Welcome MemberController logout! ");
		
		session.invalidate();
		
		return "redirect:/auth/login.do";
	}
	
	
	// 회원목록 화면
	@RequestMapping(value = "/member/list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String memberList(@RequestParam(defaultValue = "1")int curPage, Model model) {
		
		logger.info("Welcome MemberController memberList! curPage: {}", curPage);
		
		int totalCount = memberService.memberSelectTotalCount();
		
		Paging memberPaging = new Paging(totalCount, curPage);
		int start = memberPaging.getPageBegin();
		int end = memberPaging.getPageEnd();
		
		List<MemberDto> memberList = memberService.memberSelectList(start, end);
		
		Map<String, Object> pagingMap = new HashMap<String, Object>();
		pagingMap.put("totalCount", totalCount);
		pagingMap.put("memberPaging", memberPaging);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingMap", pagingMap);
		
		return "member/MemberListView";
	}
	
	// 회원상세 화면
	@RequestMapping(value = "/member/one.do")
	public String memberOne(int no, Model model) {
		logger.info("Welcome memberOne enter! - {}" + no);
		
		Map<String, Object> map = memberService.memberSelectOne(no);
		
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		
		List<Map<String, Object>> fileList
			= (List<Map<String, Object>>) map.get("fileList");
		
		model.addAttribute("memberDto", memberDto);
		model.addAttribute("fileList", fileList);
		
		return "member/MemberOneView";
	}
	
	// 회원추가 화면
	@RequestMapping(value = "/member/add.do", method = RequestMethod.GET)
	public String memberAdd(Model model) {
		logger.debug("Welcome MemberController memberAdd! ");
		
		
		return "member/MemberForm";
	}
	
	// 회원추가
	@RequestMapping(value = "/member/addCtr.do", method = RequestMethod.POST)
	public String memberAdd(MemberDto memberDto,
			MultipartHttpServletRequest mulRequest ,Model model) {
		logger.trace("Welcome MemberController memberAdd! " + memberDto);
		
		try {
			memberService.memberInsertOne(memberDto, mulRequest);
		} catch (Exception e) {
			System.out.println("파일문제 발생");
			e.printStackTrace();
		}
		
		
		return "redirect:/member/list.do";
	}
	
	
	// 회원수정 화면
	@RequestMapping(value = "/member/update.do", method = RequestMethod.GET)
	public String memberUpdate(int no, Model model) {
		logger.info("Welcome MemberController memberUpdate! " + no);
		
		Map<String, Object> map = memberService.memberSelectOne(no);
		
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		List<Map<String, Object>> fileList = (List<Map<String, Object>>) map.get("fileList");
		
		model.addAttribute("memberDto", memberDto);
		model.addAttribute("fileList", fileList);
		
		return "member/MemberUpdateForm";
	}
	
	
	// 회원수정
	@RequestMapping(value = "/member/updateCtr.do", method = RequestMethod.POST)
	public String memberUpdateCtr(MemberDto memberDto,
			@RequestParam(value = "fileIdx", defaultValue = "-1") int fileIdx,
			MultipartHttpServletRequest multipartHttpServletRequest,
			Model model, HttpSession session) {
		logger.info("Welcome MemberController memberUpdateCtr! {} :: {}", memberDto, fileIdx);
		
		int resultNum = 0;
		
		try {
			resultNum = memberService.memberUpdateOne(memberDto, multipartHttpServletRequest, fileIdx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 데이터베이스에서 회원정보가 수정이 됬는지 여부
		if(resultNum > 0) {
			MemberDto memberDtoSession = (MemberDto) session.getAttribute("_memberDto_");
			
			if(memberDtoSession != null) {
				if(memberDtoSession.getNo() == memberDto.getNo()) {
					session.setAttribute("_memberDto_", memberDto);
				}
			}
		}
		
		model.addAttribute("memberDto", memberDto);
		
		return "common/sucessPage";
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/member/deleteCtr.do", method = RequestMethod.GET)
	public String memberDelete(int no, Model model, HttpSession session) {
		logger.info("Welcome MemberController memberDelete! " + no);
		
		int result = memberService.memberDeleteOne(no);
		
		if(result > 0) {
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
		
			if(memberDto != null) {
				if(memberDto.getNo() == no) {
					session.invalidate();
					
					return "redirect:/auth/login.do";
				}
			}
		}
		
		return "redirect:/member/list.do";
	}
	

}
