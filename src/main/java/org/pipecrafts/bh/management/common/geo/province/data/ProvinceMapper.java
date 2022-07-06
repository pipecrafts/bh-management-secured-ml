package org.pipecrafts.bh.management.common.geo.province.data;

import org.apache.ibatis.annotations.Mapper;
import org.pipecrafts.bh.management.common.geo.province.model.Province;

import java.util.List;

@Mapper
interface ProvinceMapper {

  List<Province> selectAll();

}
