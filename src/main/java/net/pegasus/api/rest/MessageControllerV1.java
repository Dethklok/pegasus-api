package net.pegasus.api.rest;

import net.pegasus.api.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/message")
public class MessageControllerV1 {

  private int counter = 4;
  private List<Map<String, String>> messages = new ArrayList<>() {{
    add(new HashMap<>() {{ put("id", "1"); put("text", "first message"); }});
    add(new HashMap<>() {{ put("id", "2"); put("text", "second message"); }});
    add(new HashMap<>() {{ put("id", "3"); put("text", "third message"); }});
  }};

  @GetMapping
  public List<Map<String, String>> getAll() {
    return messages;
  }

  @GetMapping("/{id}")
  public Map<String, String> findOne(@PathVariable String id) {
    return findMessage(id);
  }

  @PostMapping
  public Map<String, String> save(@RequestBody Map<String, String> message) {
    message.put("id", String.valueOf(counter++));
    messages.add(message);
    return message;
  }

  @PutMapping("/{id}")
  public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
    Map<String, String> messageFromDb = findMessage(id);

    messageFromDb.putAll(message);
    messageFromDb.put("id", id);

    return messageFromDb;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    Map<String, String> message = findMessage(id);

    messages.remove(message);
  }

  private Map<String, String> findMessage(String id) {
    return messages.stream()
      .filter(message -> message.get("id").equals(id))
      .findFirst()
      .orElseThrow(NotFoundException::new);
  }

}
