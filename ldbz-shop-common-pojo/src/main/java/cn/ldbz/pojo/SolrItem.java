package cn.ldbz.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Solr搜索Item POJO
 *
 */

@Data
public class SolrItem implements Serializable {

    private String id;
    private String image;
    private Long price;
    private String sell_point;
    private String title;
    private String category_name;
    private String item_desc;
    @Setter(value = AccessLevel.PRIVATE)
    private String priceView;

    public String getPriceView() {

        DecimalFormat df1 = new DecimalFormat("#.00");
        df1.setGroupingUsed(false);
        String format = df1.format(price / 100);
        return format;
    }


}
