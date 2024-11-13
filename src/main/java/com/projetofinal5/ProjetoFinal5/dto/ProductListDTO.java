package com.projetofinal5.ProjetoFinal5.dto;

import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductListDTO {
    private List<ProductEntity> produtoList;
}
