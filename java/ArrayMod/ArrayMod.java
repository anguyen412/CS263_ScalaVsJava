class ArrayMod {
    static int[] doubleEachElement(int[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i] * 2;
        }
        return list;
    }
    
    public static void main(String[] args) {
        int[] list = {1, 2, 3};
        doubleEachElement(list);
        for (int item : list) {
            System.out.println(item);
        }
    }
}