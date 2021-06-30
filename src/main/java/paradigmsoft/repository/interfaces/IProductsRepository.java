package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.Products;

@Repository
public interface IProductsRepository {
	List<Products> productList();
	void createProduct(Products product);
	void updateProduct(Products product);
	void deleteProduct(int id);
	Products getProduct(int id);
}
