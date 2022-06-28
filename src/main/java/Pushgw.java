import io.prometheus.client.Gauge;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;

import java.io.IOException;

public class Pushgw {

    public static void main( String[] args ) throws IOException {
        PushGateway pushGateway = new PushGateway( "localhost:9092" );
        CollectorRegistry registry = new CollectorRegistry();

        Gauge gauge = Gauge.build()
                .name( "java_push_gateway" )
                .help( "java_push_gateway" )
                .register( registry );

        for ( int i = 0; i < 1000; i++ ) {
            gauge.setToCurrentTime();
            pushGateway.push( registry, "Job A" );
        }

    }

}
