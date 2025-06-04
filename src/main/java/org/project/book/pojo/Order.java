package org.project.book.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "订单ID")
    private BigInteger id;
    @Schema(description = "订单状态（ 0:未支付 1：已支付 2：派送中 3.已完成 4.待退款 5：已退款）")
    private int status;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "订单所属用户ID")
    private BigInteger userid;
    @Schema(description = "订单创建时间")
    private Date date;
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "该订单总价")
    private BigDecimal price;
    @TableField(exist = false)
    private List<OrderItem> orderItems;
}
