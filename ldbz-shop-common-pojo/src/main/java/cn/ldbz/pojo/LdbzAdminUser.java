package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzAdminUser implements Serializable {

	private static final long serialVersionUID = 1059327528461366505L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private Integer sortOrder ;
    
    private String userName ;
    
    private String userPwd ;
    
    private String realName ;
    
    private String email ;
    
    private String telephone ;
}