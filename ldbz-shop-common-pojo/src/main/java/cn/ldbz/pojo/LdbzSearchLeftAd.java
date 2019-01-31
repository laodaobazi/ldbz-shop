package cn.ldbz.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LdbzSearchLeftAd implements Serializable {

	private static final long serialVersionUID = -82594835288081220L;

	private Long id;
    
    private Date created;

    private String creator;
    
    private String creatorName;
    
    private Date updated;
    
    private String updator;
    
    private String updatorName;
    
    private String alt;

    private String href;

    private String src;

    private Integer status;

    private Integer sortOrder;

}