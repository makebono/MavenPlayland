package com.makebono.mavenplayland.module_test.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

/** 
 * @ClassName: ListResponseConverter 
 * @Description: Converter for list.
 * @author makebono
 * @date 2018年1月5日 上午9:35:11 
 *  
 */
@SuppressWarnings(value = { "rawtypes" })
public class ListResponseConverter extends CustomizedResponseConverter<List> {
    @Override
    protected void writeInternal(final List t, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        System.out.println("Writing: " + outputMessage.getBody());
        final OutputStream outputStream = outputMessage.getBody();
        final StringBuilder output = new StringBuilder();

        for (final Object cursor : t) {
            output.append(cursor + "<br>");
        }

        outputStream.write(output.toString().getBytes());
        outputStream.close();
    }
}
