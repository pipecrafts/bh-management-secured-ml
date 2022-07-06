package org.pipecrafts.bh.management.common.geo.province.rest;


import lombok.RequiredArgsConstructor;
import org.pipecrafts.bh.management.common.geo.province.data.ProvinceService;
import org.pipecrafts.bh.management.common.geo.province.model.Province;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/province")
@RequiredArgsConstructor
public class ProvinceController {

  private final ProvinceService provinceService;

  @GetMapping
  public ResponseEntity<List<Province>> readCollection() {
    List<Province> provinces = new ArrayList<>();
    provinces.add(new Province(1L, "Isabela"));
    provinces.add(new Province(1L, "Cagayan"));
    return ResponseEntity.ok(provinces);
  }

  @GetMapping("/persisted")
  public ResponseEntity<List<Province>> readPersistedCollection() {
    return Optional.ofNullable(provinceService.readAll())
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.noContent().build());
  }

}
