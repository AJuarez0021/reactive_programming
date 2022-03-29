package com.atomic.service;

import com.atomic.model.Courses;
import com.atomic.repository.CoursesRepository;
import com.atomic.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class CoursesServicesImpl.
 *
 * @author ajuarez
 */
@Service
public class CoursesServicesImpl extends ServiceReactiveImpl<Courses, Integer> implements CoursesServices {

    /** The repository. */
    @Autowired
    private CoursesRepository repository;

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    @Override
    protected GenericRepository<Courses, Integer> getRepository() {
        return repository;
    }

}
