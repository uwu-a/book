package org.project.book.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder
public class Book implements Serializable {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "图书ID")
    private BigInteger id;
    @Schema(description = "图书标题")
    @TableField("book_name")
    private String book_name;
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
    @TableField("cover_image")
    private String cover_image;
    @Schema(description = "图书库存")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger stock;
    @Version
    private Integer version;
}
