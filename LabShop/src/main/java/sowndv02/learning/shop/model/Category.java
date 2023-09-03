package sowndv02.learning.shop.model;


import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@Column(length = 50)
	@Column(columnDefinition = "nvarchar(200) not null")
	@NotEmpty(message ="Name must be entered" )
	@Length(max = 50, min = 5, message="Length is between 5 and 50")
	private String name;
	
	@OneToMany(mappedBy = "category")
	Set<Product> products;
}
