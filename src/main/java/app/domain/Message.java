package app.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

  private final String id = UUID.randomUUID().toString();

}
