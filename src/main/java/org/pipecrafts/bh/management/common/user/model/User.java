package org.pipecrafts.bh.management.common.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.ibatis.annotations.AutomapConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@AutomapConstructor), force = true)
@Alias("User")
public class User implements Serializable {
  private Long id;
  @NotBlank
  @NonNull
  private String username;

  @NotBlank
  @NonNull
  private String password;

  @NotBlank
  @NonNull
  private String firstName;

  private String middleName;

  @NotBlank
  @NonNull
  private String lastName;

  @NotNull
  @NonNull
  private LocalDate birthDate;

  @NonNull
  @NotNull
  private UserRole role;
}
