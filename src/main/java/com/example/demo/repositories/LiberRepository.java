package com.example.demo.repositories;

import com.example.demo.entities.LiberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiberRepository extends JpaRepository<LiberEntity, String> {

    @Query(value = "SELECT * FROM LIBER WHERE ISBN LIKE :isbn AND titulli LIKE :titulli", nativeQuery = true)
    List<LiberEntity> findByTitullAndIsbn(String titulli, String isbn);
}
