package com.example.beanio.tran;

import com.example.beanio.tran.fixed.TR1000;
import com.example.beanio.tran.xml.TR1010;
import com.example.beanio.tran.fixed.TR1001;
import lombok.extern.slf4j.Slf4j;
import org.beanio.Marshaller;
import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.StreamBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class TRTest {

    private static final String STR_TR1000 = "TR1000T1234567890000010000001홍길동       010-1111-2222554433서울시 강남구 대치동";
    private static final String STR_TR1001 = "TR1001T12345678900001000";
    private static final String STR_TR1010 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><TR2><TR2222><type>TR1010</type><trid>bbbb</trid><name>홍길동</name></TR2222></TR2>";
    private Unmarshaller fxUnmarshaller;
    private Marshaller fxMarshaller;

    private Unmarshaller xmlUnmarshaller;
    private Marshaller xmlMarshaller;

    @Before
    public void setUp() {
        StreamFactory fxFactory = StreamFactory.newInstance();
        fxFactory.define(new StreamBuilder("TR")
                .format("fixedlength")
                .addRecord(TR1000.class)
                .addRecord(TR1001.class));

        fxUnmarshaller = fxFactory.createUnmarshaller("TR", Locale.KOREAN);
        fxMarshaller = fxFactory.createMarshaller("TR");

        StreamFactory xmlFactory = StreamFactory.newInstance();
        xmlFactory.define(new StreamBuilder("TR2")
                .format("xml")
                .addRecord(TR1010.class));

        xmlUnmarshaller = xmlFactory.createUnmarshaller("TR2", Locale.KOREAN);
        xmlMarshaller = xmlFactory.createMarshaller("TR2");
    }

    @Test
    public void testFixedTr() {

        log.info("{}", fxUnmarshaller.unmarshal(STR_TR1000));
        assertThat(fxMarshaller.marshal(fxUnmarshaller.unmarshal(STR_TR1000)).toString(), is(STR_TR1000));
        assertThat(fxMarshaller.marshal(fxUnmarshaller.unmarshal(STR_TR1001)).toString(), is(STR_TR1001));
    }

    @Test
    public void testXmlTr() {

        assertThat(xmlMarshaller.marshal(xmlUnmarshaller.unmarshal(STR_TR1010)).toString(), is(STR_TR1010));
    }

    @Test
    public void testKorean() {

        final TR1010 tr1010 = new TR1010();
        tr1010.setTrid("TR1010");
        tr1010.setName("홍길동");
        final Marshaller marshal = xmlMarshaller.marshal(tr1010);
        log.info("[{}]", marshal);
    }
}