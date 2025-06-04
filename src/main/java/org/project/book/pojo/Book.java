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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "图书ID")
    private BigInteger id;
    @Schema(description = "图书标题")
    private String title;
    @Schema(description = "图书作者")
    private String author;
    @Schema(description = "出版社")
    private String publisher;
    @Schema(description = "ISBN")
    private String isbn;
    @Schema(description = "单价")
    private BigDecimal price;
    @Schema(description = "简介")
    private String description;
    @Schema(description = "图书封面Base64")
    private String cover;
    @Schema(description = "图书库存")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger stock;
}
