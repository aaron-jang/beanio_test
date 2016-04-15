package com.example.beanio.tran.fixed;

import lombok.Getter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@ToString
@Getter
@Record(name = "TR")
@Fields({
        @Field(name = "type", at = 0, length = 6, rid = true, literal = "TR1001")
})
public class TR1001 {

    @Field(at = 0, length = 6)
    private String trid;

    @Field(at = 6, length = 10)
    private String data;

    @Field(at = 16, length = 8, align = Align.RIGHT, padding = '0')
    private Integer amount;
}
