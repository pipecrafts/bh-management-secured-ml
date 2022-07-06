package org.pipecrafts.bh.management.common.geo.province.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.apache.ibatis.type.Alias;

@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Alias("Province")
public class Province {

  private Long id;

  @NotBlank
  private String name;
}
