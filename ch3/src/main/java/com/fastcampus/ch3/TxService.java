package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired
    A1Dao a1Dao;
    @Autowired
    B1Dao b1Dao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1withTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        insertB1withTx();
        a1Dao.insert(2, 100); // 성공
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1withTx() throws Exception {
        b1Dao.insert(1, 100); // 실패
        b1Dao.insert(1, 200); // 실패
    }

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);

    }

    @Transactional(rollbackFor = Exception.class) // - Exception을 rollback
//    @Transactional // RuntimeException, Error만 rollback
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100); // 성공
        a1Dao.insert(1, 200); // 실패
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}
