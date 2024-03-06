
//
import java.util.*;
//

public class List_basic{
    public static void main(String[] args){
        List<String> String_list = new ArrayList<String>();
        String_list.add("Hey ");
        String_list.add("I am ");
        String_list.add("Mingyuan Ba");

        String_list.set(2,"God");
        String_list.remove(2);
        System.out.println(String_list.get(0));
        System.out.println(String_list.get(1));
    }
}