package com.example.assessment.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeanUtil {
    public static <T> T copy(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }

        try{
            T t = target.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public static <T> List<T> batchCopy(List<?> sources, Class<T> target) {
        if (CollectionUtils.isEmpty(sources) ) {
            return null;
        }

        List<T> ts = new ArrayList<>();
        for (Object source : sources) {
            ts.add(copy(source, target));
        }
        return ts;
    }
}
