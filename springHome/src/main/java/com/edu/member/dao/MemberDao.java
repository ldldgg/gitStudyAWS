package com.edu.member.dao;

import java.util.List;
import java.util.Map;

import com.edu.member.model.MemberDto;

public interface MemberDao {

	public List<MemberDto> memberSelectList(int start, int end);

	public MemberDto memberExist(String email, String password);

	public int memberInsertOne(MemberDto memberDto);

	public MemberDto memberSelectOne(int no);

	int memberUpdateOne(MemberDto memberDto);

	public int memberDeleteOne(int no);

	public int memberSelectTotalCount();

	public void insertFile(Map<String, Object> map);

	public List<Map<String, Object>> fileSelectList(int no);

	public Map<String, Object> fileSelectStoredFileName(int parentSeq);

	public void fileDelete(int parentSeq);
}
