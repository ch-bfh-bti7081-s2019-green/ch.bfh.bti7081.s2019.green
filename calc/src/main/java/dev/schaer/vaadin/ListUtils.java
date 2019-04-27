package dev.schaer.vaadin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListUtils {
    public static <E> List<List<E>> split(List<E> list, Predicate<E> splitBy){
        List<List<E>> result = new ArrayList<>();
        List<E> currentList = new ArrayList<>();
        for(E element : list){
            if(splitBy.test(element) && !currentList.isEmpty()){
                result.add(currentList);
                currentList = new ArrayList<>();
            }else{
                currentList.add(element);
            }
        }
        if(!currentList.isEmpty()){
            result.add(currentList);
        }
        return result;
    }
}
