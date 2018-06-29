package io.pivotal.workshop;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final MeterRegistry meterRegistry;

    public GreetingController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/")
    public String hello() {
        meterRegistry.counter("counter.services.greeting.invoked").increment();
        return "Hello World";
    }
}
