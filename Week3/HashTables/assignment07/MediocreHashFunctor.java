package assignment07;




public class MediocreHashFunctor implements HashFunctor {
        public int hash(String item) {
            int hash = 7;
            for (int i = 0; i < item.length(); i++) {
                hash = hash * 31 + item.charAt(i);
            }
            return item.hashCode();
        }
}
