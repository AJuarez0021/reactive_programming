package com.atomic.service;

import com.atomic.repository.GenericRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class ServiceReactiveImpl.
 *
 * @author ajuarez
 * @param <T> the generic type
 * @param <I> the generic type
 */
public abstract class ServiceReactiveImpl<T, I> implements ServiceReactive<T, I> {

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    protected abstract GenericRepository<T, I> getRepository();

    /**
     * Save.
     *
     * @param t the t
     * @return the mono
     */
    @Override
    public Mono<T> save(T t) {
        return getRepository().save(t);
    }

    /**
     * Update.
     *
     * @param t the t
     * @return the mono
     */
    @Override
    public Mono<T> update(T t) {
        return getRepository().save(t);
    }

    /**
     * Find all.
     *
     * @return the flux
     */
    @Override
    public Flux<T> findAll() {
        return getRepository().findAll();
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the mono
     */
    @Override
    public Mono<T> findById(I id) {
        return getRepository().findById(id);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    @Override
    public Mono<Void> delete(I id) {
        return getRepository().deleteById(id);
    }

}
