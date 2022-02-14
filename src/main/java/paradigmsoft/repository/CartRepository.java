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
		var sql = "select * from cart where user_id = " + user_id;
		return actions.query(sql, 
				(rs, rowNum) -> 
						new Cart(
								rs.getInt("cart_id"),
								rs.getInt("product_id"),
								rs.getInt("user_id"),
								rs.getFloat("total"),
								rs.getDate("date"),
								rs.getInt("quantity")));
	}

	@Override
	public int createCart(Cart cart) {
		if (actions.queryForObject("select count(*) from cart where product_id = ? and user_id = ?", Integer.class, new Object[] {cart.getProduct_id(), cart.getUser_id()}) == 0) {
			var sql = "insert into cart (product_id, date, total, user_id, quantity) values (?, ?, ?, ?, ?)";
			return actions.update(sql, cart.getProduct_id(), cart.getDate(), cart.getTotal(), cart.getUser_id(), cart.getQuantity());
		}
		int quant = actions.queryForObject("select quantity from cart where product_id = ? and user_id = ?", Integer.class, new Object[] {cart.getProduct_id(), cart.getUser_id()});
		float total = actions.queryForObject("select total from cart where product_id = ? and user_id = ?", Integer.class, new Object[] {cart.getProduct_id(), cart.getUser_id()});
		actions.update("update cart set total = ? where product_id = ? and user_id = ?", total + (total / quant), cart.getProduct_id(), cart.getUser_id());
		return actions.update("update cart set quantity = ? where product_id = ? and user_id = ?", quant + 1, cart.getProduct_id(), cart.getUser_id());
	}

	@Override
	public void deleteCart(int user_id) {
		var sql = "delete from cart where user_id = ?";
		actions.update(sql, new Object[] {user_id});
	}
	
	@Override
	public void deleteProduct(int product_id, int user_id) {
		var sql = "delete from cart where product_id = ? and user_id = ?";
		actions.update(sql, new Object[] {product_id, user_id});
	}
	
	@Override
	public void deleteItem(int product_id, int user_id) {
		int quant = actions.queryForObject("select quantity from cart where product_id = ? and user_id = ?", Integer.class, new Object[] {product_id, user_id});
		if (quant == 1) {
			deleteProduct(product_id, user_id);
		}
		else {
			actions.update("update cart set quantity = ? where product_id = ? and user_id = ?", new Object[] {quant - 1, product_id, user_id});
			float total = actions.queryForObject("select total from cart where product_id = ? and user_id = ?", Integer.class, new Object[] {product_id, user_id});
			actions.update("update cart set total = ? where product_id = ? and user_id = ?", total - (total / quant), product_id, user_id);
		}
	}
}