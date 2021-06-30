package paradigmsoft.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Checkout;
import paradigmsoft.repository.interfaces.ICheckoutRepository;

@Repository
public class CheckoutRepository implements ICheckoutRepository{

	@Autowired
	private JdbcTemplate actions;
	
	@Override
	public List<Checkout> getList(int cart_id) {
		var sql = "select * from checkout";
		return actions.query(sql, 
				(rs, rowNum) ->
					new Checkout(
							rs.getInt("checkout_id"),
							rs.getInt("user_id"),
							rs.getInt("cart_id"),
							rs.getInt("card_num"),
							rs.getInt("cvv_num"),
							rs.getInt("zipcode"),
							rs.getString("street_address"),
							rs.getString("city"),
							rs.getString("state"),
							rs.getDate("card_expire")));
	}

	@Override
	public void createCheckout(Checkout checkout) {
		var sql = "insert into checkout (card_num, "
				+ "card_expire, cvv_num, street_address, city, state,"
				+ " zipcode, user_id, cart_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		actions.update(sql, checkout.getCard(), checkout.getDate(), checkout.getCvv(),
				checkout.getStreet(), checkout.getCity(), checkout.getState(), 
				checkout.getZipcode(), checkout.getUser_id(), checkout.getCart_id());
	}

	@Override
	public String getEmail(int user_id) {
		var sql = "select email from user where user_id = ?";
		
		return actions.queryForObject(sql, String.class, new Object[] {user_id});
	}
	
	@Override
	public void deleteCheckout(int id) {
		var sql = "delete from checkout where user_id = ?";
		
		actions.update(sql, new Object[] {id});
	}

}