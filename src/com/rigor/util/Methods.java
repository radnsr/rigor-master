package com.rigor.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class Methods {

	public String generateID(Session session,String prefix, String column, Class t) {
		String generateId = "";
	
		Criteria criteria = session.createCriteria(t).setProjection(Projections.max(column));
		String maxId = (String) criteria.uniqueResult();		
		int b = 0;
		if (maxId != null) {
			String a = maxId.substring(1);
			b = Integer.parseInt(a);
		}
		if (b <= 8) {
			b += 1;
			generateId += prefix + "000" + b;
			return generateId;
		} else {
			b += 1;
			generateId += prefix + "00" + b;
			return generateId;
		}
	}
}