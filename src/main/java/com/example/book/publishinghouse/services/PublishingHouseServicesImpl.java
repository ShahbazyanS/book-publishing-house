package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.PublishingHouse;
import com.example.book.publishinghouse.repository.PubHouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PublishingHouseServicesImpl implements PublishingHouseServices{

   private final Logger LOGGER = LoggerFactory.getLogger(PublishingHouseServicesImpl.class);

    private final PubHouseRepository pubHouseRepository;
    private final MessageSource messageSource;


    @Override
    public PublishingHouse findById(int id, String locale) {
        PublishingHouse publishingHouse = pubHouseRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.pub-house.not.found", null, new Locale(locale))));
        LOGGER.info(ReturnCode.OK);
        return publishingHouse;
    }

    public PublishingHouse byId(int id){
       return pubHouseRepository.getOne(id);
    }

    @Override
    public PublishingHouse add(PublishingHouse publishingHouse) {
        PublishingHouse save = pubHouseRepository.save(publishingHouse);
        LOGGER.info(ReturnCode.OK);
        return save;
    }

    @Override
    public List<PublishingHouse> pubHouses() {
        return pubHouseRepository.findAll();
    }

    @Override
    public void delete(int id, String locale) {
       pubHouseRepository.delete(pubHouseRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.pub-house.not.found", null, new Locale(locale)))));
        LOGGER.info(ReturnCode.OK);
    }

    @Override
    public PublishingHouse update(int id, PublishingHouse publishingHouse, String locale) {
       PublishingHouse publishingHouseDb = pubHouseRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.pub-house.not.found", null, new Locale(locale))));
       publishingHouseDb.setName(publishingHouse.getName());
       publishingHouseDb.setBooks(publishingHouse.getBooks());
       publishingHouseDb.setContact(publishingHouse.getContact());
        PublishingHouse publishingHouse1 = pubHouseRepository.save(publishingHouseDb);
        LOGGER.info(ReturnCode.OK);
        return publishingHouse1;
    }
}
