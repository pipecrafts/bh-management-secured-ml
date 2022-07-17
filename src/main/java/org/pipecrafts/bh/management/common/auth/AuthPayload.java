package org.pipecrafts.bh.management.common.auth;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class AuthPayload implements Serializable {
  AuthPayloadType payloadType;
  String username;
  String secret;
}
