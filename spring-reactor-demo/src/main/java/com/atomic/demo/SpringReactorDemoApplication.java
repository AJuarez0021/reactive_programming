package com.atomic.demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class SpringReactorDemoApplication.
 */
@SpringBootApplication
public class SpringReactorDemoApplication implements CommandLineRunner {
    
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(SpringReactorDemoApplication.class);
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringReactorDemoApplication.class, args);
    }
    
    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    @Override
    public void run(String... args) throws Exception {
        LOG.info("===Operadores===");
        List<String> list = createLanguages();
        just(list.get(0));
        fromIterable(list);
        doOnNext(list);
        map(list);
        flatMap(list.get(0));
        range();
        zipWith(list);
        merge(list);
        filter(list);
        takeLast(list);
        take(list);
        cleanData(list);
        defaultIfEmpty(list);
        list = createLanguages();
        onErrorReturn(list);        
        retry(list);
        delayElements();
    }
    
    /**
     * Clean data.
     *
     * @param list the list
     */
    private void cleanData(List<String> list) {
    	list.clear();
    }
    
    /**
     * Creates the languages.
     *
     * @return the list
     */
    private List<String> createLanguages() {
    	return new ArrayList<>(Arrays.asList("Java","Java Script","Java-React"));
    }
    
    /**
     * Creates the list.
     *
     * @return the list
     */
    private List<String> createList() {
        return new ArrayList<>(Arrays.asList("Backend", "Frontend", "Full Stack"));
    }

    /**
     * Just.
     *
     * @param data the data
     */
    public void just(String data) {
        LOG.info("==Just==");
        // Mono.empty();
        Mono<String> mono = Mono.just(data); 
        mono.subscribe(value -> LOG.info(String.format("Valor Seleccionado: %s", value)));
    }

   
    /**
     * From iterable.
     *
     * @param data the data
     */
    public void fromIterable(List<String> data) {
        LOG.info("==fromIterable==");
        // Flux.just("Java", "C#", "Java Script");
        Flux<String> flux = Flux.fromIterable(data); 
        flux.subscribe(LOG::info);
    }
    
    /**
     * Do on next.
     *
     * @param data the data
     */
    public void doOnNext(List<String> data) {
        LOG.info("==doOnNext==");
        Flux<String> flux = Flux.fromIterable(data);
        flux.doOnNext(LOG::info)
                .doOnComplete(() -> LOG.info("Completado"))
                .doOnTerminate(() -> LOG.info("Terminado"))
                .subscribe();
    }
    
    /**
     * Map.
     *
     * @param data the data
     */
    public void map(List<String> data) {
        LOG.info("==map==");
        Flux.fromIterable(data)
                .map(value -> "Valor: " + value)
                .subscribe(LOG::info);
    }
    
    /**
     * Flat map.
     *
     * @param data the data
     */
    public void flatMap(String data) {
        LOG.info("==flatMap==");
        Mono.just(data)
                .flatMap(value -> Mono.just("Valor Seleccionado: " + value))
                .subscribe(LOG::info);
    }
    
    /**
     * Range.
     */
    public void range() {
        LOG.info("==range==");
        Flux<Integer> fx1 = Flux.range(0, 10);
        fx1 = fx1.map(value -> value + 1); 
        fx1.subscribe(value -> LOG.info(String.format("Valor: %d", value)));
    }
    
    /**
     * Delay elements.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void delayElements() throws InterruptedException {
        LOG.info("==delay==");
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(2))
                .doOnNext(value -> LOG.info(String.format("%d", value)))
                .subscribe();
        Thread.sleep(20000);
    }
    
    /**
     * Zip with.
     *
     * @param data the data
     */
    public void zipWith(List<String> data) {
        LOG.info("==zipWith==");
        List<String> list = createList();
        Flux<String> fxData = Flux.fromIterable(data);
        Flux<String> fxList = Flux.fromIterable(list);
        fxData.zipWith(fxList, (value1, value2) -> String.format("Valor1: %s, Valor2: %s", value1, value2))
                .subscribe(LOG::info);
    }
    
    /**
     * Merge.
     *
     * @param data the data
     */
    public void merge(List<String> data) {
        LOG.info("==merge==");
        List<String> list = createList();
        Flux<String> fxData = Flux.fromIterable(data);
        Flux<String> fxList = Flux.fromIterable(list);
        Flux.merge(fxData, fxList)
                .subscribe(LOG::info);
    }
    
    /**
     * Filter.
     *
     * @param data the data
     */
    public void filter(List<String> data) {
        LOG.info("==filter==");
        Flux<String> fxData = Flux.fromIterable(data);
        fxData.filter(value -> value.startsWith("J") ) 
              .subscribe(LOG::info);
    }
    
    /**
     * Take last.
     *
     * @param data the data
     */
    public void takeLast(List<String> data) {
        LOG.info("==takeLast==");
        Flux<String> fxData = Flux.fromIterable(data);
        fxData.takeLast(1)
                .subscribe(LOG::info);
    }
    
    /**
     * Take.
     *
     * @param data the data
     */
    public void take(List<String> data) {
        LOG.info("==take==");
        Flux<String> fxData = Flux.fromIterable(data);
        fxData.take(2)
                .subscribe(LOG::info);
    }
    
    /**
     * Default if empty.
     *
     * @param data the data
     */
    public void defaultIfEmpty(List<String> data) {
        LOG.info("==defaultIfEmpty==");
        Flux<String> fxData = Flux.fromIterable(data);
        fxData.defaultIfEmpty("La lista esta vacia")
                .subscribe(LOG::info);
        
    }
    
    /**
     * On error return.
     *
     * @param data the data
     */
    public void onErrorReturn(List<String> data) {
        LOG.info("==onErrorReturn==");
        Flux<String> fxData = Flux.fromIterable(data);
        fxData.doOnNext(p -> {
            throw new ArithmeticException("MAL CALCULO");
        })
                //.onErrorMap(ex -> new ArithmeticException("MAL CALCULO"))
                .onErrorReturn("Ocurrio un error")
				.doOnComplete(() -> LOG.info("Completado"))
                .subscribe(LOG::info);
    }
        
    /**
     * Retry.
     *
     * @param data the data
     */
    public void retry(List<String> data) {
    	LOG.info("==retry==");
		Flux<String> fxData = Flux.fromIterable(data);
		fxData
		.doOnNext(p -> {
			LOG.info("Intentando....");
			throw new ArithmeticException("Ocurrio un error");
		})
		.retry(3)
		.onErrorReturn("Error!")
		.subscribe(LOG::info);
	}
}
