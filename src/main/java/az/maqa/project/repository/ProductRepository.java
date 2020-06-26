package az.maqa.project.repository;

import az.maqa.project.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByName(String name);
}
