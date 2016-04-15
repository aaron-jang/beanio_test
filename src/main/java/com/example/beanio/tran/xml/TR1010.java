package com.example.beanio.tran.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Getter
@Setter
@ToString
@Record(name = "TR2222")
@Fields({
        @Field(name = "type", rid = true, literal = "TR1010")
})
public class TR1010 {

    @Field
    private String trid;

    @Field
    private String name;
}
