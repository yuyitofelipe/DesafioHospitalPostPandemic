package cl.praxis.ProyectoHospitalPost.model.service;

import cl.praxis.ProyectoHospitalPost.ProyectoHospitalPostApplication;
import cl.praxis.ProyectoHospitalPost.model.dto.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    // private JdbcTemplate template;
    /*
    public PacienteServiceImpl(JdbcTemplate template) {
        this.template = template;
        }

    @Override
    public List<Tuit> findAll() {
        return template.query("select id, mensaje from tuit",
                new BeanPropertyRowMapper<>(Tuit.class));
    }
     */
    private List<Paciente> pacienteList;
    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostApplication.class);

    public PacienteServiceImpl() {
        pacienteList = new ArrayList<>();
        pacienteList.add(new Paciente(1, "Juan", "Perez", "12345678-9", 25));
        pacienteList.add(new Paciente(2, "Maria", "Lopez", "98765432-1", 30));
        pacienteList.add(new Paciente(3, "Pedro", "Gonzalez", "11223344-5", 40));
    }

    public PacienteServiceImpl(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public List<Paciente> findAll() {

        logger.info("Ejecutando findAll de PacienteServiceImpl");

        return pacienteList;
    }

    @Override
    public Paciente findOne(int id) {
        return pacienteList.stream()
                            .filter(paciente -> paciente.getId() == id)
                            .findFirst()
                            .orElse(null);

    }

    @Override
    public boolean create(Paciente p) {
        pacienteList.add(p);
        return true;
    }

    @Override
    public boolean update(Paciente p) {
        logger.info("Actualizando Paciente con id: " + p.getId());
        Paciente paciente =  findOne(p.getId());
        paciente.setNombre(p.getNombre());
        paciente.setApellido(p.getApellido());
        paciente.setRut(p.getRut());
        paciente.setEdad(p.getEdad());

        return true;
    }

    @Override
    public boolean delete(int id) {
       Paciente p = findOne(id);
       if(p != null){
          pacienteList.remove(p);
          return true;
       }

       return false;

    }
}
