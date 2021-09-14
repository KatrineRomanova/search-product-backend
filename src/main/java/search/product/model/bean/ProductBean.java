package search.product.model.bean;

import lombok.Data;

@Data
public class ProductBean {

    private Long id;

    private Long vendorCode;

    private String productName;

    private Long amountRemain;

    private String typeRemain;

    private String photo;

    private Long price;
}
