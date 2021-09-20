package search.product.service;

import lombok.RequiredArgsConstructor;
import search.product.model.Product;
import search.product.model.bean.ProductBean;
import org.springframework.stereotype.Service;
import search.product.repo.ProductRepository;

import java.util.Collection;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MappingService mappingService;

    public Product getProductById(Long id) {
        if (id != null)
            return productRepository.findById(id).orElse(null);
        return null;
    }

    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product createProduct(ProductBean productBean){
        Product product = new Product(productBean);

        return saveProduct(product);
    }

    public Product updateProduct(ProductBean productBean){
        if(productBean != null) {
            Product product = getProductById(productBean.getId());
            return saveProduct(mappingService.ofProductBean(product, productBean));
        }
        return null;
    }

    public void deleteProduct(Long id){
        if(id != null)
            productRepository.deleteById(id);
    }

}
