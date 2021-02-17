package net.pegasus.api.rest;

import lombok.AllArgsConstructor;
import net.pegasus.api.domain.Message;
import net.pegasus.api.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/message")
@AllArgsConstructor
public class MessageControllerV1 {
  private final MessageRepository messageRepository;

  @GetMapping
  public List<Message> getAll() {
    return messageRepository.findAll();
  }

  @GetMapping("/{id}")
  public Message findOne(@PathVariable("id") Message message) {
    return message;
  }

  @PostMapping
  public Message save(@RequestBody Message message) {
    return messageRepository.save(message);
  }

  @PutMapping("/{id}")
  public Message update(
    @PathVariable("id") Message messageFromDb,
    @RequestBody Message message) {
    BeanUtils.copyProperties(message, messageFromDb, "id");
    return messageRepository.save(messageFromDb);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    messageRepository.deleteById(id);
  }

}
