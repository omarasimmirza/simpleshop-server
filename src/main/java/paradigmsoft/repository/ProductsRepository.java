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
						rs.getFloat("price"),
						rs.getString("disp_img")));
	}

	@Override
	public Products getProduct(int id) {
		var sql = "select * from products where product_id = " + id;
		return actions.queryForObject(sql, (rs, rowNum) ->
					new Products(
						rs.getInt("product_id"),
						rs.getInt("items"),
						rs.getString("name"),
						rs.getString("department"),
						rs.getString("description"),
						rs.getFloat("price"),
						rs.getString("disp_img")));
	}
	
	public List<Products> search(String keyword) {
		var sql = "select * from products where instr(name, \"" + keyword + "\")";
		return actions.query(sql, (rs, rowNum) ->
				new Products(
						rs.getInt("product_id"),
						rs.getInt("items"),
						rs.getString("name"),
						rs.getString("department"),
						rs.getString("description"),
						rs.getFloat("price"),
						rs.getString("disp_img")));	
	}
}