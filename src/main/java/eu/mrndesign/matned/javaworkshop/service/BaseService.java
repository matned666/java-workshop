package eu.mrndesign.matned.javaworkshop.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService {


    protected Pageable getPageable(Integer startPage, Integer itemsPerPage) {
        if (itemsPerPage < 1)
            itemsPerPage = 1;
        return PageRequest.of(startPage, itemsPerPage);
    }


}
