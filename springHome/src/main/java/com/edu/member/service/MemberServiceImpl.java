package com.edu.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.member.dao.MemberDao;
import com.edu.member.model.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	public MemberDao memberDao;
	
	@Override
	public List<MemberDto> memberSelectList(int start, int end) {
		// TODO Auto-generated method stub
		
		return memberDao.memberSelectList(start, end);
	}
	
	@Override
	public int memberSelectTotalCount() {
		// TODO Auto-generated method stub
		return memberDao.memberSelectTotalCount();
	}
	

	@Override
	public MemberDto memberExist(String email, String password) {
		// TODO Auto-generated method stub
		return memberDao.memberExist(email, password);
	}

	@Override
	public int memberInsertOne(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberDao.memberInsertOne(memberDto);
	}

	@Override
	public MemberDto memberSelectOne(int no) {
		// TODO Auto-generated method stub
		return memberDao.memberSelectOne(no);
	}

	@Override
	public int memberUpdateOne(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberDao.memberUpdateOne(memberDto);
	}

	@Override
	public int memberDeleteOne(int no) {
		// TODO Auto-generated method stub
		return memberDao.memberDeleteOne(no);
	}


	
}
