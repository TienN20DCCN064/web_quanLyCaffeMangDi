package ptithcm.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class SupportController {
	@Autowired
	SessionFactory factory;

	public int update(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public int insert(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public int delete(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public static String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			return myHash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean descryptPassword(String inputPassword, String hashPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inputPassword.getBytes());
			byte[] digest = md.digest();
			String myCheck = DatatypeConverter.printHexBinary(digest).toUpperCase();
			return hashPassword.equals(myCheck);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
