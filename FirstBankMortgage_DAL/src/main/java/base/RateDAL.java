package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Collections;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {


	public static double getRate(int GivenCreditScore)
	{
		Transaction trans = null;
		double rate;
		Session session = HibernateUtil.getSessionFactory().openSession();
			trans = session.beginTransaction();
			Query query = session.createQuery("From minCreditScore where RateDomainModel >= " + GivenCreditScore);
			List<?> loQueries = query.list();
			rate = ((RateDomainModel)loQueries.get(0)).getInterestRate();
			
			for(int z=0; z <= loQueries.size(); z++)//Goes through the list and finds the lowest rate
			{
				if(rate >= ((RateDomainModel)loQueries.get(z)).getInterestRate())
				{
					rate = ((RateDomainModel)loQueries.get(z)).getInterestRate();
				}
				else{
					rate=rate;}
				}
			trans.commit();
			return rate;
	}
	
}
