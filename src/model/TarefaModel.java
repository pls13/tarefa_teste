package model;

import entities.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author timi
 */
public class TarefaModel extends AbstractModel<Tarefa> {

    public TarefaModel() {
        super(Tarefa.class);
    }

    public List<Tarefa> getMinhasTarefas(int id_usuario) {
        if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().getTransaction().begin();
        }
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Tarefa.class);
        //crit.add(Restrictions.eq("id_usuario_executor",id_usuario));
        crit.add(Restrictions.sqlRestriction("id_usuario_executor = "+id_usuario));
        crit.add(Restrictions.ne("status", Tarefa.FINALIZADA));
        crit.addOrder(Order.desc("created"));
        return crit.list();
    }
    
    public List<Tarefa> getTarefasCadastro(int id_usuario) {
        if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().getTransaction().begin();
        }
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Tarefa.class);
        crit.addOrder(Order.desc("created"));
        return crit.list();
    }
}
