package be.itsworking.dpl.dao;

import java.util.ArrayList;

import be.itsworking.dpl.to.MyPharmacy;

public interface DAOMyPharmacy {

    boolean deleteMyPharmacy(int id);

	boolean insertMyPharmacy(MyPharmacy myPharmacy);

    boolean updateMyPharmacy(MyPharmacy myPharmacy);

    MyPharmacy loadMyPharmacy(int id);
    
    ArrayList<MyPharmacy> selectAllPharmacies();
    
    int countAll();

	boolean exist(MyPharmacy myPharmacy);

	boolean insertMyPharmacyList(ArrayList<MyPharmacy> locList);
}
