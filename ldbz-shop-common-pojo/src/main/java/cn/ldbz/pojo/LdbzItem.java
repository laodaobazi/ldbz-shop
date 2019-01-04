package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzItem  implements Serializable {

	private static final long serialVersionUID = 5162189266133983206L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private Integer sortOrder ;
    
    private String title ;

    private String code ;
    
    private String sellPoint ;
    
    private Long price ;
    
    private int num ;
    
    private String barcode ;
    
    private String image ;
    
    private Long cid ;
    
    private Integer status ;
    
    private Integer weight ;
    
    private String colour ;
    
    private String size ;

}
