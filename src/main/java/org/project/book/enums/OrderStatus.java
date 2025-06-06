package org.project.book.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    UNPAID(0, "未支付"),
    PAID(1, "已支付"),
    DELIVERING(2, "派送中"),
    COMPLETED(3, "已完成"),
    REFUND_PENDING(4, "待退款"),
    REFUNDED(5, "已退款"),
    CANCELLED(6,"已取消");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(int code) {
        OrderStatus status = fromCode(code);
        return status != null ? status.getDescription() : null;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}