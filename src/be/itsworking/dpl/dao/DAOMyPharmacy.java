package be.itsworking.dpl.dao;

import java.util.ArrayList;

import be.itsworking.dpl.to.MyPharmacy;

public interface DAOMyPharmacy
{
	MyPharmacy loadMyPharmacy(int id);
	MyPharmacy loadMyPharmacy(String name);
	boolean deleteMyPharmacy(int id);
	boolean insertMyPharmacy(MyPharmacy myPharmacy);
	boolean updateMyPharmacy(MyPharmacy myPharmacy);

	ArrayList<MyPharmacy> selectAllPharmacies();
	boolean insertMyPharmacyList(ArrayList<MyPharmacy> locList);

	int countAll();
	boolean exist(int idPharmacy);
}
