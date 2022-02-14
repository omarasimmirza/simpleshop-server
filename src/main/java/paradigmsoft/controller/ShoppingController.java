package paradigmsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import paradigmsoft.model.Authenticate;
import paradigmsoft.model.Cart;
import paradigmsoft.model.Checkout;
import paradigmsoft.model.Email;
import paradigmsoft.model.Products;
import paradigmsoft.model.User;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import paradigmsoft.repository.CartRepository;
import paradigmsoft.repository.CheckoutRepository;
import paradigmsoft.repository.ImagesRepository;
import paradigmsoft.repository.ProductsRepository;
import paradigmsoft.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ShoppingController {

	@Autowired
	private ImagesRepository imgRep;
	@Autowired
	private UserRepository userRep;
	@Autowired
	private ProductsRepository productRep;
	@Autowired
	private CheckoutRepository checkoutRep;
	@Autowired
	private CartRepository cartRep;

	@RequestMapping(method = RequestMethod.OPTIONS)
	ResponseEntity<?> options() {
		return ResponseEntity.ok().allow(
			   HttpMethod.GET,
			   HttpMethod.POST,
			   HttpMethod.HEAD,
			   HttpMethod.OPTIONS,
			   HttpMethod.PUT,
			   HttpMethod.DELETE).build();
		
	}
	
	@GetMapping("/listofproducts/")
	public ResponseEntity<List<Products>> listProducts() {
		return ResponseEntity.ok(productRep.productList());
	}
	
	@GetMapping("/cartlist/{id}")
	public ResponseEntity<List<Cart>> cartList(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(cartRep.getList(id));
	}
	
	@GetMapping("/productdetail/{id}")
	public ResponseEntity<Products> productDetail(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(productRep.getProduct(id));
	}
	
	@GetMapping("/getImg/{id}")
	public ResponseEntity<List<String>> getImages(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(imgRep.getImage(id));
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Products>> search(@PathVariable(value="keyword") String keyword) {
		return ResponseEntity.ok(productRep.search(keyword));
	}

	@GetMapping("/userid/{username}")
	public ResponseEntity<Integer> getUserId(@PathVariable(value="username") String username) {
		return ResponseEntity.ok(userRep.getUserId(username));
	}

	@PostMapping("/email")
	public void sendEmail(@RequestBody List<Email> send) {
		checkoutRep.getEmail(send);
	}
	
	@GetMapping("/total/{id}")
	public ResponseEntity<Float> total(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(cartRep.getTotal(id));
	}
	
	@PostMapping("/createcheckout")
	public void checkout(@RequestBody Checkout checkout) {
		checkoutRep.createCheckout(checkout);
	}
	
	@PostMapping("/createuser")
	public void createNewUser(@RequestBody User user) {
		userRep.createUser(user);
	}
	
	@PostMapping("/createcart")
	public ResponseEntity<Cart> cart(@RequestBody Cart cart) {
		if(cartRep.createCart(cart) == 1)
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/loggedin")
	public ResponseEntity<User> loggedIn(@RequestBody Authenticate user) {
		if(userRep.login(user)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/deletebycart/{id}")
	public void deleteByCart(@PathVariable(value="id") int id) {
		cartRep.deleteCart(id);
	}
	
	@DeleteMapping("/deletebyproduct/{product_id}/{user_id}")
	public void deleteByProduct(@PathVariable(value="product_id") int product_id, @PathVariable(value="user_id") int user_id) {
		cartRep.deleteProduct(product_id, user_id);
	}
	
	@DeleteMapping("/deletebyitem/{product_id}/{user_id}")
	public void deleteByItem(@PathVariable(value="product_id") int product_id, @PathVariable(value="user_id") int user_id) {
		cartRep.deleteItem(product_id, user_id);
	}
}