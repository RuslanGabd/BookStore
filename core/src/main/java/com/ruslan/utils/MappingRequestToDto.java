package com.ruslan.utils;

import com.ruslan.dto.RequestDto;
import com.ruslan.entity.request.Request;
import org.springframework.stereotype.Service;

@Service
public class MappingRequestToDto {

    public RequestDto mapToRequestDto(Request request) {
        return new RequestDto(request.getId(), request.getDate(), request.getBook());
    }

    public Request mapToRequest(RequestDto dto) {
        return new Request(dto.getId(), dto.getBook(), dto.getDateCreated());
    }
}