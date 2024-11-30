package com.projetofinal5.ProjetoFinal5.repository;

import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
