package fr.charly.cap_entrprise.service.interfaces;

public interface DAOFindBySlugInterface<T> {

    T findBySlug(String slug);


}
