package bran.packages.tyre.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bran.packages.tyre.dto.TyreDTO;
import bran.packages.tyre.dto.TyreDimensions;
import bran.packages.tyre.pagination.Pagination;
import bran.packages.tyre.service.TyreService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tyre")
public class TyreContoller {

	private final TyreService tyreService;
	
	@GetMapping("/popular")
	public ResponseEntity<List<TyreDTO>> findMostPopular(){
		return new ResponseEntity<>(tyreService.findMostPopular(),HttpStatus.OK);
	}
	
	@GetMapping("dimensions")
	public ResponseEntity<TyreDimensions> allDimensions(){
		return new ResponseEntity<>(tyreService.allDimensions(),HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<Page<TyreDTO>> search(@Valid @RequestBody Pagination pagination){
		return new ResponseEntity<>(tyreService.search(pagination),HttpStatus.OK);
	}
	
	//TODO create, update i delete za admina
}
