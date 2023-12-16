package com.ruslan.utils;

import com.ruslan.database.DAO.BookRepository;
import com.ruslan.dto.RequestDto;
import com.ruslan.entity.request.Request;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class MappingRequestToDto {

    BookRepository bookRepository;

    public RequestDto mapToRequestDto(Request request) {
        return new RequestDto(request.getId(),
                request.getDate(),
                request.getBook().getId());
    }

    public Request mapToRequest(RequestDto dto) {
        return new Request(dto.getId(),
                bookRepository.findById(dto.getId()).orElse(null),
                dto.getDateCreated());
    }


}