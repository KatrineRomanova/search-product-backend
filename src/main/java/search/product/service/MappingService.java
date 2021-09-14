package search.product.service;

import org.springframework.stereotype.Service;
import search.product.model.Product;
import search.product.model.bean.ProductBean;


@Service
public class MappingService {

    public Product ofProductBean(Product product, ProductBean productBean){
        if(product != null && productBean != null) {
            product.setProductName(productBean.getProductName());
            product.setVendorCode(productBean.getVendorCode());
            product.setAmountRemain(productBean.getAmountRemain());
            product.setPrice(productBean.getPrice());
            product.setPhoto(productBean.getPhoto());
            product.setTypeRemain(productBean.getTypeRemain());
        }
        return product;
    }

}
