package com.bookadaisical.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Optional<Image> findById(UUID id);
}
