package com.exercise.hibernate2;
 
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
 
public class CustomPersonId implements IdentifierGenerator {


    public String generateId() {
        Random rnd = new Random();
        String result = "";
        for (int i=0;i<4;i++) {
            int value = rnd.nextInt(9);
            result += String.valueOf(value);
        }
        return result;
    }
 
    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {

        Date date1 = new Date();
        Calendar calendar1 = Calendar.getInstance();
        return  "P-"+this.generateId() +"-"+calendar1.get(Calendar.YEAR);
 
    }
}