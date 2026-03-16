public interface Dictionary<K, V> {
    /**
     * Returnerer elementet hørende til nøglen k, hvis nøglen ikke findes
     * returneres null
     *
     * @return objektet med nøgle key
     * @param key nøglen elementet skal findes til
     */
    V get(K key);

    /**
     * Returnerer om dictionary er tom
     *
     * @return om map'en er tom
     */
    boolean isEmpty();

    /**
     * Indsætter objektet value med nøgle key i dictionary. Hvis key allerede
     * eksisterer overskrives value hørende til key værdi og den gamle value
     * returneres. Hvis key ikke er der returneres null. Hverken key eller
     * value må være null
     *
     * @param key nøglen objektet skal indsættes med
     * @param value objektet der skal indsættes
     * @return den gamle value hvis key eksisterede, ellers null
     */
    V put(K key, V value);

    /**
     * Fjerner (nøgle, værdi)-parret med nøglen key fra dictionary'en og
     * value returneres.
     *
     * @return objektet hørende til nøglen key; null hvis key ikke findes
     * @param key nøglen der med tilhørende objekt skal fjernes
     */
    V remove(K key);

    /**
     * Returnerer antallet af elementer i dictionary
     *
     * @return antal elementer i dictionary
     */
    int size();
}
