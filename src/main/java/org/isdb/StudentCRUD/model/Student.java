package org.isdb.StudentCRUD.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "T_STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 50)
	private String email;

	@OneToOne
	@JoinColumn(name = "clazz", referencedColumnName = "id", nullable = false)
	private StudentClass clazz;

	@Column(nullable = false, length = 30, unique = true)
	private Integer roll;

	@OneToMany(mappedBy = "student")
	private List<Book> books;

	@Column(nullable = false, length = 17)
	private String phone;

	@Column(length = 100)
	private String address;

	@Column(nullable = false, length = 10)
	private String gender;

	@Column(nullable = false, length = 30)
	private Instant dob;
}
