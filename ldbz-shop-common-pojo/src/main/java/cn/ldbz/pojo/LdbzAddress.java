package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzAddress  implements Serializable {
	
	private static final long serialVersionUID = 6006177874680486651L;

	private Long id;
	
    private Long userId;
    
    private String userAddress;
    
    private Integer isDefault;
    
    private Date created;
    
    private Date updated;
    
}
