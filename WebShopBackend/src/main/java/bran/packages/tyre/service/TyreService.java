package bran.packages.tyre.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import bran.packages.tyre.dto.TyreDTO;
import bran.packages.tyre.dto.TyreDimensions;
import bran.packages.tyre.entity.Tyre;
import bran.packages.tyre.mapper.TyreMapper;
import bran.packages.tyre.pagination.Pagination;
import bran.packages.tyre.repository.TyreRepository;
import bran.packages.user.exception.ProblemWithDatabase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TyreService {

	private final TyreRepository tyreRepository;
	
	private final TyreMapper tyreMapper;
	
	private final int tyresPerPage = 12;
	
	@Transactional
	public TyreDTO save(TyreDTO tyreDTO) {
		
		tyreDTO.setFrontID(UUID.randomUUID());
		
		final Tyre result;
		
		try {
			result = tyreRepository.save(tyreMapper.fromDTO(tyreDTO));
		}catch (Exception e) {
			throw new ProblemWithDatabase("Problem with database while trying to save new entity!");
		}
		
		return tyreMapper.toDTO(result);
	}
	
	@Transactional
	public TyreDTO updatePrice(TyreDTO tyreDTO) {
		
		Optional<Tyre> optional = tyreRepository.findByFrontID(tyreDTO.getFrontID());
		
		if(optional.isEmpty()) {
			//TODO exceptions
		}
		
		Tyre tyre = optional.get();
		
		tyre.setPrice(tyreDTO.getPrice());
		
		TyreDTO result = tyreMapper.toDTO( tyreRepository.save(tyre) );
		
		return result;
	}

	@Transactional
	public void delete(TyreDTO tyreDTO) {
		
		Optional<Tyre> optional = tyreRepository.findByFrontID(tyreDTO.getFrontID());

		if(optional.isEmpty()) {
			//TODO isto
		}
		Tyre tyre = optional.get();
		try {
			tyreRepository.delete(tyre);
		}catch (Exception e) {
			throw new ProblemWithDatabase("Problem with database while trying to delete entity!");
		}	
	}

	public TyreDimensions allDimensions() {
	
		TyreDimensions dimensions = new TyreDimensions();
		
		dimensions.setWidths(tyreRepository.findAllWidth());
		dimensions.setHeights(tyreRepository.findAllHeight());
		dimensions.setDiameters(tyreRepository.findAllDiameter());
		
		return dimensions;
	}
	
	public Page<TyreDTO> search(Pagination pagination) {
				
		Sort sort;
		try {
			String[] sortString = pagination.getSort().split(" ");
			sort = sortString[1].equals("ascending") ? Sort.by(sortString[0].toLowerCase()).ascending() : Sort.by(sortString[0].toLowerCase()).descending();				
			
		}catch (Exception e) {
			sort = Sort.unsorted();
		}
		
		if(pagination.getPriceTo() == 0) {
			pagination.setPriceTo(Double.MAX_VALUE);
		}
		
		//System.out.println(pagination.getPriceFrom() + "    " + pagination.getPriceTo());
		
		Page<TyreDTO> page =
				tyreRepository.search(pagination.getTyreSearch().getWidth(),pagination.getTyreSearch().getHeight(),pagination.getTyreSearch().getDiameter(), 
						pagination.getTyreSearch().getSeason(), pagination.getPriceFrom(), pagination.getPriceTo(), 
							PageRequest.of(pagination.getPage(), tyresPerPage, sort)).map(tyre -> tyreMapper.toDTO(tyre));
							
		return page;
	}
	
	//TODO da proverimo limit
	
	public List<TyreDTO> findMostPopular(){
		List<TyreDTO> test = tyreMapper.toDTOList( tyreRepository.findTop12ByOrderByPriceAsc() );
		return test;
	}

	
	
}
