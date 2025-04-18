package model;

public class Pair<K, V> {
    private final K key;
    private final V value;

    // Constructeur
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getter pour la clé
    public K getKey() {
        return key;
    }

    // Getter pour la valeur
    public V getValue() {
        return value;
    }

    // Redéfinir toString() pour un affichage plus simple
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    // Redéfinir equals() et hashCode() pour comparaison d'objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) && value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return 31 * key.hashCode() + value.hashCode();
    }
}
