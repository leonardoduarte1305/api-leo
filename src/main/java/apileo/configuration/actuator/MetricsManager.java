package apileo.configuration.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import apileo.repository.ColaboradorRepository;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

@Component
public class MetricsManager {

	@Bean
	public MeterBinder quantidadeDeColaboradores(ColaboradorRepository repository) {
		return (meterRegistry) -> {
			Gauge.builder("colaboradores.qtd", repository::count)
					.register(meterRegistry);
		};
	}

	@Bean
	public CountedAspect countedAspect(MeterRegistry meterRegistry){
		return new CountedAspect(meterRegistry);

		//Este Bean gerencia os @Counted da aplicação, um exemplo está abaixo
		//@Counted(value = "colaboradores.count.listarColaboradores")
		//https://youtu.be/9dXxm3ff4oI?t=2481
	}
}
