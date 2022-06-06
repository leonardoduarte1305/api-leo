package apileo.configuration.actuator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import apileo.repository.ColaboradorRepository;
import apileo.repository.GestorRepository;
import apileo.repository.SetorRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

@Component
public class ActuatorConfiguration implements ReactiveHealthIndicator {

	public static final String METRIC_API = "api-leo";

	public static final String METRIC_COLABORADOR = "Colaborador";

	public static final String METRIC_GESTOR = "Gestor";

	public static final String METRIC_SETOR = "Setor";

	@Autowired
	private MeterRegistry meterRegistry;
	@Autowired
	private ColaboradorRepository colabRepo;

	@Autowired
	private GestorRepository gestRepo;

	@Autowired
	private SetorRepository setorRepo;

	@PostConstruct
	protected void initialize(){
		Gauge.builder(METRIC_COLABORADOR, colabRepo, ColaboradorRepository::count).register(meterRegistry);

		Gauge.builder(METRIC_GESTOR, gestRepo, GestorRepository::count).register(meterRegistry);

		Gauge.builder(METRIC_SETOR, setorRepo, SetorRepository::count).register(meterRegistry);
	}

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
