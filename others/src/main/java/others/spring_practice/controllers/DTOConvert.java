package others.spring_practice.controllers;

public interface DTOConvert<S, T> {
    T convert(S s);
}
