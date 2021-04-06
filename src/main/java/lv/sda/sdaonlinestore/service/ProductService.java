package lv.sda.sdaonlinestore.service;

import lv.sda.sdaonlinestore.entity.Product;
import lv.sda.sdaonlinestore.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts(){
        List<Product> product = new ArrayList<Product>();
        productRepository.findAll().forEach(e -> product.add(e));
        return product;
    }
    @Transactional
    public void saveOrUpdate(Product product){
        productRepository.save(product);
    }
    @Transactional
    public void update(Product product, Long productId){
        productRepository.save(product);
    }
    @Transactional
    public void save(Product product){
        productRepository.save((product));
    }
    @Transactional
    public void delete(Product product){
        productRepository.delete(product);
    }
    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

}
