package com.andreeatuslea.musicapp.services;
import com.andreeatuslea.musicapp.repositories.PreisRepo;
import com.andreeatuslea.musicapp.tables.Preis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.TimeUnit;

@Service
public class PreisService {

    @Autowired
    private PreisRepo preisRepo;
    @Autowired
    private PlatformTransactionManager transactionManager;

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();


    public void performTransactionalOperation() throws InterruptedException {
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Preis newPreis = new Preis();
            newPreis.setPreisId(999);
            newPreis.setName("New Preis");
            preisRepo.save(newPreis);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        TimeUnit.SECONDS.sleep(5);
        transactionManager.rollback(status);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean performReadOperationDuringTransaction() throws InterruptedException {
        TransactionStatus status = transactionManager.getTransaction(def);
        return preisRepo.findByPreisId(999) != null;
    }
}