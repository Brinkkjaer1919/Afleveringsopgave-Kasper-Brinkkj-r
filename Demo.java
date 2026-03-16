public class Demo {
    static void main() {
        Dictionary<Integer, String> dictionary = new DictionaryOpenAddressing<>();

        System.out.println(dictionary.isEmpty());
        System.out.println(dictionary.size());

        dictionary.put(8, "hans");
        dictionary.put(3, "viggo");
        System.out.println(dictionary.isEmpty());
        System.out.println(dictionary.size());

        System.out.println(dictionary.get(8));

        dictionary.put(7, "bent");
        dictionary.put(2, "lene");
        System.out.println(dictionary.isEmpty());
        System.out.println(dictionary.size());
        System.out.println(dictionary.remove(3));

        System.out.println(dictionary.size());
    }
}
