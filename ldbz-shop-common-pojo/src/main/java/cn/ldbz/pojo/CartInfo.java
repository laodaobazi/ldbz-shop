package cn.ldbz.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * 购物车 pojo
 *
 */

@Data
public class CartInfo implements Serializable{

    private Long id;
    private String name;
    private String imageUrl;
    private String colour;
    private String size;
    private Long price;
    private Long weight;
    private Integer num;
    @Setter(value = AccessLevel.PRIVATE)
    private Long sum;

    public Long getSum() {
        return price * num;
    }
}
