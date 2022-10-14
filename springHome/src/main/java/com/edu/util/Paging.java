package com.edu.util;

import java.io.Serializable;

public class Paging implements Serializable {


	private static final int PAGE_SCALE = 5;
	private static final int BLOCK_SCALE = 3;
	
	private int curPage;
	
	private int totPage; 	// 전체 페이지
	private int totBlock;	// 전체 블록
	private int curBlock;	// 현재 블록
	
	private int prevBlock; 	// 이전 블록
	private int nextBlock; 	// 다음 블록
	
	private int pageBegin; 	// #{start}
	private int pageEnd; 	// #{end}
	
	private int blockBegin; // 블록 시작
	private int blockEnd;	// 블록 끝

	//count 전체게시글 수 curPage 현재 블록 위치
	public Paging(int count, int curPage) {
		
		curBlock = 1;
		this.curPage = curPage;
		
		setTotPage(count);
		setPageRange();
		setTotBlock();
		setBlockRange();
	}
	
	public void setBlockRange() {
		curBlock = (int)Math.ceil((double)curPage / BLOCK_SCALE);
		
		blockBegin = (curBlock - 1) * BLOCK_SCALE + 1;
		
		blockEnd = blockBegin + BLOCK_SCALE - 1;
		
		if(blockEnd > totPage) {
			blockEnd = totPage;
		}
		
		prevBlock = (curPage == 1) ? 1 : (curBlock - 1) * BLOCK_SCALE;
		
		nextBlock = curBlock >= totBlock ? (curBlock * BLOCK_SCALE) : (curBlock * BLOCK_SCALE) + 1;
		
		if(prevBlock <= 0) {
			prevBlock = 1;
		}
		
		if(nextBlock >= totPage) {
			nextBlock = totPage;
		}
	}
	
	public void setPageRange() {
		pageBegin = (curPage -1) * PAGE_SCALE + 1;
		
		pageEnd = pageBegin + PAGE_SCALE - 1;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {
		totPage = (int)Math.ceil((double)count / PAGE_SCALE);
	}

	public int getTotBlock() {
		return totBlock;
	}

	public void setTotBlock() {
		totBlock = (int)Math.ceil((double)totPage / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	
	
}
