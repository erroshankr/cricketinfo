package com.cricket.info.validators;

import java.util.List;

public interface DataValidator<T> {

    List<String> validate(T data);
}
