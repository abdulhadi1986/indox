package nl.indox.webshop.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private int categoryId;
	@Column(name="cat_name")
	private String categoryName;
	@Column(name="cat_description")
	private String categoryDescritpion;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescritpion() {
		return categoryDescritpion;
	}
	public void setCategoryDescritpion(String categoryDescritpion) {
		this.categoryDescritpion = categoryDescritpion;
	}
	
	
}
