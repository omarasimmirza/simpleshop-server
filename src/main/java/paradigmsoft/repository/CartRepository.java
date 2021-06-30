package paradigmsoft.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Cart;
import paradigmsoft.repository.interfaces.ICartRepository;

@Repository
public class CartRepository implements ICartRepository{
	
	@Autowired
	private JdbcTemplate actions;

	@Override
	public float getTotal(int user_id) {
		var sql = "select sum(total) from cart where user_id = ?";
		return actions.queryForObject(sql, Float.class, new Object[] {user_id});
	}

	@Override
	public List<Cart> getList(int user_id) {
		var sql = "select * from cart";
		return actions.query(sql, 
				(rs, rowNum) -> 
						new Cart(
								rs.getInt("cart_id"),
								rs.getInt("product_id"),
								rs.getInt("user_id"),
								rs.getFloat("total"),
								rs.getDate("date")));
	}

	@Override
	public void createCart(Cart cart) {
		var sql = "insert into cart (product_id, date, total, user_id)"
				+ " values (?, ?, ?, ?)";
		actions.update(sql, cart.getProduct_id(), cart.getDate(), cart.getTotal(), cart.getUser_id());
	}

	@Override
	public void deleteCart(int user_id) {
		var sql = "delete from cart where user_id = ?";
		actions.update(sql, new Object[] {user_id});
	}

	@Override
	public void deleteProduct(int product_id) {
		var sql = "delete from cart where product_id = ?";
		actions.update(sql, new Object[] {product_id});
	}
}