package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Book;

@Repository
public interface DataRepository extends JpaRepository<Book , Long> {
	public Optional<Book> findById(long id);
}
