package com.example.book.publishinghouse.services;

import com.example.book.publishinghouse.constants.ReturnCode;
import com.example.book.publishinghouse.exceptions.ResourcesNotFoundException;
import com.example.book.publishinghouse.model.Contact;
import com.example.book.publishinghouse.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ContactServicesImpl implements ContactServices{

   private final Logger LOGGER = LoggerFactory.getLogger(ContactServicesImpl.class);

    private final ContactRepository contactRepository;
    private final MessageSource messageSource;

    @Override
    public Contact findById(int id, String locale) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.contact.not.found", null, new Locale(locale))));
        LOGGER.info(ReturnCode.OK);
        return contact;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll(String locale) {
        List<Contact> all = contactRepository.findAll();
        if (all.isEmpty()){
            LOGGER.info(messageSource.getMessage(
                    "error.messages.null.value", null, new Locale(locale)));
        }
        LOGGER.info(ReturnCode.OK);
        return all;
    }

    @Override
    public Contact update(int id, Contact contact, String locale) {
       Contact contactDb = contactRepository.save(contactRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.contact.not.found", null, new Locale(locale)))));
       contactDb.setAddress(contact.getAddress());
       contactDb.setEmail(contact.getEmail());
       contactDb.setPhone(contact.getPhone());
        Contact saveContact = contactRepository.save(contactDb);
        LOGGER.info(ReturnCode.OK);
        return saveContact;
    }

    @Override
    public void delete(int id, String locale) {
       contactRepository.delete(contactRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException(messageSource.getMessage(
                        "error.messages.contact.not.found", null, new Locale(locale)))));
       LOGGER.info(ReturnCode.OK);
    }
}
