import java.util.ArrayList;
import java.util.List;

class ListMod {
    static List<Integer> doubleEachElement(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i)*2);
        }
        return list;
    }
    
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        doubleEachElement(list);
        for (int item : list) {
            System.out.println(item);
        }
    }
}