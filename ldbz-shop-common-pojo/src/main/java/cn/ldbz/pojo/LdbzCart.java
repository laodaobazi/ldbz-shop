package cn.ldbz.pojo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class LdbzCart  implements Serializable {
	
	private static final long serialVersionUID = 6006177874680486651L;
	
	private Long code;
    private String title;
    private String image;
    private Long price;
    private Integer count;
    
    @Setter(value = AccessLevel.PRIVATE)
    private Long total;

    public Long getTotal() {
        return price * count;
    }

}
