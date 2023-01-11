import custom_collections.CustomArrayList;

public class MainClass {
    public static void main(String[] args) {
        CustomArrayList<String> ints = new CustomArrayList<>();
        ints.add("ABC");
        ints.add("EFG");
        ints.add("AAA");
        ints.add("ABB");
        ints.add("ABA");

        CustomArrayList.bubbleSortedCollection(ints);

        for (int i = 0; i < ints.size(); i++) {
            System.out.println(ints.get(i));
        }

    }
}

