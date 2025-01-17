package br.com.projeto.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por fazer a conversão de uma Entidade em VO e vice-versa
 */


public class ModelMapper {

    private static org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
