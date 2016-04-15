package com.example.beanio.tran.fixed;

import lombok.Getter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;
import org.beanio.builder.Align;

import java.util.List;

@ToString
@Getter
@Record(name = "TR")
@Fields({
        @Field(name = "type", at = 0, length = 6, rid = true, literal = "TR1000")
})
public class TR1000 {

    @Field(at = 0, length = 6)
    private String trid;

    @Field(at = 6, length = 10)
    private String data;

    @Field(at = 16, length = 8, align = Align.RIGHT, padding = '0')
    private Integer amount;

    @Field(at = 24, length = 5, align = Align.RIGHT, padding = '0')
    private Integer addressCount;

    @Segment(at = 29, occursRef = "addressCount")
    private List<Address> addressList;

    @ToString
    @Getter
    public static class Address {

        @Field(at = 0, length = 10)
        private String name;

        @Field(at = 10, length = 13)
        private String phoneNumber;

        @Field(at = 23, length = 6)
        private String zipCode;

        @Field(at = 29, length = 11)
        private String address;
    }
}
