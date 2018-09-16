package app;

import app.verticles.PublisherVerticle;
import app.verticles.SubscriberVerticle;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Application {

  private static Logger logger = LoggerFactory.getLogger(Application.class);
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static void main(String... arguments) {
    logger.info("Starting application");
    Vertx vertx = Vertx.vertx();

    // Producer verticles
    vertx.deployVerticle(PublisherVerticle.builder().build());

    // Consumer Verticles
    vertx.deployVerticle(SubscriberVerticle.builder().id(1).build());
    vertx.deployVerticle(SubscriberVerticle.builder().id(2).build());
    vertx.deployVerticle(SubscriberVerticle.builder().id(3).build());
  }

}
