package bran.packages.tyre.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import bran.packages.tyre.dto.TyreDTO;
import bran.packages.tyre.entity.Tyre;

@Mapper(componentModel = "spring")
public interface TyreMapper {

	TyreDTO toDTO(Tyre tyre);
	
	Tyre fromDTO(TyreDTO tyreDTO);
	
	List<TyreDTO> toDTOList(List<Tyre> tyres);
	
}
