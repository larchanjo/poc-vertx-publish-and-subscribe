package app.verticles;

import app.Application;
import app.EventBusProperties;
import app.domain.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import lombok.Builder;
import lombok.val;

@Builder
public class PublisherVerticle extends AbstractVerticle {

  private static Logger logger = LoggerFactory.getLogger(SubscriberVerticle.class);

  @Override
  public void start(Future<Void> startFuture) throws JsonProcessingException {
    val channel = EventBusProperties.MESSAGE_CHANNEL;
    val message = Message.builder().build();
    val format = "%s - Publishing %s to %s";

    logger.info(String.format(format, getClass().getSimpleName(), message, channel));
    getVertx().eventBus().publish(channel, Application.OBJECT_MAPPER.writeValueAsString(message));
  }

}
