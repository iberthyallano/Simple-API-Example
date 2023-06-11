package com.iberthy.backend.controller.Mapper;
import com.iberthy.backend.controller.dto.request.cliente.ClienteFilterDTO;
import com.iberthy.backend.controller.dto.request.cliente.ClienteSaveDTO;
import com.iberthy.backend.controller.dto.response.cliente.ClienteDTO;
import com.iberthy.backend.domain.entity.ClienteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ClienteMapper {

    @Autowired
    private ModelMapper mapper;

    public ClienteModel mapClienteFilterDTOToModel(ClienteFilterDTO dto){
        return this.mapper.map(dto, ClienteModel.class);
    }

    public ClienteModel mapClienteSaveDTOToModel(ClienteSaveDTO dto){
        return this.mapper.map(dto, ClienteModel.class);
    }

    public ClienteDTO mapClienteModelToClienteDTO(ClienteModel model){
        return this.mapper.map(model, ClienteDTO.class);
    }

    public List<ClienteDTO> mapClienteModelToClienteDTO(List<ClienteModel> models){
        return models.stream().map(md -> this.mapClienteModelToClienteDTO(md)).toList();
    }

}
