package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.Products;

@Repository
public interface IProductsRepository {
	List<Products> productList();
	Products getProduct(int id);
	List<Products> search(String keyword);
}
