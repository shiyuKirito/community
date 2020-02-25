import java.util.*;

public class Text {
    public Set set = new HashSet<>();
    public  List list1 = new LinkedList<>();
    public  void solve(){
        set.add(1);
        set.add(3);
        set.add(4);
        set.add("nihao");
        set.add(2);
        list1.addAll(set);
        for(Object num:set){
            System.out.println(num);
        }
        System.out.println("--------");
        for(int i=0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
        Integer a = 2;
        System.out.println(set.remove(a));
        System.out.println(set.remove(a));
        System.out.println(set.remove(a));
        System.out.println("---1111111-----");
        for(int i=0;i<set.size();i++){
            System.out.println(list1.get(i));
        }
        System.out.println("--------");
        for(int i=0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
    }

    public static void main(String[] args) {
        Text t = new Text();
        t.solve();
    }
}
