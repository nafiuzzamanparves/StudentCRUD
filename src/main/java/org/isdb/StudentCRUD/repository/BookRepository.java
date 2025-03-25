package org.isdb.StudentCRUD.repository;

import org.isdb.StudentCRUD.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
