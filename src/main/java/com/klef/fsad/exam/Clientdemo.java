package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class Clientdemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        // Insert Record
        Transaction tx = session.beginTransaction();

        Payment p = new Payment();
        p.setName("Rahul");
        p.setDate(new Date());
        p.setStatus("SUCCESS");
        p.setAmount(5000);
        p.setMethod("UPI");

        session.save(p);

        tx.commit();

        System.out.println("Payment Inserted");

        // Delete using HQL
        Transaction tx2 = session.beginTransaction();

        String hql = "delete from Payment where id=:pid";

        Query q = session.createQuery(hql);
        q.setParameter("pid",1);

        int result = q.executeUpdate();

        tx2.commit();

        System.out.println(result+" Record Deleted");

        session.close();
        sf.close();
    }
}