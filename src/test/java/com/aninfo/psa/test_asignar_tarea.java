package com.aninfo.psa;

import com.aninfo.psa.Services.ProyectoService;
import com.aninfo.psa.Services.TareaService;
import com.aninfo.psa.modelo.Proyecto;
import com.aninfo.psa.modelo.Tarea;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class test_asignar_tarea {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    TareaService tareaService;

    @Autowired
    TareaService tareaService3;

    @Autowired
    ProyectoService proyectoService2;

    @Autowired
    ProyectoService proyectoService3;

    private Proyecto proyecto;
    private Tarea tarea;
    private Tarea tarea3;

    private Proyecto proyecto2;
    private Proyecto proyecto3;
    private Proyecto proyecto4;

    //atributos para tests integrales
    private Tarea tarea5;

    private Proyecto proyecto5;
    private Proyecto proyecto6;

    @Transactional
    @Given("que hay un proyecto {string}")
    public void queHayUnProyecto(String nombreDeProyecto) {
        proyectoService.crearProyecto(new Proyecto(nombreDeProyecto));

        proyecto = new Proyecto(nombreDeProyecto);

        assertEquals(proyecto.getEstado(), "Pendiente");
    }

    @Transactional
    @When("creo una tarea llamada {string} para el proyecto")
    public void creoUnaTareaLlamadaParaElProyecto(String nombreTarea) {
        Long id = 1L;
        Long id_tarea = 1L;
        tareaService.crear_tarea(new Tarea(nombreTarea));
        proyectoService.asignar_tarea(proyectoService.buscarPorID(id).get(), id_tarea);

        assertEquals(nombreTarea, proyectoService.buscarPorID(id).get().getTareas().get(0).getNombre());

        tarea = new Tarea(nombreTarea, "Descripcion", "objetivo", "Alta");

        assertEquals(tarea.getEstado(), "Pendiente");

        proyecto.add_tarea(tarea);
    }
 
    @Transactional
    @Then("la tarea {string} quedara asociada al proyecto {string}")
    public void laTareaQuedaraAsociadaAlProyecto(String nombreTarea, String arg1) {
        Long id = Long.valueOf(1);
        Long id_tarea = Long.valueOf(1);
        assertEquals(nombreTarea, proyectoService.buscarPorID(id)
                .get()
                .getTareas()
                .stream()
                .filter(tarea -> tarea.getId() == id_tarea).findAny().get().getNombre());

        assertEquals(proyecto.getTarea(nombreTarea).getNombre(), tarea.getNombre());
    }

    //escenario 2------------------------------
    @Transactional
    @Given("que hay un proyecto {string} con el estado {string}")
    public void queHayUnProyectoConElEstado(String nombreProyecto, String estado) {
        proyectoService2.crearProyecto(new Proyecto(nombreProyecto));
        assertEquals(proyectoService2.buscarPorID(1L).get().getEstado(), "Pendiente");

        proyectoService2.asignar_estado(1L, estado);

        proyecto2 = new Proyecto(nombreProyecto);

        assertEquals(proyecto2.getEstado(), "Pendiente");
        proyecto2.setEstado(estado);
        assertEquals(proyecto2.getEstado(), estado);
    }

    @Transactional
    @When("creo una tarea {string} y la asigno al proyecto {string}")
    public void creoUnaTareaYLaAsignoAlProyecto(String nombreTarea, String nombreProyecto) {
        tareaService.crear_tarea(new Tarea(nombreTarea));
        proyectoService2.asignar_tarea(proyectoService2.buscarPorID(1L).get(), 1L);

        tarea = new Tarea(nombreTarea, "Descripcion", "objetivo", "Alta");
        assertEquals(tarea.getEstado(), "Pendiente");

        proyecto2.add_tarea(tarea);
    }

    @Transactional
    @Then("la tarea no se asignara")
    public void laTareaNoSeAsignara() {
        Proyecto proyectoInterrumpido = proyectoService2.buscarPorID(1L).get();

        assertEquals(proyectoInterrumpido.getEstado(), "Interrumpido");
        assertNull(proyectoInterrumpido.getTarea(tarea.getNombre()));

        assertNull(proyecto2.getTarea(tarea.getNombre()));
    }

    //escenario 3------------------------------
    @Transactional
    @Given("exista una tarea con nombre {string} asignada a un proyecto con nombre {string} con estado {string}")
    public void exista_una_tarea_con_nombre_asignada_a_un_proyecto_con_nombre_con_estado(String nomnreTarea, String nombreProyecto, String estadoProyecto) {

        //integral
        tarea5 = new Tarea(nomnreTarea, "Descripcion", "objetivo", "Alta");
        proyecto5 = new Proyecto(nombreProyecto, "implementacion", "cliente", "alcance", "version", "descripcion");

        assertEquals(proyectoService3.obtenerProyectos().size(), 2); //se cargaron los proyectos anteriores
        assertEquals(proyecto5.getEstado(), "Pendiente");

        proyectoService3.crearProyecto(proyecto5);
        assertEquals(proyectoService3.obtenerProyectos().get(1).getNombre(), proyecto5.getNombre());

        assertEquals(proyectoService3.buscarPorID(2L).get().getEstado(), "Pendiente");

        tareaService3.crear_tarea(tarea5);

        //busco la id de la tarea
        assertEquals(tareaService3.obtener_tarea(3L).get().getNombre(), tarea5.getNombre());

        proyectoService3.asignar_tarea(proyectoService3.buscarPorID(2L).get(), 3L);

        assertEquals(proyectoService3.buscarPorID(2L).get().getTarea(tarea5.getNombre()).getNombre(), tarea5.getNombre());

        proyectoService3.buscarPorID(2L).get().dar_baja();

        assertEquals(proyectoService3.buscarPorID(2L).get().getEstado(), estadoProyecto);
        assertEquals(proyectoService3.buscarPorID(2L).get().getTarea(tarea5.getNombre()).getEstado(), "Eliminada");

        //unitario
        tarea3 = new Tarea(nomnreTarea, "Descripcion", "objetivo", "Alta");
        proyecto3 = new Proyecto(nombreProyecto, "implementacion", "cliente", "alcance", "version", "descripcion");

        proyecto3.add_tarea(tarea3);

        assertEquals(proyecto3.getTarea(tarea3.getNombre()), tarea3);

        proyecto3.dar_baja();

        assertEquals(proyecto3.getEstado(), estadoProyecto);
    }

    @Transactional
    @Given("un proyecto con nombre {string}, con estado “En curso”")
    public void un_proyecto_con_nombre_con_estado_en_curso(String nombreNuevoProyecto) {

        //integral
        proyecto6 = new Proyecto(nombreNuevoProyecto, "implementacion", "cliente", "alcance", "version", "descripcion");

        proyectoService3.crearProyecto(proyecto6);

        assertEquals(proyectoService3.buscarPorID(4L).get().getNombre(), proyecto6.getNombre());
        assertEquals(proyectoService3.buscarPorID(4L).get().getEstado(), "Pendiente");

        proyectoService3.buscarPorID(4L).get().setEstado("En curso");

        assertEquals(proyectoService3.buscarPorID(4L).get().getEstado(), "En curso");


        //unitario
        proyecto4 = new Proyecto(nombreNuevoProyecto, "implementacion", "cliente", "alcance", "version", "descripcion");
        String estadoNuevoProyecto = "En curso";

        proyecto4.setEstado(estadoNuevoProyecto);

        assertEquals(proyecto4.getEstado(), estadoNuevoProyecto);
    }

    @Transactional
    @When("asigne la tarea con nombre {string} al proyecto {string}")
    public void asigne_la_tarea_con_nombre_al_proyecto(String nombreTarea, String nombreProyecto) {

        //integral
        Tarea tareaACambiar = tareaService3.obtener_tarea(3L).get();

        assertEquals(tareaACambiar.getNombre(), tarea5.getNombre());
        assertEquals(tareaACambiar.getEstado(), "Eliminada");

        //cambio la tarea de proyecto
        proyectoService3.asignar_tarea(proyectoService3.buscarPorID(4L).get(), 3L);


        //unitario
        Tarea tareaCambio = proyecto3.getTarea(tarea3.getNombre());

        assertEquals(tareaCambio, tarea3);

        proyecto4.add_tarea(tareaCambio);
    }

    @Transactional
    @Then("la tarea con nombre {string} no se asociará al nuevo proyecto")
    public void la_tarea_con_nombre_no_se_asociará_al_nuevo_proyecto(String string) {

        //integral
        assertNull(proyectoService3.buscarPorID(4L).get().getTarea(tarea5.getNombre()));

        //unitario
        assertNull(proyecto4.getTarea(tarea3.getNombre()));
    }
}
