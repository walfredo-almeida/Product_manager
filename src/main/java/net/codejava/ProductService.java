package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		
		return repo.findAll(pageable);
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
