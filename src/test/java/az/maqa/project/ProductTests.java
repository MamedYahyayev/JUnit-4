package az.maqa.project;

import az.maqa.project.model.Product;
import az.maqa.project.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateProduct() {
        Product product = new Product("Honor", 1500);
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
    }


    @Test
    @Order(2)
    public void testFindProductByNameExist() {
        String name = "Honor";
        Product product = productRepository.findByName(name);

        assertThat(product.getName()).isEqualTo(name);
    }

    @Test
    @Order(3)
    public void testFindProductByNameNotExist() {
        String name = "Honor 123";
        Product product = productRepository.findByName(name);

        assertNull(product);
    }


    @Test
    @Rollback(false)
    @Order(5)
    public void updateProductTest() {
        String productName = "HonorZPro";
        Product product = new Product(productName, 2469);
        product.setId(1);

        productRepository.save(product);

        Product updateProduct = productRepository.findByName(productName);

        assertThat(updateProduct.getName()).isEqualTo(productName);

    }

    @Test
    @Order(4)
    public void productListTest() {
        List<Product> productList = (List<Product>) productRepository.findAll();

        assertThat(productList).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(6)
    public void deleteProductTest() {
        Integer id = 1;

        boolean isExistBefore = productRepository.findById(id).isPresent();

        productRepository.deleteById(id);

        boolean isExistAfter = productRepository.findById(id).isPresent();

        assertTrue(isExistBefore);
        assertFalse(isExistAfter);

    }

}
