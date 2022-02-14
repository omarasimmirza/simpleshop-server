package paradigmsoft.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	private int id, product_id, user_id, quantity;
	private float total;
	private Date date;
	
	public Cart() {
		
	}
	public Cart(int id, int product_id, int user_id, float total, Date date, int quantity) {
		this.id = id;
		this.product_id = product_id;
		this.user_id = user_id;
		this.total = total;
		this.date = date;
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "product_id")
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@Column(name = "user_id")
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Column(name = "total")
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart items:\nCart ID: " + id + " User ID: " + user_id + " Product ID: " + product_id + " Total: " + total + " Date: " + date + " Quantity: " + quantity;
	}
	
	@Override
	  public boolean equals(Object obj) {
	    if (obj == null) {
		  return false;
	    }
	    return this.getId() == ((Cart) obj).getId();
	  }
}
