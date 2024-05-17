package com.andreeatuslea.musicapp.services;

import com.andreeatuslea.musicapp.repositories.PreisRepo;
import com.andreeatuslea.musicapp.tables.Preis;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class DirtyReadService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional
    public boolean[] transaction1(PreisRepo preisRepo) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);

        boolean[] result = new boolean[2];

        // Read data
        Preis preis = preisRepo.findById(999).orElse(null);
        result[0] = preis != null;


        new Thread(() -> transaction2(preisRepo)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Preis preisAfterDelay = preisRepo.findById(999).orElse(null);
        result[1] = preisAfterDelay != null;

        transactionManager.commit(transactionStatus);

        return result;
    }

    public void transaction2(PreisRepo  preisRepo) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);
        transactionStatus.setRollbackOnly();


        Preis preis = preisRepo.findById(999).orElse(new Preis());

        preis.setName("New Preis");
        preis.setPreisId(999);
        preisRepo.save(preis);

        transactionStatus.flush();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
}