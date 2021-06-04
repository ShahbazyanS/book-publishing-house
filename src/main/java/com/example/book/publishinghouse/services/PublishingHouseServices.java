package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.model.PublishingHouse;

public interface PublishingHouseServices {

    PublishingHouse findById(int id, String locale);

    PublishingHouse add(PublishingHouse publishingHouse);

    void delete(int id, String locale);

    PublishingHouse update(int id, PublishingHouse publishingHouse, String locale);
}
