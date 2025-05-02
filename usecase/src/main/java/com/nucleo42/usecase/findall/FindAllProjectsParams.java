package com.nucleo42.usecase.findall;

import java.util.List;

public record FindAllProjectsParams(
        String name,
        Integer vacancies,
        List<Long> technologies,
        String memberName,
        Integer month,
        Integer year
) {
}
