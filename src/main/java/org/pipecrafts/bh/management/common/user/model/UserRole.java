package org.pipecrafts.bh.management.common.user.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {
  ADMIN,
  CUSTOMER;

  public String getGrantedAuthority() {
    return String.format("ROLE_%s", this.name());
  }
}
