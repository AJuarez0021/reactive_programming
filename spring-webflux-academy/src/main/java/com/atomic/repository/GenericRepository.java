package com.atomic.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * The Interface GenericRepository.
 *
 * @author ajuarez
 * @param <T> the generic type
 * @param <I> the generic type
 */
@NoRepositoryBean
public interface GenericRepository<T, I> extends ReactiveCrudRepository<T, I> {

}
