package paradigmsoft.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Products;
import paradigmsoft.repository.interfaces.IProductsRepository;

@Repository
public class ProductsRepository implements IProductsRepository{

	@Autowired
	private JdbcTemplate actions;

	@Override
	public List<Products> productList() {
		var sql = "select * from products";
		
		return actions.query(sql, (rs, rowNum) ->
				new Products(
						rs.getInt("product_id"),
						rs.getInt("items"),
						rs.getString("name"),
						rs.getString("department"),
						rs.getString("description"),
						rs.getFloat("price")));
	}

	@Override
	public void createProduct(Products product) {
		var sql = "insert into products (name, department, price, items, description)"
				+ " values (?, ?, ?, ?, ?)";
		actions.update(sql, product.getName(), product.getDepartment(), product.getPrice(), product.getItems(), product.getDescription());
	}

	@Override
	public void updateProduct(Products product) {
		
	}

	@Override
	public void deleteProduct(int id) {
		var sql = "delete from product where product_id = ?";
		actions.update(sql, new Object[] {id});
	}

	@Override
	public Products getProduct(int id) {
		var sql = "select from products where product_id = " + id;
		return actions.queryForObject(sql, (rs, rowNum) ->
					new Products(
						rs.getInt("product_id"),
						rs.getInt("items"),
						rs.getString("name"),
						rs.getString("department"),
						rs.getString("description"),
						rs.getFloat("price")));
	}
	
}