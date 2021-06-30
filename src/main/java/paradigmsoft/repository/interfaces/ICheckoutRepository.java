package paradigmsoft.repository.interfaces;

import java.util.List;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Checkout;

@Repository
public interface ICheckoutRepository {
	List<Checkout> getList(int cart_id);
	void createCheckout(Checkout checkout);
	String getEmail(int user_id);
	void deleteCheckout(int id);
}
