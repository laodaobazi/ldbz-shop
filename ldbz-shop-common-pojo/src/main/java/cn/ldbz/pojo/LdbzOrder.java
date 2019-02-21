package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzOrder implements Serializable {

	private Long id ;
	
	/**
	 * 订单编号
	 */
	private Long orderCode ;
	
	private Date created ;
	
	private Long creator ;
	
	private Integer status ;
	
	private String address ;
	
	private Float totalPrice ;
	
}
