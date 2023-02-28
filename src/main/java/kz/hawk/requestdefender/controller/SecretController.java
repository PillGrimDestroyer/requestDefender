package kz.hawk.requestdefender.controller;

import kz.hawk.requestdefender.model.request.CheckRequest;
import kz.hawk.requestdefender.model.request.PrepareRequest;
import kz.hawk.requestdefender.register.SecretRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/secret")
@RequiredArgsConstructor
public class SecretController {

  private final SecretRegister secretRegister;

  @PostMapping("/prepare")
  public @ResponseBody
  ResponseEntity<?> prepare(@Valid @RequestBody PrepareRequest prepareRequest) {
    var resp = secretRegister.prepareRequest(prepareRequest);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  @PostMapping("/request_check")
  public @ResponseBody
  ResponseEntity<?> checkRequest(@RequestBody CheckRequest checkRequest) {
    var resp = secretRegister.checkRequest(checkRequest);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }
}
