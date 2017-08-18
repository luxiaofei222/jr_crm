package com.jingren.jing.school.dao.letter;

import com.jingren.jing.school.back.letter.Letter;

public interface LetterMapper {

	/**
	* @Title: LetterMapper.java 
	* @Package com.jingren.jing.school.dao.letter 
	* @Description: TODO 保存私信
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月30日 下午3:23:18 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveLetter(Letter letter);
}
