package com.edu.member.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.dao.MemberDao;
import com.edu.member.model.MemberDto;
import com.edu.util.FileUtils;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger log
	= LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	public MemberDao memberDao;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
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
	public void memberInsertOne(MemberDto memberDto, MultipartHttpServletRequest mulRequest) throws Exception {
		// TODO Auto-generated method stub
		
		memberDao.memberInsertOne(memberDto);
		
		Iterator<String> iterator = mulRequest.getFileNames();
		MultipartFile multipartFile = null;
		
		while(iterator.hasNext()) {
			multipartFile = mulRequest.getFile(iterator.next());
			
			if(multipartFile.isEmpty() == false) {
				log.debug("-----------file start-----------");

				log.debug("name : {} ", multipartFile.getName());
				log.debug("fileName : {} ", multipartFile.getOriginalFilename());
				log.debug("size : {} ", multipartFile.getSize());
				
				log.debug("-----------file end-----------");
			}
		}
		
		int parentSeq = memberDto.getNo();
		
		List<Map<String, Object>> list
			= fileUtils.parseInsertFileInfo(parentSeq, mulRequest);
		
		for (int i = 0; i < list.size(); i++) {
			memberDao.insertFile(list.get(i));
		}
	}

	@Override
	public Map<String, Object> memberSelectOne(int no) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MemberDto memberDto = memberDao.memberSelectOne(no);
		resultMap.put("memberDto", memberDto);
		
		List<Map<String, Object>> fileList = memberDao.fileSelectList(no);
		resultMap.put("fileList", fileList);
		
		return resultMap;
	}

	@Override
	public int memberUpdateOne(MemberDto memberDto, MultipartHttpServletRequest multipartHttpServletRequest
			, int fileIdx) throws Exception {
		// TODO Auto-generated method stub
		
		int resultNum = 0;
		
		try {
			resultNum = memberDao.memberUpdateOne(memberDto);
			
			int parentSeq = memberDto.getNo();
			
			Map<String, Object> tempFileMap = memberDao.fileSelectStoredFileName(parentSeq);
			
			List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(parentSeq
					, multipartHttpServletRequest);
			
			if(list.isEmpty() == false) {
				if(tempFileMap != null) {
					memberDao.fileDelete(parentSeq);
					fileUtils.parseUpdateFileInfo(tempFileMap);
				}
				
				for (Map<String, Object> map : list) {
					memberDao.insertFile(map);
				}
			}else if(fileIdx == -1) {
				if(tempFileMap != null) {
					memberDao.fileDelete(parentSeq);
					fileUtils.parseUpdateFileInfo(tempFileMap);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return resultNum;
	}

	@Override
	public int memberDeleteOne(int no) {
		// TODO Auto-generated method stub
		return memberDao.memberDeleteOne(no);
	}


	
}
