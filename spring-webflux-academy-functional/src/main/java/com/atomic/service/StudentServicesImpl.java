package com.atomic.service;

import com.atomic.model.Student;
import com.atomic.repository.GenericRepository;
import com.atomic.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class StudentServicesImpl.
 *
 * @author ajuarez
 */
@Service
public class StudentServicesImpl extends ServiceReactiveImpl<Student, Integer> implements StudentServices {

    /** The repository. */
    @Autowired
    private StudentRepository repository;

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    @Override
    protected GenericRepository<Student, Integer> getRepository() {
        return repository;
    }

}
