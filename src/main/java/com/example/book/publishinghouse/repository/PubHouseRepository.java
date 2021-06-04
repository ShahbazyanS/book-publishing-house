package com.example.book.publishinghouse.repository;

import com.example.book.publishinghouse.model.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PubHouseRepository extends JpaRepository<PublishingHouse, Integer> {
}
