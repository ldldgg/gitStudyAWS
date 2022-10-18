package com.edu.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.model.MemberDto;

public interface MemberService {

	public List<MemberDto> memberSelectList(int start, int end);

	public MemberDto memberExist(String email, String password);

	public void memberInsertOne(MemberDto memberDto, MultipartHttpServletRequest mulRequest) throws Exception;

	public Map<String, Object> memberSelectOne(int no);

	int memberUpdateOne(MemberDto memberDto, MultipartHttpServletRequest multipartHttpServletRequest, int fileIdx)
		throws Exception;

	public int memberDeleteOne(int no);

	public int memberSelectTotalCount();
}
