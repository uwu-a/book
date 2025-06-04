package org.project.book.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("order_item")
public class OrderItem implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "订单子项ID")
    private BigInteger id;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "订单项所属父订单ID")
    private BigInteger orderid;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "本订单项图书ID")
    private BigInteger bookid;
    @Schema(description = "本项图书数量")
    private int quantity;
    @Schema(description = "本项图书单价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal univalence;
}
