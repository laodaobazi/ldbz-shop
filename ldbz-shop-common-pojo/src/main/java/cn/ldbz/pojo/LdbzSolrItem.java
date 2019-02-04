package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzSolrItem implements Serializable {

	private static final long serialVersionUID = 5162189266133983206L;

	private Long id;

    private Date created;
    
    private String title ;
    
    private String detail ;

    private Long code ;
    
    private Float price ;
    
    private Float oldPrice ;
    
    private String image ;
    
    private long category ;
    
    private long categoryFid ;
    
    private String categoryName ;
    
    private Float priceFrom ;
    
    private Float priceTo ;
    
    private String field ;

    private String order ;

}
