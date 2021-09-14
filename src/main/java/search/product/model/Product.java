package search.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import search.product.model.bean.ProductBean;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "product_id")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "vendor_code")
    private Long vendorCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount_remain")
    private Long amountRemain;

    @Column(name = "type_remain")
    private String typeRemain;

    @Column(name = "photo")
    private String photo;

    @Column(name = "price")
    private Long price;

    public Product(ProductBean productBean){
        this.productName = productBean.getProductName();
        this.vendorCode = productBean.getVendorCode();
        this.amountRemain = productBean.getAmountRemain();
        this.price = productBean.getPrice();
        this.photo = productBean.getPhoto();
        this.typeRemain = productBean.getTypeRemain();
    }

}
