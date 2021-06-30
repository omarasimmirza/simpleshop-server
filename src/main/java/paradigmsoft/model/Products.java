package paradigmsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
	private int id, items;
	private String name, department, description;
	private float price;

	public Products() {
		
	}
	public Products(int id, int items, String name, String department, String description, float price) {
		this.id = id;
		this.department = department;
		this.items = items;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "items")
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Products:\nProduct ID: " + id + " Product name: " + name + "Product amount: " + items + "Product price: " + price;
	}
	
	@Override
	  public boolean equals(Object obj) {
	    if (obj == null) {
		  return false;
	    }
	    return this.getId() == ((Products) obj).getId();
	  }
}
