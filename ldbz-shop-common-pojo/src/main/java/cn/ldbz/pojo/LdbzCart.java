package cn.ldbz.pojo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class LdbzCart  implements Serializable {
	
	private static final long serialVersionUID = 6006177874680486651L;

	private Long item_code;
    private String item_title;
    private String item_image;
    private Float item_price;
    private Integer count;
    
    @Setter(value = AccessLevel.PRIVATE)
    private Float total;

    public Float getTotal() {
        return item_price * count;
    }

}
