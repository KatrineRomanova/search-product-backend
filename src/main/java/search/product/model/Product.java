package search.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "price")
    private Long price;

    @Column(name = "photo_id")
    private Long photoId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private Photo photo;

    public Product(ProductBean productBean){
        this.productName = productBean.getProductName();
        this.vendorCode = productBean.getVendorCode();
        this.amountRemain = productBean.getAmountRemain();
        this.price = productBean.getPrice();
        this.photoId = productBean.getPhotoId();
        this.typeRemain = productBean.getTypeRemain();
    }

}
