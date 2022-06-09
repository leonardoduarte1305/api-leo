package apileo.configuration.actuator;

//@Component
public class MetricsManager {

	//https://www.youtube.com/watch?v=9dXxm3ff4oI

	public static final String METRIC_API = "api-leo";
	public static final String METRIC_COLABORADOR = "Colaborador";
	public static final String METRIC_GESTOR = "Gestor";
	public static final String METRIC_SETOR = "Setor";

	/*
	@Autowired
	private MeterRegistry meterRegistry;
	@Autowired
	private ColaboradorRepository colabRepo;

	@Autowired
	private GestorRepository gestRepo;

	@Autowired
	private SetorRepository setorRepo;

	@PostConstruct
	protected void initialize() {
		Gauge.builder(METRIC_COLABORADOR, colabRepo, ColaboradorRepository::count).register(meterRegistry);

		Gauge.builder(METRIC_GESTOR, gestRepo, GestorRepository::count).register(meterRegistry);

		Gauge.builder(METRIC_SETOR, setorRepo, SetorRepository::count).register(meterRegistry);
	}*/
}
