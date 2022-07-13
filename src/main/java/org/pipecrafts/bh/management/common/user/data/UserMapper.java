package org.pipecrafts.bh.management.common.user.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pipecrafts.bh.management.common.user.model.User;

@Mapper
interface UserMapper {

  long insert(@Param("entity") User user);

  User selectByUsername(@Param("username") String username);
}
