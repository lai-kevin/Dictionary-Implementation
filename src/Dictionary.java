public interface Dictionary<V extends Hashable> {
    /**
     * Checks whether or not the dictionary is empty.
     * @return <code>true</code> if and only if the dictionary has no items, and
     * <code>false</code> otherwise.
     */
    boolean isEmpty();

/**
 * The dictionary <b>insert</b> operation.
 * @param value the value to be inserted into the dictionary.
 * @throws java.lang.NullPointerException if the value is <code>null</code>.
 */
    void insert(V value);

    /**
     * The dictionary <b>delete</b> operation.
     * @param value the value to be removed from the dictionary.
     * @return the given value, if it was actually deleted from the dictionary,
     * and <code>null</code> otherwise.
     *
     * @throws java.lang.NullPointerException if the value to be deleted is
     * <code>null</code>.
     */
    V delete(V value);

    /**
     * The dictionary <b>search</b> operation.
     * @param value the value to search.
     * @return the index associated with the given value, or -1 if the value is
     * not present in the dictionary.
     */
    int find(V value);
}

