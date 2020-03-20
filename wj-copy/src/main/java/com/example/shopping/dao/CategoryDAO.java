package com.example.shopping.dao;

import com.example.shopping.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan
 * @date 2019/4
 */

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
