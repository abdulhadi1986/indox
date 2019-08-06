package nl.indox.webshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
