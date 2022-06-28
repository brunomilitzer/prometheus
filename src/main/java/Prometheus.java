import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class Prometheus {

    static final Counter counter = Counter.build()
            .name( "java_random_counter" )
            .help( "JAVA RANDOM COUNTER" )
            .labelNames( "foo", "bar" )
            .register();

    static final Gauge gauge = Gauge.build()
            .name( "java_random_gauge" )
            .help( "JAVA RANDOM GAUGE" )
            .register();

    static final Summary summary = Summary.build()
            .name( "java_my_process_time" )
            .help( "JAVA TIME" )
            .register();

    public static void main( String[] args ) throws IOException {
        counter.labels( "1", "2" ).inc();
        gauge.set( 100 );
        gauge.inc(10);
        gauge.dec(5);

        Summary.Timer tryBlockLengths = summary.startTimer();

        try {
            System.out.println("Hello");
        } finally {
            tryBlockLengths.observeDuration();
        }

        HTTPServer server = new HTTPServer.Builder()
                .withPort( 8000 ).build();
    }
}
