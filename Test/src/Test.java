public class Test {
    public static void main(String[] args) {
        long num = 19999999999999L;
        int num2 = 1999999999;
        int num3 = Long.valueOf(19999999999999L).intValue();
        System.out.println("num = " + num);
        System.out.println("num2 = " + num2);
        System.out.println("num3 = " + num3);
    }
}
