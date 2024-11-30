package com.projetofinal5.ProjetoFinal5.dto;

import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListDTO {
    private List<ProductEntity> produtoList;
}
