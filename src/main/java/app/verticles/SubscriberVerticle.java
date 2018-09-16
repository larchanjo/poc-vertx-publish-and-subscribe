package app.verticles;

import app.Application;
import app.EventBusProperties;
import app.domain.Message;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.io.IOException;
import lombok.Builder;
import lombok.NonNull;
import lombok.val;

@Builder
public class SubscriberVerticle extends AbstractVerticle {

  private static Logger logger = LoggerFactory.getLogger(SubscriberVerticle.class);
  private final Integer id;

  public SubscriberVerticle(@NonNull Integer id) {
    this.id = id;
  }

  @Override
  public void start(Future<Void> startFuture) {
    val channel = EventBusProperties.MESSAGE_CHANNEL;
    getVertx().eventBus().consumer(channel, message -> {
      try {
        val json = message.body().toString();
        val object = Application.OBJECT_MAPPER.readValue(json, Message.class);
        val format = "%s[%s] - Consuming %s from %s";
        logger.info(String.format(format, getClass().getSimpleName(), id, object, channel));
      } catch (IOException e) {
        logger.error("Error to read message", e);
      }
    });
  }

}
