package sowndv02.learning.shop.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "searchByName", query="SELECT o From Product o where o.name like ?1")
})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	@NotEmpty(message = "Name must be entered")
	@Length(min = 5, message = "Length of name must be greater than 5 characters")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(nullable = false)
	@Min(value = 0, message = "Price must be greater than or equals 0")
	@NotNull(message = "Price must be entered")
	private Double price;
	
	
	@Column(length = 100)
	private String imageUrl;
	
	@Column(nullable = false)
	@Min(value = 0, message = "Quantity must be greater than or equals 0")
	@NotNull(message = "Quantity must be entered")
	private Integer quantity;
	
	@Column(nullable = false)
	@Min(value = 0, message = "Discount must be greater than or equals 0")
	@Max(value = 100, message = "Discount must be less than 100")
	@NotNull(message = "Discount must be entered")
	private Float discount;
	
	private ProductStatus status;
	
	
	@OneToMany(mappedBy = "product")
	Set<OrderDetail> orderDetails;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	
	@PrePersist
	public void prePersit() {
		createdDate = new Date();
	}
}
