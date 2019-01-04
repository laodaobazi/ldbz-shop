package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzSheetAssign implements Serializable {

	private static final long serialVersionUID = 1059327528461366505L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private Integer sortOrder ;
    
    private Long itemId;
    
    private String itemName;

    private String itemCode;
    
    private Long sheetId;
    
    private String sheetName;
    
}