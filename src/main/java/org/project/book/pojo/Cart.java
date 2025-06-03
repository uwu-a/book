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
public class Cart {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "购物车ID")
    private BigInteger id;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "购物车所属用户ID")
    private BigInteger userid;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "车内图书ID")
    private BigInteger bookid;
    @Schema(description = "该图书数量")
    private int amount;
}
