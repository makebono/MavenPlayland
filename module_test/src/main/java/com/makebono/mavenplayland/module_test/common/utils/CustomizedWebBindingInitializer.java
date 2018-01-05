package com.makebono.mavenplayland.module_test.common.utils;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: CustomizedWebBindingInitializer 
 * @Description: Implements WebBindingInitializer, translates date inputs
 * @author makebono
 * @date 2018年1月3日 上午10:15:47  
 */
public class CustomizedWebBindingInitializer implements WebBindingInitializer {
    private static final Logger logger = LoggerFactory.getLogger(CustomizedWebBindingInitializer.class);

    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void initBinder(final WebDataBinder binder, final WebRequest req) {
        // System.out.println("boo!");

        binder.registerCustomEditor(Student.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(final String input) {
                logger.debug(
                        "Convert input to Student. Following format: 'FirstName LastName(id) from University'. Input:  "
                                + input);
                try {
                    System.out.println("Read a String input, convert it into student.");
                    final Student student = new Student();
                    final int givenNameIndex = input.indexOf(' ');
                    final int idIndex = input.indexOf('(');
                    final int fromIndex = input.indexOf(' ', idIndex) + 1;
                    final int universityIndex = input.indexOf(' ', fromIndex);

                    final String givenName = input.substring(0, givenNameIndex);
                    final String surname = input.substring(givenNameIndex + 1, idIndex);
                    final int id = Integer.valueOf(input.substring(idIndex + 1, fromIndex - 2));
                    final String university = input.substring(universityIndex + 1, input.length());

                    student.setGivenName(givenName);
                    student.setSurname(surname);
                    student.setId(id);
                    student.setUniversity(university);

                    setValue(student);
                }
                catch (final Exception e) {
                    System.out.println(
                            "Error occurs, please follow the input format: 'FirstName LastName(id) from University'. For more details: "
                                    + e.getMessage());
                }
            }
        });

        // It's apparently that multiple binding could be done here. Just register the custom editors one by one.
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
