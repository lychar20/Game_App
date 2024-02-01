package fr.charly.cap_entrprise.service.interfaces;

import java.util.List;

public interface DAOFindAllInterface<T> {

    List<T> findAll();

    List<T> findAllSorted();


}
