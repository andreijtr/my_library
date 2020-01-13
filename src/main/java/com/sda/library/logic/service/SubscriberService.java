package com.sda.library.logic.service;

import com.sda.library.dto.SubscriberDTO;
import com.sda.library.entities.Subscriber;
import com.sda.library.repository.SubscriberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberDAO subscriberDAO;

    public SubscriberDTO findByPersonalID(Long personalID) {
        Subscriber subscriber = subscriberDAO.findByPersonalID(personalID);
        return convertSubscriberToSubscriberDTO(subscriber);
    }

    public List<SubscriberDTO> findByFirstNameAndSurname(String firstName, String surname) {
        List<Subscriber> subscriberList = subscriberDAO.findByNames(firstName, surname);
        List<SubscriberDTO> subscriberDTOList = new ArrayList<>();

        for (Subscriber s : subscriberList) {
            subscriberDTOList.add(convertSubscriberToSubscriberDTO(s));
        }
        return subscriberDTOList;
    }

    public List<SubscriberDTO> findAll() {
        List<Subscriber> subscriberList = subscriberDAO.findAll();
        List<SubscriberDTO> subscriberDTOList = new ArrayList<>();

        for (Subscriber s : subscriberList) {
            subscriberDTOList.add(convertSubscriberToSubscriberDTO(s));
        }
        return subscriberDTOList;
    }

    public SubscriberDTO convertSubscriberToSubscriberDTO(Subscriber subscriber) {
        SubscriberDTO subscriberDTO = new SubscriberDTO();
        subscriberDTO.setFirstName(subscriber.getFirstName());
        subscriberDTO.setSurname(subscriber.getSurname());
        subscriberDTO.setPersonalID(subscriber.getPersonalID());
        return subscriberDTO;
    }
}
