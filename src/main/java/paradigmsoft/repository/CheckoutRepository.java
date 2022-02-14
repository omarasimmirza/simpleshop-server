package paradigmsoft.repository;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Checkout;
import paradigmsoft.model.Email;
import paradigmsoft.repository.interfaces.ICheckoutRepository;

@Repository
public class CheckoutRepository implements ICheckoutRepository{

	@Autowired
	private JdbcTemplate actions;
	@Autowired
	private EmailRepository send;
	@Autowired
	private CartRepository deleteCart;
	
	@Override
	public List<Checkout> getList(int user_id) {
		var sql = "select * from checkout where user_id = " + user_id;
		return actions.query(sql, 
				(rs, rowNum) ->
					new Checkout(
							rs.getInt("checkout_id"),
							rs.getInt("user_id"),
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
				+ " zipcode, user_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		actions.update(sql, checkout.getCard(), checkout.getDate(), checkout.getCvv(),
				checkout.getStreet(), checkout.getCity(), checkout.getState(), 
				checkout.getZipcode(), checkout.getUser_id());
	}

	@Override
	public void getEmail(List<Email> data) {
		String email = actions.queryForObject("select email from user where user_id = ?", String.class, new Object[] {data.get(0).getId()});
		String text = "Dear Customer,\n\nYour order using your card ending in XXXX XXXX XXXX " + data.get(0).getCardNum().substring(data.get(0).getCardNum().length() - 4, data.get(0).getCardNum().length()) + " has been confirmed, here are the details:";
		Email element = new Email();
		Iterator<Email> item = data.iterator();
		while(item.hasNext()) {
			element = item.next();
			text += "\n________________________________\n\n" +"Product name: " + element.getProdName() + "\nPrice: " + element.getProdPrice() + "\nQuantity: " + element.getProdQuantity() + "\n________________________________\n";
		}
		text += "\nTotal: $" + element.getTotal() + "\n\nCome again!";
		send.sendSimpleMessage(email, "Order Confirmation:", text);
		deleteCart.deleteCart(data.get(0).getId());
	}
	
}