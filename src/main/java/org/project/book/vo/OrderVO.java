package org.project.book.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.project.book.pojo.Book;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder
public class OrderVO implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger orderID;
    private int Status;
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger userID;
    private Date Time;
    private BigDecimal Price;
    private List<Book> content;
}
