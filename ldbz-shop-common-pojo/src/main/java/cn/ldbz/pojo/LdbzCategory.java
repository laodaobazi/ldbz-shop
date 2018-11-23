package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzCategory implements Serializable {

	private static final long serialVersionUID = 5162189266133983206L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private Integer sortOrder ;
    
    private String categoryName ;
    
    private String categoryLevel ;
    
    private Long fid ;
    
    private Integer status ;
    
}
