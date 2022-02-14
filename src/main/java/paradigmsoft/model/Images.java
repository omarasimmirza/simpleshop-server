package paradigmsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="images")
public class Images {
	private int image_id, product_id;
	private String img_url;
	
	public Images(int image_id, int product_id, String img_url) {
		super();
		this.image_id = image_id;
		this.product_id = product_id;
		this.img_url = img_url;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	@Column(name = "product_id")
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@Column(name = "img_url")
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		  return false;
		}
		return this.getImage_id() == ((Images) obj).getImage_id();
	}
}
