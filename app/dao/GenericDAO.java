public abstract class GenericDAO<T, ID> {
    protected final List<T> entities = new ArrayList<>();
    protected ID currentId;

    public void save(T entity) {
        setId(entity, generateId());
        entities.add(entity);
    }

    public T findById(ID id) {
        return entities.stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst()
                .orElse(null);
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

    public List<T> findByCriteria(Predicate<T> searchFunction) {
        entities.stream()
                .filter(searchFunction)
    }

    protected abstract ID generateId();
    protected abstract ID getId(T entity);
    protected abstract void setId(T entity, ID id);
}