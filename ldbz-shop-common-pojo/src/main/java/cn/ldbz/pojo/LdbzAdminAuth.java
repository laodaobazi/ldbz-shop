package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzAdminAuth implements Serializable {

	private static final long serialVersionUID = -5944223170055706401L;

	private Long roleId ;
	
	private String roleName ;
    
    private Long menuId ;
    
    private String menuName ;

    private Long userId ;
    
    private String userName ;
    
    private String realName ;
    
    private Date created;

    private String creator;
    
}