package bran.packages.tyre.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bran.packages.tyre.entity.Tyre;
import bran.packages.tyre.enums.Season;

public interface TyreRepository extends JpaRepository<Tyre, Long> {

	Optional<Tyre> findByFrontID(UUID frontID);
	
	List<Tyre> findTop12ByOrderByPriceAsc();

	@Query("select t from Tyre t where t.width like %?1% and t.height like %?2% and t.diameter like %?3% and t.season = ?4 and t.price > ?5 and t.price < ?6 ")
	Page<Tyre> search(String width, String height, String diameter, Season season, double priceFrom, double priceTo, Pageable pageable);  

	@Query(value = "select distinct width from tyres order by width ASC", nativeQuery = true)
	List<String> findAllWidth();
	
	@Query(value = "select distinct height from tyres order by height ASC", nativeQuery = true)
	List<String> findAllHeight();
	
	@Query(value = "select distinct diameter from tyres order by diameter ASC", nativeQuery = true)
	List<String> findAllDiameter();
	
}
