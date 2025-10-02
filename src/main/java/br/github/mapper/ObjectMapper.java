package br.github.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ObjectMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destination) {

        List<D> destinationObjetcs = new ArrayList<D>();
        for (Object origin : origins) {
            destinationObjetcs.add(mapper.map(origin, destination));
        }

        return destinationObjetcs;
    }
}
