package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ClassDto;

import com.doan.elearning.entity.Eclass;


public interface ClassService {

    Eclass save(ClassDto classdto);

    void deleteClass(Long id);

    List<Eclass> findAll();

    // Optinal<Eclass> findById();
    Eclass findByLgid(Long idlg);

    List<Eclass> findByidGV(Long id);

    Eclass update(ClassDto classDto);
}
