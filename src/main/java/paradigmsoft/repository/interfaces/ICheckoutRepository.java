package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.Checkout;
import paradigmsoft.model.Email;

@Repository
public interface ICheckoutRepository {
	List<Checkout> getList(int cart_id);
	void createCheckout(Checkout checkout);
	void getEmail(List<Email> data);
}
