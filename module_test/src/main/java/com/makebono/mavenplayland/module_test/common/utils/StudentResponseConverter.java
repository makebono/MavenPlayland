package com.makebono.mavenplayland.module_test.common.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: StudentResponseConverter 
 * @Description: Converter for @ResponseBody. Accepts Student as output and print it the way Http be capable to deal with. 
 * @author makebono
 * @date 2018年1月4日 上午10:12:03 
 *  
 */
public class StudentResponseConverter extends AbstractHttpMessageConverter<Student> {

    @Override
    protected boolean supports(final Class<?> clazz) {
        // System.out.println(Student.class.isAssignableFrom(clazz));
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean canWrite(final Class<?> clazz, final MediaType mediaType) {
        return supports(clazz);
    }

    @Override
    protected Student readInternal(final Class<? extends Student> clazz, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(final Student t, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        final OutputStream outputStream = outputMessage.getBody();
        System.out.println(outputMessage);
        final String output = t.toString();
        outputStream.write(output.getBytes());
        outputStream.close();
    }

}
