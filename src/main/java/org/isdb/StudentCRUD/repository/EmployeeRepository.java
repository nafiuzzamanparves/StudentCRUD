package org.isdb.StudentCRUD.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.isdb.StudentCRUD.model.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert employeeInsert;

	public EmployeeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.employeeInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("EmployeeSB")
				.usingGeneratedKeyColumns("id");
	}

	public int save(Employee employee) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", employee.getName());
		parameters.put("email", employee.getEmail());
		parameters.put("designation", employee.getDesignation());
		parameters.put("age", employee.getAge());
		parameters.put("address", employee.getAddress());
		parameters.put("dob", employee.getDob());
		parameters.put("salary", employee.getSalary());

		Number key = employeeInsert.executeAndReturnKey(parameters);
		return key.intValue();
	}

	public Optional<Employee> findById(int id) {
		try {
			String sql = "SELECT * FROM EmployeeSB WHERE id = ?";
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public List<Employee> findAll() {
		String sql = "SELECT * FROM EmployeeSB";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	public int update(Employee employee) {
		String sql = "UPDATE EmployeeSB SET name = ?, email = ?, designation = ?, "
				+ "age = ?, address = ?, dob = ?, salary = ? WHERE id = ?";

		return jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getDesignation(),
				employee.getAge(), employee.getAddress(), employee.getDob(), employee.getSalary(), employee.getId());
	}

	public int deleteById(int id) {
		String sql = "DELETE FROM EmployeeSB WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public boolean existsById(int id) {
		String sql = "SELECT COUNT(*) FROM EmployeeSB WHERE id = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
		return count != null && count > 0;
	}

	public List<Employee> findByName(String name) {
		String sql = "SELECT * FROM EmployeeSB WHERE name LIKE ?";
		return jdbcTemplate.query(sql, new EmployeeRowMapper(), "%" + name + "%");
	}

	private static class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
					rs.getString("designation"), rs.getInt("age"), rs.getString("address"),
					rs.getObject("dob", LocalDate.class), rs.getDouble("salary"));
		}
	}

	public Employee saveAndReturnEmp(Employee employee) {
		try (Connection connection = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO EmployeeSB (name, email, designation, age, address, dob, salary) VALUES (?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getDesignation());
			preparedStatement.setInt(4, employee.getAge());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setObject(6, employee.getDob());
			preparedStatement.setDouble(7, employee.getSalary());

			// Execute the insert
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating employee failed, no rows affected.");
			}

			// Get the generated ID
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);

					// Set the ID in the employee object
					Employee savedEmployee = new Employee(id, employee.getName(), employee.getEmail(),
							employee.getDesignation(), employee.getAge(), employee.getAddress(), employee.getDob(),
							employee.getSalary());

					return savedEmployee;
				} else {
					throw new SQLException("Creating employee failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error saving employee", e);
		}
	}

}