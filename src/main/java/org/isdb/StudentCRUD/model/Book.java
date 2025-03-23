package org.isdb.StudentCRUD.model;
/*
 * Book
   id - mandatory
   name - mandatory
   author - optional
   publisher - optional
   class - mandatory
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "T_BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 100)
	private String author;

	@Column(nullable = false, length = 100)
	private String publisher;

//	@Transient
	@OneToOne
	@JoinColumn(name = "clazz", referencedColumnName = "id")
	private StudentClass clazz;

	@ManyToOne
	@JoinColumn(name = "student", nullable = false)
	private Student student;
}
