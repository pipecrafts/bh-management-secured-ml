package org.pipecrafts.bh.management.common.geo.province.data;

import lombok.RequiredArgsConstructor;
import org.pipecrafts.bh.management.common.geo.province.model.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {

  private final ProvinceMapper provinceMapper;

  public List<Province> readAll() {
    return provinceMapper.selectAll();
  }

}
