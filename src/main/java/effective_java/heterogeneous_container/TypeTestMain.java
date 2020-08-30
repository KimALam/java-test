package effective_java.heterogeneous_container;

public class TypeTestMain {
    public static void main(String[] args) {
        Favorites favorites = new Favorites();

        favorites.putFavorite(String.class, "aaa");
        favorites.putFavorite(Integer.class, 111);
        favorites.putFavorite(Double.class, 2.0);

        System.out.println("String: " + favorites.getFavorite(String.class));
        System.out.println("Integer: " + favorites.getFavorite(Integer.class));
        System.out.println("Double: " + favorites.getFavorite(Double.class));
    }
}
