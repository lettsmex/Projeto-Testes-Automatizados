package com.projetofinal5.ProjetoFinal5.mappers;

import com.projetofinal5.ProjetoFinal5.dto.ProductDTO;
import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(ProductDTO produtoDTO);

    ProductDTO toDTO(ProductEntity produtoEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarProduto(ProductDTO produtoAtualizado, @MappingTarget ProductEntity produtoExistente);
}