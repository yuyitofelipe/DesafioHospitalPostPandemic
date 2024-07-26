package cl.praxis.ProyectoHospitalPost.controller;

import cl.praxis.ProyectoHospitalPost.ProyectoHospitalPostApplication;
import cl.praxis.ProyectoHospitalPost.model.dto.Paciente;
import cl.praxis.ProyectoHospitalPost.model.service.PacienteService;
import cl.praxis.ProyectoHospitalPost.model.service.PacienteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostApplication.class);
    PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping()
    public String findAll(Model model){
        logger.info("Ejecutando findAll() de PacienteController");
        model.addAttribute("pacientes", service.findAll());
        return "pacientesList";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") int id, Model model){
        logger.info("Ejecutando findOne() de PacienteController");
        model.addAttribute("paciente", service.findOne(id));
        return "pacienteEdit";
    }

    @PostMapping
    public String update(@ModelAttribute Paciente paciente){
        boolean result = service.update(paciente);
        if (result){
            logger.info("Libro actualizado correctamente");
        } else {
            logger.error("Error al actualizar libro");
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/new")
    public ModelAndView create(){
        return new ModelAndView("pacienteNew");
    }

    @PostMapping("/new")
    public String save(@ModelAttribute Paciente paciente){
        boolean result = service.create(paciente);

        if (result){
            logger.info("Libro creado correctamente");
        } else {
            logger.error("Error al crear libro");
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") int id){
        boolean result = service.delete(id);

        if (result){
            logger.info("Libro eliminado correctamente");
        } else {
            logger.error("Error al eliminar el libro");
        }
        return "redirect:/pacientes";
    }

}
