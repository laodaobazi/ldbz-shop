package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzItemComment   implements Serializable {
	
	private static final long serialVersionUID = 8671274900768514875L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Long itemCode;
    
    private String comment;

    private int star ;
    
    private int status ;
    
}
