package com.iberthy.backend.controller.Mapper;
import com.iberthy.backend.controller.dto.request.usuario.UsuarioFilterDTO;
import com.iberthy.backend.controller.dto.request.usuario.UsuarioSaveDTO;
import com.iberthy.backend.controller.dto.response.usuario.UsuarioDTO;
import com.iberthy.backend.domain.entity.UsuarioModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    @Autowired
    private ModelMapper mapper;

    public UsuarioModel mapUsuarioFilterDTOToModel(UsuarioFilterDTO dto){
        return this.mapper.map(dto, UsuarioModel.class);
    }

    public UsuarioModel mapUsuarioSaveDTOToModel(UsuarioSaveDTO dto){
        return this.mapper.map(dto, UsuarioModel.class);
    }

    public UsuarioDTO mapUsuarioModelToUsuarioDTO(UsuarioModel model){
        return this.mapper.map(model, UsuarioDTO.class);
    }

    public List<UsuarioDTO> mapUsuarioModelToUsuarioDTO(List<UsuarioModel> models){
        return models.stream().map(md -> this.mapUsuarioModelToUsuarioDTO(md)).toList();
    }

}
