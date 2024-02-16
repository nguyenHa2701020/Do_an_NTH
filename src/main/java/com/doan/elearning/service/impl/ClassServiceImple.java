package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.repository.ClassRepository;
import com.doan.elearning.service.ClassService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ClassServiceImple implements ClassService{
private final ClassRepository cr;
    @Override
    public List<Eclass> findAll() {
      return cr.findAll();
    }

    @Override
    public Eclass save(ClassDto classdto) {
       Eclass c= new Eclass();
       c.setName(classdto.getName());
       c.setStart(classdto.getStart());
       c.setLevel(classdto.getLevel());
       c.setIdGV(classdto.getIdGV());
       return cr.save(c);

    }

    @Override
    public Eclass findByLgid(Long idlg) {
        
        return cr.findByLgid(idlg);
    }

    @Override
    public List<Eclass> findByidGV(Long id) {
      return cr.findByidGV(id);
    }
    
}
