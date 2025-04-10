package org.isdb.StudentCRUD.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Integer id;
	@NotNull
	@Size(max = 10, message = "Name can not be greater than 10")
	private String name;
	@PositiveOrZero
	private Double price;
}
