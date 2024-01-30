package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.entity.Eclass;

public interface ClassService {

     Eclass save(ClassDto classdto);

 List<Eclass> findAll();

  Eclass findByLgid(Long idlg);
}
