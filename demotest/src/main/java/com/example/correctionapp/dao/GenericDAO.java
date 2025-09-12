package com.example.correctionapp.dao;

import com.example.correctionapp.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class GenericDAO<T, ID> {
    protected final List<T> entities = new ArrayList<>();
    protected ID currentId;

    public void save(T entity) {
        setId(entity, generateId());
        entities.add(entity);
    }

    public Optional<T> findById(ID id) {
        return entities.stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst();
    }

    public void update(T entity) {
        ID id = getId(entity);
        for (int i = 0; i < entities.size(); i++) {
            if (getId(entities.get(i)).equals(id)) {
                entities.set(i, entity);
                return;
            }
        }
        throw new IllegalArgumentException("Entité avec l'ID " + id + " non trouvée pour la mise à jour.");
    }

    public void delete(T entity) {
        entities.remove(entity);
    }

    public List<T> findAll() {
        return new ArrayList<>(entities);
    }

    // CORRECTION : Méthode complétée
    public List<T> findByCriteria(Predicate<T> searchFunction) {
        return entities.stream()
                .filter(searchFunction)
                .collect(Collectors.toList());
    }

    protected abstract ID generateId();
    protected abstract ID getId(T entity);
    protected abstract void setId(T entity, ID id);
}
