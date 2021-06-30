package paradigmsoft.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkout")
public class Checkout {
	private int id, user_id, cart_id, card, cvv, zipcode;
	private String street, city, state;
	private Date date;
	
	public Checkout() {
		
	}
	public Checkout(int id, int user_id, int cart_id, int card, int cvv, int zipcode, String street, String city, String state, Date date) {
		this.cart_id = cart_id;
		this.id = id;
		this.user_id = user_id;
		this.card = card;
		this.cvv = cvv;
		this.zipcode = zipcode;
		this.street = street;
		this.city = city;
		this.state = state;
		this.date = date;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "user_id")
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Column(name = "cart_id")
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	@Column(name = "card_num")
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	@Column(name = "cvv_num")
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	@Column(name = "zipcode")
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Checkout:/nCheckout ID " + id + " User ID: " + user_id + " Cart ID: " + cart_id + " Street Address: " + street;
	}
	
	@Override
	  public boolean equals(Object obj) {
	    if (obj == null) {
		  return false;
	    }
	    return this.getId() == ((Checkout) obj).getId();
	  }
}
