package com.ntuc.service;

import com.ntuc.repository.*;
import com.ntuc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SempService {
	@Autowired
	private SempRepository repo;
	
//	public List<Semp> listAll(String keyword) {
//		
//		if (keyword !=null) {
//			return repo.findAll(keyword);
//		}
//		return repo.findAll();
//	}
	
	public Page<Semps> listAll(int pageNumber, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber-1, 2,sort);
		if(keyword!=null) {
		return repo.findAll(keyword,pageable);
		}
		return repo.findAll(pageable);
			
	}
	
	
	  public void save(Semps semp) { repo.save(semp); }
	  
	  public Semps get(Integer id) { return repo.findById(id).get(); }
	  
	  public void delete(Integer id) { repo.deleteById(id); }
	 
}
