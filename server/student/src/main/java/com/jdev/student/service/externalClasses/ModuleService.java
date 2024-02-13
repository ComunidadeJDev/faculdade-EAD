package com.jdev.student.service.externalClasses;

import com.jdev.student.model.externalClasses.Module;
import com.jdev.student.repository.ModuleRepository;
import com.jdev.student.service.exceptions.ModuleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public Module findByName(String name) {
        Optional<Module> module = moduleRepository.findByName(name);
        return module.orElseThrow(ModuleNotFoundException::new);
    }
}
