package cn.ldbz.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LdbzOrderItem implements Serializable {

	private Long id ;
	
	private String title ;
	
	private Float price ;
	
	private String image ;
	
	private Integer num;
	
	private Long orderCode ;
	
}
