package modelo.dao;

import java.io.*;
import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.dto.MovimientoDTO;
import modelo.entidades.CategoriaTransferencia;
import modelo.entidades.Cuenta;
import modelo.entidades.Transferencia;


/**
 * 
 */
public class TransferenciaDAO extends MovimientoDAO {


    /**
     * Default constructor
     */
    public TransferenciaDAO() {
	
    }

    /**
     * 
     */
    public void getAllByAccount() {
        // TODO implement here
    }

    /**
     * @param from 
     * @param to
     * @return 
     */
    public List<MovimientoDTO> getAll(Date from, Date to) {
        // TODO implement here
    	return null;
    }

    /**
     * @param amount 
     * @param dstAccount 
     * @param srcAccount 
     * @param date 
     * @param concept 
     * @param category
     */
    public void transfer(double amount, Cuenta dstAccount, Cuenta srcAccount, Date date, String concept, CategoriaTransferencia transferCategory) {
    	CuentaDAO cdao = new CuentaDAO();
    	cdao.updateBalance(amount, dstAccount.getId());
    	cdao.updateBalance(-amount, srcAccount.getId());
    	EntityManager em = ManejoEntidadPersistencia.getEntityManager();
		em.getTransaction().begin();
		try {
            Transferencia transferencia = new Transferencia(concept,date,amount,srcAccount,dstAccount, transferCategory);
			em.persist(transferencia);	
			em.getTransaction().commit();
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		em.close();
    }

}