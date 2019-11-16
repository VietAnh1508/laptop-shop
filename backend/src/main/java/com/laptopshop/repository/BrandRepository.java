package com.laptopshop.repository;

import com.laptopshop.dto.BrandDto;
import com.laptopshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select new com.laptopshop.dto.BrandDto(id, name, logoImagePath) from Brand")
    List<BrandDto> getAll();

    Optional<Brand> findByName(String name);

}
