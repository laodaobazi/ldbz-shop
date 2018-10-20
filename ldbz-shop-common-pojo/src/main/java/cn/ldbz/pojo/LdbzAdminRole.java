package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzAdminRole implements Serializable {

	private static final long serialVersionUID = 7573629775973699686L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private Integer sortOrder ;
    
    private String roleName ;
    
}