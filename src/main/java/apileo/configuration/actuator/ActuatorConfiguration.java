package apileo.configuration.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ActuatorConfiguration implements ReactiveHealthIndicator {

	//Para referência futura: https://www.baeldung.com/spring-boot-actuators
	@Override public Mono<Health> health() {
		return checkDownstreamServiceHealth().onErrorResume(
				ex -> Mono.just(new Health.Builder().down(ex).build())
		);
	}

	private Mono<Health> checkDownstreamServiceHealth() {
		// we could use WebClient to check health reactively
		return Mono.just(new Health.Builder().up().build());
	}
}
