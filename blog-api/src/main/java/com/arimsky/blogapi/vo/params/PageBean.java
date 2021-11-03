package com.arimsky.blogapi.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: PageBean
 * @author: aRimsiky
 * @date: 2021/10/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageBean {


	private int page = 1;
	private int pageSize = 10;

	private Long categoryId;

	private Long tagId;

	private String year;

	private String month;

	public String getMonth(){
		if (this.month != null && this.month.length() == 1){
			return "0"+this.month;
		}
		return this.month;
	}
}
