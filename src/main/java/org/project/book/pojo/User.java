package org.project.book.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "用户ID")
    private BigInteger id;
    @Schema(description = "用户名")
    private String name;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "电话号码")
    private String phone;
    @Schema(description = "地址")
    private String address;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "用户被封禁?")
    private boolean locked;
}
