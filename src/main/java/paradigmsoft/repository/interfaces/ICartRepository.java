package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.Cart;

@Repository
public interface ICartRepository {
	float getTotal(int user_id);
	List<Cart> getList(int user_id);
	int createCart(Cart cart);
	void deleteCart(int user_id);
	void deleteProduct(int product_id, int user_id);
	void deleteItem(int product_id, int user_id);
}
