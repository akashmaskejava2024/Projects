package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean existsByName(String name);

}
