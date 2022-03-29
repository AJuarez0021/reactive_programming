package com.atomic.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Interface ServiceReactive.
 *
 * @author ajuarez
 * @param <T> the generic type
 * @param <I> the generic type
 */
public interface ServiceReactive<T, I> {

    /**
     * Save.
     *
     * @param t the t
     * @return the mono
     */
    Mono<T> save(T t);

    /**
     * Update.
     *
     * @param t the t
     * @return the mono
     */
    Mono<T> update(T t);

    /**
     * Find all.
     *
     * @return the flux
     */
    Flux<T> findAll();

    /**
     * Find by id.
     *
     * @param id the id
     * @return the mono
     */
    Mono<T> findById(I id);

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    Mono<Void> delete(I id);
}
