package com.powerrangers.linkedhu.mapper;

import com.powerrangers.linkedhu.dto.PrivateMessageDTO;
import com.powerrangers.linkedhu.entity.PrivateMessage;
import com.powerrangers.linkedhu.repository.PrivateMessageRepository;
import com.powerrangers.linkedhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PrivateMessageMapper{

    PrivateMessageDTO mapToDto(PrivateMessage privateMessage);

    PrivateMessage mapToEntity(PrivateMessageDTO privateMessageDTO);
    List<PrivateMessageDTO> mapToDto(List<PrivateMessage> privateMessageList);

    List<PrivateMessage> mapToEntity(List<PrivateMessageDTO> privateMessageDTOList);
}

