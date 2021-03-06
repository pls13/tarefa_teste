package entities;
// Generated 01/08/2015 18:08:32 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tarefa generated by hbm2java
 */
@Entity
@Table(name="tarefa"
    ,schema="public"
)
public class Tarefa  implements java.io.Serializable {
    
    
    public static final String PARADA =  "Parada";
    public static final String EXECUTANDO =  "Executando";
    public static final String FINALIZADA =  "Finalizada";
    
    
     private int id;
     private Usuario usuarioByIdUsuarioCriador;
     private Usuario usuarioByIdUsuarioExecutor;
     private String titulo;
     private String descricao;
     private Integer esforcoEstimado;
     private String status;
     private Date created;
     private Set<TarefaExecucao> tarefaExecucaos = new HashSet<TarefaExecucao>(0);

    public Tarefa() {
    }

	
    public Tarefa(int id, Usuario usuarioByIdUsuarioCriador, Usuario usuarioByIdUsuarioExecutor, String titulo) {
        this.id = id;
        this.usuarioByIdUsuarioCriador = usuarioByIdUsuarioCriador;
        this.usuarioByIdUsuarioExecutor = usuarioByIdUsuarioExecutor;
        this.titulo = titulo;
    }
    public Tarefa(int id, Usuario usuarioByIdUsuarioCriador, Usuario usuarioByIdUsuarioExecutor, String titulo, String descricao, Integer esforcoEstimado, String status, Date created, Set<TarefaExecucao> tarefaExecucaos) {
       this.id = id;
       this.usuarioByIdUsuarioCriador = usuarioByIdUsuarioCriador;
       this.usuarioByIdUsuarioExecutor = usuarioByIdUsuarioExecutor;
       this.titulo = titulo;
       this.descricao = descricao;
       this.esforcoEstimado = esforcoEstimado;
       this.status = status;
       this.created = created;
       this.tarefaExecucaos = tarefaExecucaos;
    }
   
    @Id 
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="tarefa_id_seq", name="tarefa_id_seq")
    @GeneratedValue(generator="tarefa_id_seq", strategy=GenerationType.SEQUENCE)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_criador", nullable=false)
    public Usuario getUsuarioByIdUsuarioCriador() {
        return this.usuarioByIdUsuarioCriador;
    }
    
    public void setUsuarioByIdUsuarioCriador(Usuario usuarioByIdUsuarioCriador) {
        this.usuarioByIdUsuarioCriador = usuarioByIdUsuarioCriador;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario_executor", nullable=false)
    public Usuario getUsuarioByIdUsuarioExecutor() {
        return this.usuarioByIdUsuarioExecutor;
    }
    
    public void setUsuarioByIdUsuarioExecutor(Usuario usuarioByIdUsuarioExecutor) {
        this.usuarioByIdUsuarioExecutor = usuarioByIdUsuarioExecutor;
    }

    
    @Column(name="titulo", nullable=false)
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    @Column(name="descricao")
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="esforco_estimado")
    public Integer getEsforcoEstimado() {
        return this.esforcoEstimado;
    }
    
    public void setEsforcoEstimado(Integer esforcoEstimado) {
        this.esforcoEstimado = esforcoEstimado;
    }

    
    @Column(name="status", length=20)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", length=29,insertable = false,updatable = false)
    
    public Date getCreated() {
        return this.created;
    }
    
    
    public void setCreated(Date created) {
        this.created = created;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tarefa")
    public Set<TarefaExecucao> getTarefaExecucaos() {
        return this.tarefaExecucaos;
    }
    
    public void setTarefaExecucaos(Set<TarefaExecucao> tarefaExecucaos) {
        this.tarefaExecucaos = tarefaExecucaos;
    }




}


