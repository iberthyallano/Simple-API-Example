package com.iberthy.backend.controller.Mapper;
import com.iberthy.backend.controller.dto.request.produto.ProdutoFilterDTO;
import com.iberthy.backend.controller.dto.request.produto.ProdutoSaveDTO;
import com.iberthy.backend.controller.dto.response.produto.ProdutoDTO;
import com.iberthy.backend.domain.entity.ProdutoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ProdutoMapper {

    @Autowired
    private ModelMapper mapper;

    public ProdutoModel mapProdutoFilterDTOToModel(ProdutoFilterDTO dto){
        return this.mapper.map(dto, ProdutoModel.class);
    }

    public ProdutoModel mapProdutoSaveDTOToModel(ProdutoSaveDTO dto){
        return this.mapper.map(dto, ProdutoModel.class);
    }

    public ProdutoDTO mapProdutoModelToProdutoDTO(ProdutoModel model){
        return this.mapper.map(model, ProdutoDTO.class);
    }

    public List<ProdutoDTO> mapProdutoModelToProdutoDTO(List<ProdutoModel> models){
        return models.stream().map(md -> this.mapProdutoModelToProdutoDTO(md)).toList();
    }
}
