package assignment07;

public class GoodHashFunctor implements HashFunctor {
    public int hash(String item) {
        int hash = 5381;

        for (int i = 0; i < item.length(); i++) {
            hash = (hash << 7) + hash + item.charAt(i);
        }

        return hash;

    }
}