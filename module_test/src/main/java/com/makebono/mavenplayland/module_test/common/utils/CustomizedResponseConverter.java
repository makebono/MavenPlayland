package com.makebono.mavenplayland.module_test.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/** 
 * @ClassName: StudentResponseConverter 
 * @Description: Converter for @ResponseBody. Accepts any class as long as it has a toString() method and it's the 
 * output response you want as output. And print it the way Http be capable to deal with. Extends this class for 
 * specific using.
 * @author makebono
 * @date 2018年1月4日 上午10:12:03 
 *  
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public abstract class CustomizedResponseConverter<T> extends AbstractHttpMessageConverter<T> {
    @Override
    protected boolean supports(final Class<?> clazz) {
        final Class me = this.getClass();
        Class target = null;
        final ParameterizedType type = (ParameterizedType) me.getGenericSuperclass();
        target = (Class) type.getActualTypeArguments()[0];
        // System.out.println(target.getName());
        // System.out.println(clazz.getName());
        if (target.isAssignableFrom(clazz)) {
            System.out.println("Converter for " + target.getName() + " found.");
        }
        return (target.isAssignableFrom(clazz));
    }

    // This decides if could write the ouput. So need to be handled carefully.
    @Override
    public boolean canWrite(final Class<?> clazz, final MediaType mediaType) {
        return supports(clazz);
    }

    // For requesting input, not using here.
    @Override
    protected T readInternal(final Class<? extends T> clazz, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return null;
    }

    // Decide what to be printed in ResponseBody
    @Override
    protected void writeInternal(final Object t, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        System.out.println("Writing: " + outputMessage.getBody());
        final OutputStream outputStream = outputMessage.getBody();
        final String output = t.toString();
        outputStream.write(output.getBytes());
        outputStream.close();
    }
}
